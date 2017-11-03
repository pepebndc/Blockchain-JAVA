/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.blockchain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author pepeb
 */
public class Bloque {

    /**
     *
     * @param index = número de bloque
     * @param tiempo cuando se ha creado
     * @param data String de datos del bloque
     * @param hashAnterior Hash del bloque anterior
     */
    private int index;
    private String tiempo;
    private String data;
    private String hashAnterior;
    private String hash;
    private String relleno;

    Bloque(int i, String t, String d, String hA, String h, String r) throws NoSuchAlgorithmException {

        index = i;
        tiempo = t;
        data = d;
        hashAnterior = hA;
        hash = h;
        relleno = r;

    }

    @SuppressWarnings("empty-statement")
    public void main(int index, String tiempo, String data, String hashAnterior) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        hashAnterior = "";
        this.setIndex(index);
        this.setTiempo(tiempo);
        this.setData(data);
        this.setHashAnterior(hashAnterior);

        this.setHash(calcularHash(index, main.tiempoActual(), data, hashAnterior, getRelleno()));

        System.out.println(getHash());

    }

    static String[] minarBloque(int index, String tiempo, String data, String hashAnterior, int dificultad) throws NoSuchAlgorithmException, FileNotFoundException, IOException {

        //crear una string todo '0' con longitud dificultad
        StringBuilder sb = new StringBuilder(18);
        for (int i = 0; i < dificultad; i++) {
            sb.append("0");
        }
        String codigo = sb.toString();
        String hash = "string inicial";
        int rellenoInt = (int) (Math.random() * 1000000000);
        String datosFile = "";
        int i;
        //ir cambiando el relleno hasta conseguir el objetivo de dificultad
        while (!hash.startsWith(codigo)) {

            datosFile = readFile("contenidosBloque.txt");

            int t = 0;
            while (!hash.startsWith(codigo) && t != 10) {
                rellenoInt++;
                hash = calcularHash(index, tiempo, datosFile, hashAnterior, rellenoInt);
                t++;
                //System.out.println(datosFile+"    "+t+"   "+hash);
            }

            //System.out.println("el relleno del bloque "+index+"= " + rellenoInt);
        }

        System.out.println("bloque " + index + " ha sido minado--> " + hash + " //contenidos: " + datosFile);

        String rellenoString = String.valueOf(rellenoInt);

        return new String[]{hash, rellenoString};

    }

    static String calcularHash(int index, String tiempo, String data, String hashAnterior, int rellenoInt) throws NoSuchAlgorithmException {

        String plaintext;

        plaintext = index + tiempo + data + hashAnterior + Integer.toString(rellenoInt);
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

    static String comprobarHash(int index, String tiempo, String data, String hashAnterior, int rellenoInt) throws NoSuchAlgorithmException {

        String plaintext;

        plaintext = index + tiempo + data + hashAnterior + Integer.toString(rellenoInt);
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

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the tiempo
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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

    /**
     * @return the hashAnterior
     */
    public String getHashAnterior() {
        return hashAnterior;
    }

    /**
     * @param hashAnterior the hashAnterior to set
     */
    public void setHashAnterior(String hashAnterior) {
        this.hashAnterior = hashAnterior;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the relleno
     */
    public int getRelleno() {
        return Integer.parseInt(relleno);
    }

    /**
     * @param relleno the relleno to set
     */
    public void setRelleno(int relleno) {
        this.relleno = String.valueOf(relleno);
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
}
