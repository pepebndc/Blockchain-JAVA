/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ServerSocketFactory;

public class Client {

    private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private boolean isConnected = false;

    public Client() {

    }

    public void communicate() {

        while (!isConnected) {
            try {
                socket = new Socket("192.168.1.140", 4444);
                System.out.println("Connected");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                Student student = new Student(1, "Bijoy");
                System.out.println("Object to be written = " + student);
                outputStream.writeObject(student);
                outputStream.flush();
                System.out.println("flushed");

            } catch (SocketException se) {
                se.printStackTrace();
// System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.communicate();
    }
}
