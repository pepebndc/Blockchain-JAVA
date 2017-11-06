/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class TCPserverTEMP extends Thread {
}
/*

    static Socket s;
    private boolean conected;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;

    @Override
    public void run() {
        System.out.println("error3");
        setConected(true);
        System.out.println("error4");
        //opening the connection
        try {
            System.out.println("error5");
            ServerSocket serv = new ServerSocket(4004); //Port 4000
            System.out.println("error6");
            Socket so = serv.accept();
            System.out.println("error7");
            ObjectOutputStream oo = new ObjectOutputStream(so.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(so.getInputStream());
            System.out.println("socket open in 4004 " + so.getInetAddress().getHostAddress());
        } catch (IOException e) {
            System.out.println("Error opening in/out" + e);
            return;
        }
        while (isConected()) {
            //getting the client's IP
            InetAddress rawClientIP = s.getInetAddress();
            String ClientIP = rawClientIP.getHostAddress();

            System.out.println("---------------  connetion open  ---------------");

            //We read the command sent and compare with our list of commands
            CommandMessaje command = null;
            try {
                command = (CommandMessaje) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(TCPserverTEMP.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Command DISCONECTED, take the ip of the sender out of the hosts list
            if (command.getCommand().equals("DISCONECTED")) {
                quit(); //quit connection with the client, we have all the information needed and we avoid using more time the TCP connection

                Iterator<InetAddress> it = main.TFG.getHosts().iterator();
                while (it.hasNext()) {
                    InetAddress host = it.next();
                    if (host.equals(rawClientIP)) {
                        it.remove();
                    }
                }
                return;
            }

            //Command NEW HOST CONNECT, places the ip of the sender on the hosts list and sends the Blockchain object and alert the rest of the network
            if (command.getCommand().equals("NEW HOST CONNECT")) {
                System.out.println("PEPE");
                try {
                    main.TFG.getHosts().add(rawClientIP);

                    //send the blockchain to the new host
                    String com = "JOIN ME";
                    InetAddress a = null;
                    BlockChain bc = main.TFG;
                    Block b = null;
                    String c = null;

                    CommandMessaje resp = new CommandMessaje(com, a, bc, b, c);
                    oos.writeObject(resp);
                    oos.flush();

                    //notify the network
                    sendNewHostRecieve(rawClientIP);

                    quit(); //quit connection with the client
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(TCPserverTEMP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //Command NEW HOST RECIEVE, recieves the IP of the new host from the "link" host and adds into the hosts list
            if (command.getCommand().equals("NEW HOST RECIEVE")) {

                InetAddress newClient = command.getAddress();
                main.TFG.getHosts().add(newClient);

                quit(); //quit connection with the client
                return;
            }

            //Command JOIN ME, recieves the Blockchain from the new connection
            if (command.getCommand().equals("JOIN ME")) {

                main.TFG = command.getBlockchain();

                quit(); //quit connection with the client
                return;
            }

            //Command ADD, recieves the updated string of contents of the block being mined
            if (command.getCommand().equals("ADD")) {
                String contents = command.getContents();

                main.TFG.setCurrentMiningContents(contents);

                quit(); //quit connection with the client
                return;
            }

            //Command DIFF, recieves the updated string of contents of the block being mined
            if (command.getCommand().equals("DIFF")) {
                String contents = command.getContents();
                int diff = Integer.parseInt(contents);

                main.TFG.setDiff(diff);

                quit(); //quit connection with the client
                return;
            }

            //Command NEW BLOCK, recieves the updated string of contents of the block being mined
            if (command.getCommand().equals("NEW BLOCK")) {
                Block b = command.getBlock();
                main.TFG.getChain().add(b);

                quit(); //quit connection with the client
            }
        }

    } // fin run

    //METHODS
    private String byteArrayToHexString(byte[] b) {
        String result = "";
        int j;
        for (int i = 0; i < b.length; i++) {
            j = (b[i] & 0xff) + 0x100;
            result += Integer.toString(j, 16).substring(1);
        }
        return result;
    }

    public static void sendNewHostConnect(String ip) throws IOException {
        System.out.println("connect1");
        ObjectOutputStream oo=null;
        Socket so=null;
        try {
            System.out.println("Error1");
            so = new Socket("localhost", 406);
            System.out.println("Error2");
            oo = new ObjectOutputStream(so.getOutputStream());
            System.out.println("Error3");
            //ObjectInputStream oi = new ObjectInputStream(so.getInputStream());
            System.out.println("Error4");

        } catch (IOException e) {
            System.out.println("Problems conecting on TCP");
            System.out.println(e);

        }

        System.out.println("connect2");
        String com = "NEW HOST CONNECT";
        InetAddress a = null;
        BlockChain bc = null;
        Block b = null;
        String c = null;

        CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
        oo.writeObject(command);
        oo.flush();
        oo.close();
        so.close();
        
    }

    public static void sendNewHostRecieve(InetAddress ip) {
        //TO DO- SEND TO EVERY HOST ON THE LIST THE NEW HOST LIST
    }

    public static void sendNewContent() {
        //TO DO- GET THE NEW CONTENTS AND SENDS TO EVERYONE
    }

    public static void sendNewDiff() {
        //TO DO- GET THE NEW DIFF AND SEND TO EVERYONE
    }

    public static void sendDisconect() {
        // TO DO- SEND A DISCONECT SIGNAL TO EVERYONE
    }

    public static void connect(String ip) {
        try {
            System.out.println("Error1");
            Socket so = new Socket("localhost", 403);
            System.out.println("Error2");
            ObjectOutputStream oos = new ObjectOutputStream(so.getOutputStream());
            System.out.println("Error3");
            ObjectInputStream ois = new ObjectInputStream(so.getInputStream());
            System.out.println("Error4");

        } catch (IOException e) {
            System.out.println("Problems conecting on TCP");
            System.out.println(e);

        }
    }

    public static void quit() {
        try {
            oos.close();
            ois.close();
            s.close();
            System.out.println("---------------  Connection closed  ---------------");
        } catch (IOException e) {
            System.out.println("Error closing in/out");
        }
    }

    /**
     * @return the conected
 */
// public boolean isConected() {
//     return conected;
// }

/**
 * @param conected the conected to set
 */
//   public void setConected(boolean conected) {
//      this.conected = conected;
//  }
 // fin clase SesionTCP
