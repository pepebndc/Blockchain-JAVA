package es.upv.joblanu1.blockchain.feature;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 *
 * @author pepeb
 */
public class Block implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private int index;
    private long time;
    private List<Transaction> data;
    private String lastHash;
    private String hash;
    private String nonce;

    Block(int index, long time, List<Transaction> data, String lastHash, String hash, String nonce) throws NoSuchAlgorithmException {

        this.index = index;
        this.time = time;
        this.data = data;
        this.lastHash = lastHash;
        this.hash = hash;
        this.nonce = nonce;

    }


    static String computeHash(int index, long time, String data, String lastHash, int nonceInt) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String plaintext = "";
        String hashtext = "";
        plaintext = index + time + data + lastHash + Integer.toString(nonceInt);

        /* MD5
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
         */
        //SHA-512
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(plaintext.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashtext = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
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
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * @return the data
     */
    public List<Transaction> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<Transaction> data) {
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
