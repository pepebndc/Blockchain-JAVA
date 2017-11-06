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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            //  1.notify the rest of the network
            //  2.response to the new host, sendind the blockchain
            if (command.getCommand().equals("NEW HOST CONNECT")) {
                System.out.println("I AM ASKED TO CONNECT BY : " + rawClientIP.toString());
                main.TFG.getHosts().add(ClientIP);
                //notify the rest of the network (NEW HOST ADD)
                Iterator<String> it = main.TFG.getHosts().iterator();
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

                //SEND THE CONTENTS TO THE NEW HOST
                TCPclient.sendNewHostAccept(ClientIP);
                System.out.println("I HAVE SENT THE BLOCKCHAIN TO THE NEW HOST");
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
            //THERE IS A NEW CONTENT TO MINE, UPDATE MINING CONTENTS
            if (command.getCommand().equals("ADD CONTENT")) {
                System.out.println("There is a new content to mine: "+command.getContents());
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
