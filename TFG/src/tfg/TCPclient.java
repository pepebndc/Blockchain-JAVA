/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepeb
 */
public class TCPclient {

    public static void sendNewHostConnect(String ip, String address, PublicKey pubKey) {
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
            String c = null;
            PublicKey k = pubKey;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] New host messaje sent to " + ip);
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
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST ACCEPTED";
            String a = null;
            BlockChain bc = main.TFG;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] New host accepted messaje sent to " + ip);
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
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST ADD";
            String a = ipNewHost;
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT]  New host ADD messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewUserADD(String endPoint, User newUser) {
        //TO DO- SEND TO EVERY HOST ON THE LIST THE NEW HOST LIST

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("[TCP CLIENT]  Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW USER ADD";
            String a = newUser.getAddress();
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = newUser.getPublicKey();
            String n = newUser.getName();
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT]  New user ADD messaje sent to " + endPoint);
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
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "ADD CONTENT";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = main.TFG.getCurrentMiningContents();
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();

            System.out.println("[TCP CLIENT]  New contents messaje sent to " + endPoint);

        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendNewPendingTransaction(String endPoint) {
        //TO DO- GET THE NEW CONTENTS AND SENDS TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "PENDING TRANSACTION";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = main.TFG.getPendingTransactions();
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();

            System.out.println("[TCP CLIENT]  New pending transaction messaje sent to " + endPoint);

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
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "DIFF";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = String.valueOf(main.TFG.getDiff());
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] New difficulty messaje sent to " + endPoint);
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
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW BLOCK";
            String a = null;
            BlockChain bc = null;
            Block b = B;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] NEW BLOCK messaje sent to " + endPoint);
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
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "DISCONECT";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] DISCONECT messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendChallenge(String endPoint, byte[] challenge) {
        // TO DO- SEND A DISCONECT SIGNAL TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST CHALLENGE";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = challenge;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] SEND CHALLENGE messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendChallengeResponse(String endPoint, String decrypted, String userName, String userAddress, PublicKey userPubKey) {
        // TO DO- SEND A DISCONECT SIGNAL TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST SOLUTION";
            String a = userAddress;
            BlockChain bc = null;
            Block b = null;
            String c = decrypted;
            PublicKey k = userPubKey;
            String n = userName;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] CHALLENGE RESPONSE messaje sent to " + endPoint);
        } catch (IOException ex) {
            Logger.getLogger(TCPclient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendFailedChallenge(String endPoint) {
        // TO DO- SEND A DISCONECT SIGNAL TO EVERYONE

        try {

            ObjectOutputStream oo = null;
            Socket so = null;
            try {
                so = new Socket(endPoint, 4001);
                oo = new ObjectOutputStream(so.getOutputStream());
            } catch (IOException e) {
                System.out.println("[TCP CLIENT] Problems conecting on TCP");
                System.out.println(e);
            }

            String com = "NEW HOST SOLUTION FAILED";
            String a = null;
            BlockChain bc = null;
            Block b = null;
            String c = null;
            PublicKey k = null;
            String n = null;
            byte[] e = null;
            Transaction t = null;
            List<Transaction> tl = null;
            Document d = null;

            CommandMessaje command = new CommandMessaje(com, a, bc, b, c, k, n, e, t, tl,d);
            oo.writeObject(command);
            oo.flush();
            oo.close();
            so.close();
            System.out.println("[TCP CLIENT] DISCONECT messaje sent to " + endPoint);
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
            System.out.println("[TCP CLIENT] Problems conecting on TCP");
            System.out.println(e);
        }
        oo.writeObject(CM);
        oo.flush();
        oo.close();
        s.close();
    }

}
