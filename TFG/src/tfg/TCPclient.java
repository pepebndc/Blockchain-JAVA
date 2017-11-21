/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepeb
 */
public class TCPclient {

    public static void sendNewHostConnect(String ip, String address, String priv) {
        try {
            //TO DO- ASK TO JOIN THE NETWORK

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(ip, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST CONNECT";
            String a = address;
            BlockChain bc = null;
            Block b = null;
            String c = priv;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("new host messaje sent to " + ip);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewHostAccept(String ip) {
        try {
            //TO DO- ASK TO JOIN THE NETWORK

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                System.out.println("trying to connect to : " + ip);
                so = new Socket(ip, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST ACCEPTED";
            String a = null;
            BlockChain bc = main.getTFG();
            Block b = null;
            String c = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("new host accepted messaje sent to " + ip);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewHostADD(String ipNewHost, String endPoint) {
        //TO DO- SEND TO EVERY HOST ON THE LIST THE NEW HOST LIST

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST ADD";
            String a = ipNewHost;
            BlockChain bc = null;
            Block b = null;
            String c = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("new host ADD messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewContent(String endPoint) {
        //TO DO- GET THE NEW CONTENTS AND SENDS TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "ADD CONTENT";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = main.getTFG().getCurrentMiningContents();

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("new contents messaje sent to " + endPoint+" - "+c);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewDiff(String endPoint) {
        //TO DO- GET THE NEW DIFF AND SEND TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "DIFF";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = String.valueOf(main.getTFG().getDiff());

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("new difficulty messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewBlock(String endPoint, Block B) {
        // TO DO- SEND A DISCONECT SIGNAL TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW BLOCK";
            String a = null;
            BlockChain bc = null;
            Block b = B;
            String c = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("NEW BLOCK messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendDisconect(String endPoint) {
        // TO DO- SEND A DISCONECT SIGNAL TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "DISCONECT";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("DISCONECT messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewCommand(CommandMessaje CM, String ip) throws IOException {

        ObjectOutputStream oo = null;
        Socket s = null;
        try {
            s = new Socket(ip, 4001);
            oo = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.println("Problems conecting on TCP");
            System.out.println(e);
        }
        oo.writeObject(CM);
        oo.flush();
        oo.close();
        s.close();
    }

}
