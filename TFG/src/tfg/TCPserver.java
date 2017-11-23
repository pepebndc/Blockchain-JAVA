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
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

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
    private String challenge = "";

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
                System.out.println("Error reading the CommandMessaje:" +ex);
                Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }
            

            //A NEW HOST WANTS TO JOIN THE NETWORK
            if (command.getCommand().equals("NEW HOST CONNECT")) {
                try {
                    System.out.println("I AM ASKED TO CONNECT BY : " + ClientIP + "(" + rawClientIP.toString() + ")");

                    //Send the challenge
                    //generate random string
                    char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
                    StringBuilder sb = new StringBuilder();
                    Random random = new Random();
                    for (int i = 0; i < 100; i++) {
                        char c = chars[random.nextInt(chars.length)];
                        sb.append(c);
                    }
                    this.challenge = sb.toString();
                    PublicKey pubKey = command.getPubKey();
                    System.out.println("Public key: " + pubKey);
                    Cipher cipher;
                    cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    //encrypt the challenge
                    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                    byte[] aux = cipher.doFinal(challenge.getBytes("UTF-8"));
                    String codedChallenge = DatatypeConverter.printBase64Binary(aux);

                    TCPclient.sendNewChallenge(ClientIP, "codedChallenge");
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

            //RESPONSE FROM THE NEW HOST WITH THE RESOLVED CHALLENGE
            if (command.getCommand().equals("NEW HOST CHALLENGE RESPONSE")) {
                System.out.println("Resolution of the challenge: " + command.getContents());

                //check with the generated challenge
                if (command.getContents().equals(this.challenge)) {
                    try {
                        //the challege is completed, send everything
                        //CHECK IF THE IP IS IN THE NETWORK
                        Iterator<String> iti = main.getTFG().getHosts().iterator();
                        boolean IPalreadyAdded = false;
                        while (iti.hasNext()) {
                            String host = iti.next();
                            if (host.equals(ClientIP)) {
                                IPalreadyAdded = true;
                            }
                        }
                        if (!IPalreadyAdded) {
                            //add the new host to the blockchain
                            main.getTFG().getHosts().add(ClientIP);
                            
                            // notify the rest of the network (NEW HOST ADD)
                            Iterator<String> it = main.getTFG().getHosts().iterator();
                            while (it.hasNext()) {
                                String host = it.next();
                                try {
                                    if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                                        TCPclient.sendNewHostADD(ClientIP, host);
                                    }
                                    
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }
                        
                        //CHECK IF THE user IS IN THE NETWORK
                        User newUserConnected = new User (command.getName(), command.getAddress(), command.getPubKey());
                        Iterator<User> ite = main.getTFG().getUsers().iterator();
                        boolean userAlreadyAdded = false;
                        while (ite.hasNext()) {
                            User user = ite.next();
                            if (user.equals(newUserConnected)) {
                                userAlreadyAdded = true;
                            }
                        }
                        if (!userAlreadyAdded) {
                            //add the new user to the blockchain
                            
                            main.getTFG().getUsers().add(newUserConnected);
                            
                            // notify the rest of the network (NEW HOST ADD)
                            Iterator<String> it = main.getTFG().getHosts().iterator();
                            while (it.hasNext()) {
                                String host = it.next();
                                try {
                                    if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                                        TCPclient.sendNewUserADD(ClientIP, newUserConnected);
                                    }
                                    
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }

                        //SEND THE CONTENTS TO THE NEW HOST
                        TCPclient.sendNewHostAccept(ClientIP);
                        System.out.println("I HAVE SENT THE BLOCKCHAIN TO THE NEW HOST");
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }

                return;
            }

            //I HAVE SENT A CHALLENGE TO RESOLVE
            if (command.getCommand().equals("NEW HOST CHALLENGE")) {
                try {
                    System.out.println("I HAVE BEEN SENT A CHALLENGE");
                    String challengeReceived = command.getContents();
                    System.out.println("TCP SERVER - CHALLENGE recieved");
                    PrivateKey privKey = main.getLocalUser().getPrivateKey();
                    System.out.println("TCP SERVER - PRIVATE KEY recieved");
                    Cipher cipher;
                    cipher = Cipher.getInstance("RSA");
                    //encrypt the challenge
                    cipher.init(Cipher.DECRYPT_MODE, privKey);
                    System.out.println("TCP SERVER - CIPHER STARTED");
                    byte[] aux = cipher.doFinal(challengeReceived.getBytes("UTF-8"));
                    String decodedChallenge = DatatypeConverter.printBase64Binary(aux);

                    //send the response
                    User user = new User(main.getLocalUser().getName(), main.getLocalUser().getAddress(), main.getLocalUser().getPublicKey());
                    TCPclient.sendNewChallengeSolved(ClientIP, "codedChallenge", user);
                    
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

            //I HAVE BEEN ACCEPTED TO THE NETWORK, APPLY THE BLOCKCHAIN
            if (command.getCommand().equals("NEW HOST ACCEPTED")) {
                System.out.println("I HAVE BEEN ACCEPTED");
                main.setTFG(command.getBlockchain());
                return;
            }
            //A NEW HOST HAS JOINED, ADD TO THE HOST LIST
            if (command.getCommand().equals("NEW HOST ADD")) {
                System.out.println("A new host has joined");
                main.getTFG().getHosts().add(command.getAddress());
                return;
            }
            //A NEW USER HAS JOINED, ADD TO THE HOST LIST
            if (command.getCommand().equals("NEW USER ADD")) {
                try {
                    System.out.println("A new user has joined");
                    User newUserConnected = new User (command.getName(), command.getAddress(), command.getPubKey());
                    main.getTFG().getUsers().add(newUserConnected);
                    return;
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TCPserver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //THERE IS A NEW CONTENT TO MINE, UPDATE MINING CONTENTS
            if (command.getCommand().equals("ADD CONTENT")) {
                System.out.println("There is a new content to mine: " + command.getContents());
                main.getTFG().setCurrentMiningContents(command.getContents());
                return;
            }
            //THERE IS A NEW DIFFICULTY
            if (command.getCommand().equals("DIFF")) {
                System.out.println("There is a new difficulty in the network");
                main.getTFG().setDiff(Integer.parseInt(command.getContents()));
                return;
            }
            //THERE IS A NEW BLOCK
            if (command.getCommand().equals("NEW BLOCK")) {
                System.out.println("There is a new block in the network");
                main.getTFG().getChain().add(command.getBlock());
                //erase current mining contents
                main.getTFG().setCurrentMiningContents("");

                return;
            }
            //THE HOST WANTS TO DISCONECT, DROP FROM HOST LIST
            if (command.getCommand().equals("DISCONECT")) {
                System.out.println("The host " + ClientIP + " wants to disconect");

                Iterator<String> it = main.getTFG().getHosts().iterator();
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
