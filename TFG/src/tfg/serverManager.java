/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class serverManager implements Runnable {

    private TCPserver session[];

    serverManager() {
        int clients = 256;
        session = new TCPserver[clients];
    }

    public static void main(String args[]) throws Exception {
    }

    @Override
    public void run() {
        ServerSocket serv = null;
        int clients = 256;
        int i = 0;
        try {
            serv = new ServerSocket(4001);
        } catch (IOException ex) {
            Logger.getLogger(serverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            do {

                Socket s = new Socket();
                s = serv.accept();
                session[i] = new TCPserver(s);
                session[i].start();
                i++;
                if (i == clients) {
                    i = 0;
                }

            } while (i != clients + 1);
            serv.close();
        } catch (IOException ex) {
            Logger.getLogger(serverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // fin main
} // fin clase servidor
