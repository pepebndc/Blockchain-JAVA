/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author pepeb
 */
public class TCPserver extends Thread {

    private boolean listening = true;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;
    private String challengeStored = null;

    public TCPserver(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {

        try { //OPEN IN/OUT STREAMS
            inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error opening in/out");
            return;
        }

        //GET CLIENT INFO
        SocketAddress rawClientIP = socket.getRemoteSocketAddress();
        //This gets the addres in the format "/192.168.X.X:(port number)"

        String ClientIP = rawClientIP.toString().substring(1, rawClientIP.toString().indexOf(':'));

        System.out.println("Connected");

        CommandMessaje command = null;
        while (isListening()) {

            try {//READ NEW COMMANDMESSAJE
                command = (CommandMessaje) inStream.readObject();
                //System.out.println("I have the CommandMessaje");
                System.out.println("Command received = " + command.getCommand());
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error reading the CommandMessaje");
                Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
            }

            //A NEW HOST WANTS TO JOIN THE NETWORK
            if (command.getCommand().equals("NEW HOST CONNECT")) {
                try {
                    System.out.println("I AM ASKED TO CONNECT BY : " + ClientIP + "(" + rawClientIP.toString() + ")");

                    //CREATE THE CHALLENGE
                    //create the random string
                    char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
                    StringBuilder sb = new StringBuilder();
                    Random random = new Random();
                    for (int i = 0; i < 50; i++) {
                        char c = chars[random.nextInt(chars.length)];
                        sb.append(c);
                    }
                    String plainTextChallenge = sb.toString();
                    main.challengeStored = plainTextChallenge;
                    System.out.println("I have created a challenge: " + plainTextChallenge);

                    //encrypt the challenge with the public key of the new host
                    PublicKey pubKey = command.getPubKey();
                    Cipher encrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    encrypt.init(Cipher.ENCRYPT_MODE, pubKey);
                    byte[] encryptedMessage = encrypt.doFinal(plainTextChallenge.getBytes());
                    System.out.println("I have created a challenge, encrypted: " + encryptedMessage);
                    TCPclient.sendChallenge(ClientIP, encryptedMessage);

                    return;
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            //I HAVE BEEN SENT A CHALLENGE
            if (command.getCommand().equals("NEW HOST CHALLENGE")) {
                try {
                    System.out.println("I HAVE BEEN SENT A CHALLENGE");
                    System.out.println("Encrypted CHALLENGE: " + command.getEncoded());
                    //decrypt the challenge
                    Cipher decrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    decrypt.init(Cipher.DECRYPT_MODE, main.getLocalUser().getPrivateKey());
                    String decryptedMessage = new String(decrypt.doFinal(command.getEncoded()), StandardCharsets.UTF_8);
                    System.out.println("Solution: " + decryptedMessage);
                    //send the response and the user data
                    TCPclient.sendChallengeResponse(ClientIP, decryptedMessage, main.getLocalUser().getName(), main.getLocalUser().getAddress(), main.getLocalUser().getPublicKey());

                    return;
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //I HAVE BEEN SENT THE SOLUTION OF A CHALLENGE
            if (command.getCommand().equals("NEW HOST SOLUTION")) {
                System.out.println("I HAVE BEEN SENT THE SOLUTION OF A CHALLENGE");
                System.out.println("The challenge I sent: " + main.challengeStored);
                System.out.println("The challenge I received: " + command.getContents());
                //compare the solution with the sent challenge
                if (command.getContents().equals(main.challengeStored)) {
                    //notify the new user that it's been accepted
                    TCPclient.sendNewHostAccept(ClientIP);

                   

                    //CHECK IF THE USER IS ON THE USERS LISTS. IF NOT, NOTIFY THE NETWORK OF A NEW USER
                    boolean existsUser = false;
                    Iterator<User> itu = main.TFG.getUsers().iterator();
                    while (itu.hasNext()) {
                        String user = itu.next().getAddress();
                        if (user.equals(command.getAddress())) {
                            existsUser = true;
                        }
                    }
                    if (!existsUser) {
                        try {
                            User newUser = new User(command.getName(), command.getAddress(), command.getPubKey());

                            //notify the network
                            Iterator<String> ite = main.TFG.getHosts().iterator();
                            while (ite.hasNext()) {
                                try {
                                    String host = ite.next();
                                    if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                                        TCPclient.sendNewUserADD(ClientIP, newUser);
                                    }
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            //add to my list
                            main.TFG.getUsers().add(newUser);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                     //CHECK IF THE IP IS ON THE HOSTS LISTS. IF NOT, NOTIFY THE NETWORK OF A NEW HOST
                    boolean existsHost = false;
                    Iterator<String> it = main.TFG.getHosts().iterator();
                    while (it.hasNext()) {
                        String host = it.next();
                        if (host.equals(ClientIP)) {
                            existsHost = true;
                        }
                    }
                    if (!existsHost) {

                        //notify the network
                        Iterator<String> ite = main.TFG.getHosts().iterator();
                        while (ite.hasNext()) {
                            try {
                                String host = ite.next();
                                if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                                    TCPclient.sendNewHostADD(ClientIP, host);
                                }
                            } catch (UnknownHostException ex) {
                                Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        //add to my list
                        main.TFG.getHosts().add(ClientIP);
                    }

                } else {
                    TCPclient.sendFailedChallenge(ClientIP);
                }
                return;
            }

            //I HAVE BEEN DENYED FROM THE NETWORK
            if (command.getCommand().equals("NEW HOST SOLUTION FAILED")) {
                System.out.println("I HAVE FAILED THE SOLUTION, restart and try again");

                return;
            }

            //I HAVE BEEN ACCEPTED TO THE NETWORK, APPLY THE BLOCKCHAIN
            if (command.getCommand().equals("NEW HOST ACCEPTED")) {
                System.out.println("I HAVE BEEN ACCEPTED");
                main.TFG = command.getBlockchain();
                return;
            }
            //A NEW HOST HAS JOINED, ADD TO THE HOST LIST
            if (command.getCommand().equals("NEW HOST ADD")) {
                System.out.println("A new host has joined");
                main.TFG.getHosts().add(command.getAddress());
                return;
            }
            //A NEW USER HAS JOINED, ADD TO THE USERS LIST
            if (command.getCommand().equals("NEW USER ADD")) {
                try {
                    System.out.println("A new user has joined");
                    User newUser = new User(command.getName(), command.getAddress(), command.getPubKey());
                    main.TFG.getUsers().add(newUser);
                    return;
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //THERE IS A NEW CONTENT TO MINE, UPDATE MINING CONTENTS
            if (command.getCommand().equals("ADD CONTENT")) {
                if (!command.getContents().equals("")) {
                    System.out.println("There is a new content to mine: " + command.getContents());
                }
                main.TFG.setCurrentMiningContents(command.getContents());
                return;
            }
            //THERE IS A NEW DIFFICULTY
            if (command.getCommand().equals("DIFF")) {
                System.out.println("There is a new difficulty in the network");
                main.TFG.setDiff(Integer.parseInt(command.getContents()));
                return;
            }
            //THERE IS A NEW BLOCK
            if (command.getCommand().equals("NEW BLOCK")) {
                System.out.println("There is a new block in the network");
                main.TFG.getChain().add(command.getBlock());
                //erase current mining contents
                main.TFG.setCurrentMiningContents("");

                return;
            }
            //THE HOST WANTS TO DISCONECT, DROP FROM HOST LIST
            if (command.getCommand().equals("DISCONECT")) {
                System.out.println("The host " + ClientIP + " wants to disconect");

                Iterator<String> it = main.TFG.getHosts().iterator();
                while (it.hasNext()) {
                    String host = it.next();
                    if (host.equals(ClientIP)) {
                        it.remove();
                    }
                }
                return;
            }

        }

    }

    public void quit() {
        try {
            outStream.close();
            inStream.close();
            socket.close();
            System.out.println("---------------  Connection closed  ---------------");
        } catch (IOException e) {
            System.out.println("Error closing in/out");
        }
    }

    /**
     * @return the listening
     */
    public boolean isListening() {
        return listening;
    }

    /**
     * @param listening the listening to set
     */
    public void setListening(boolean listening) {
        this.listening = listening;
    }

}
