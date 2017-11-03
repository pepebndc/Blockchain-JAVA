/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.blockchain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepeb
 */
public class minarThread implements Runnable {

    private boolean minando = true;
    private BlockChain chain;
    private String data;

    public void empezarMinar() {
        minando = true;
    }

    public void terminarMinar() {
        minando = false;
    }

    public minarThread(BlockChain chain, String data) {
        this.chain = chain;
        this.data = data;
    }

    @Override
    public void run() {

        while (minando) {

            
            //VACIAR ARCHIVO DE ANTES
            PrintWriter writer = null;

            try {
                writer = new PrintWriter("contenidosBloque.txt");
                writer.println("");
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(minarThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            


            int longitud = getChain().getCadena().size();
            int index = longitud;
            int dificultad = getChain().getDificultad();
            String hashAnterior = getChain().getCadena().get(longitud - 1).getHash();
            String tiempo = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new java.util.Date());

            String[] minado = {"", ""};
            String datosFile = "";
            String dirArchivo = "contenidosBloque.txt";
            try {
                datosFile = readFile(dirArchivo);
                setData(datosFile);

            } catch (IOException ex) {
                Logger.getLogger(minarThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(datosFile);
            try {
                minado = Bloque.minarBloque(index, tiempo, dirArchivo, hashAnterior, dificultad);
            } catch (NoSuchAlgorithmException | FileNotFoundException ex) {
                Logger.getLogger(minarThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("pepe1" + index+ ex);
            } catch (IOException ex) {
                Logger.getLogger(minarThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("pepe2");
            }
            Bloque nuevo = null;
            try {
                //AÑADE EL BLOQUE A LA BLOCKCHAIN
                datosFile = readFile(dirArchivo);
                setData(datosFile);
                nuevo = new Bloque(longitud, tiempo, getData(), hashAnterior, minado[0], minado[1]);

                //GUARDA UNA COPIA DE LOS DATOS EN UN TXT DE CADA BLOQUE
                writer = new PrintWriter("contenidos/contenidosBloque" + index + ".txt");
                datosFile = readFile(dirArchivo);
                writer.println(datosFile);
                writer.close();

            } catch (NoSuchAlgorithmException | FileNotFoundException ex) {
                Logger.getLogger(minarThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(minarThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            getChain().getCadena().add(nuevo);

        }
    }

    /**
     * @return the chain
     */
    public BlockChain getChain() {
        return chain;
    }

    private static String readFile(String file) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            reader.close();
            return stringBuilder.toString();

        }catch(Exception e){
            return "";
            
        }

    }

    /**
     * @param chain the chain to set
     */
    public void setChain(BlockChain chain) {
        this.chain = chain;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

}
