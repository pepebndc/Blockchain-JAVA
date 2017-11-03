/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.blockchain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

        // creación de bloques
/**
 *
 * @author pepeb
 */
public class main {
    
   public static BlockChain TEST;
   
    
    
    public static void main() throws NoSuchAlgorithmException, IOException {
        
        
        //creación del objeto blockchain
        TEST = new BlockChain("test de blockchain");
        TEST.setDificultad(4);
        //CREACIÓN MANUAL DEL BLOQUE GÉNESIS
        Bloque genesis1 = new Bloque(0, tiempoActual(), "genesis1", "0", "0", "0");
        TEST.getCadena().add(genesis1);
        Bloque genesis2 = new Bloque(1, tiempoActual(), "genesis2", "0", "0", "0");
        TEST.getCadena().add(genesis2);
        //mostrar datos Blockchain
        //verBlockchain(TEST);
        //comprobar validez
        /*
        System.out.println("la cadena es válida:");
        if (TEST.cadenaValida()) {
            System.out.println("SÍ");
        } else {
            System.out.println("NO");
        }
        */
        
        //comprobar validez después de tocar cosas
        /*TEST.getCadena().get(2).setData("mucho dinero");
        
        String hashbloque2 = calcularHash(2, TEST.getCadena().get(2).getTiempo(), "mucho dinero", TEST.getCadena().get(2).getHashAnterior(), TEST.getCadena().get(2).getRelleno());
        TEST.getCadena().get(2).setHash(hashbloque2);
        
        System.out.println("la cadena es válida despues de modificar cosas:");
        if (TEST.cadenaValida()) {
        System.out.println("SÍ");
        } else {
        System.out.println("NO");
        }
        */

    }
    


    public static void nuevoBloque(BlockChain chain, String data) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        int longitud = chain.getCadena().size();
        int index = longitud;
        int dificultad = chain.getDificultad();
        String hashAnterior = chain.getCadena().get(longitud - 1).getHash();
        String tiempo = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new java.util.Date());

        String[] minado = Bloque.minarBloque(index, tiempo, data, hashAnterior, dificultad);
        Bloque nuevo = new Bloque(longitud, tiempo, data, hashAnterior, minado[0], minado[1]);

        chain.getCadena().add(nuevo);

    }

    public static String tiempoActual() {

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new java.util.Date());
        return timeStamp;
    }

    static String calcularHash(int index, String tiempo, String data, String hashAnterior, int relleno) throws NoSuchAlgorithmException {

        String plaintext;
        plaintext = index + tiempo + data + hashAnterior + relleno;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);

        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }

    static void verBlockchain(BlockChain nombre) {
        Iterator<Bloque> iterator = nombre.getCadena().iterator();
        while (iterator.hasNext()) {
            Bloque b = iterator.next();
            String hashprint = b.getHash();
            int indexprint = b.getIndex();
            String datosprint = b.getData();
            String prevhashprint = b.getHashAnterior();
            int relleno = b.getRelleno();

            System.out.println("Bloque" + indexprint);
            System.out.println("datos =" + datosprint);
            System.out.println("Hash =" + hashprint);
            System.out.println("Hash Anterior=" + prevhashprint);
            System.out.println("Relleno=" + relleno);

            System.out.println("  ");

        }
    }

}
