/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author pepeb
 */
public class Block {


    private int index;
    private String time;
    private String data;
    private String lastHash;
    private String hash;
    private String nonce;

    Block(int index, String time, String data, String lastHash, String hash, String nonce) throws NoSuchAlgorithmException {

        this.index = index;
        this.time = time;
        this.data = data;
        this.lastHash = lastHash;
        this.hash = hash;
        this.nonce = nonce;

    }

    static String[] mineBlock(int index, String time, String data, String lastHash, int diff) throws NoSuchAlgorithmException, FileNotFoundException, IOException {

        //Create an al '0's String to set difficulty
        StringBuilder sb = new StringBuilder(18);
        for (int i = 0; i < diff; i++) {
            sb.append("0");
        }
        String code = sb.toString();
        String hash = "hi";
        int nonceInt = (int) (Math.random() * 1000000000);
        String fileData = "";

        
        //Changing the nonce to match difficulty
        while (!hash.startsWith(code)) {

            fileData = main.readFile("contentsBLOCK.txt");

            int t = 0;
            while (!hash.startsWith(code) && t != 10) {
                nonceInt++;
                hash = computeHash(index, time, fileData, lastHash, nonceInt);
                t++;

            }

        }

        
        System.out.println("Block " + index + " has been mined--> " + hash + " //CONTENTS: " + fileData);

        String nonceString = String.valueOf(nonceInt);

        return new String[]{hash, nonceString};

    }

    static String computeHash(int index, String time, String data, String lastHash, int nonceInt) throws NoSuchAlgorithmException {

        String plaintext;

        plaintext = index + time + data + lastHash + Integer.toString(nonceInt);
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
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String tiempo) {
        this.time = tiempo;
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
     * @return the lastHash
     */
    public String getLastHash() {
        return lastHash;
    }

    /**
     * @param lastHash the lastHash to set
     */
    public void setLastHash(String lastHash) {
        this.lastHash = lastHash;
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
     * @return the nonce
     */
    public int getNonce() {
        return Integer.parseInt(nonce);
    }

    /**
     * @param nonce the nonce to set
     */
    public void setNonce(int nonce) {
        this.nonce = String.valueOf(nonce);
    }

}