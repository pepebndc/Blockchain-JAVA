/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.security.KeyPair;
import java.security.*;
import java.util.Random;

/**
 *
 * @author pepeb
 */
public class LocalUser {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the privateKey
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey the privateKey to set
     */
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @return the publicKey
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    private static final long serialVersionUID = 5950169519310163575L;
    private String name;
    private String address;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    LocalUser(String name, String address, PublicKey publicKey, PrivateKey privateKey) throws NoSuchAlgorithmException {

        this.name = name;
        this.address = address;
        this.privateKey = privateKey;
        this.publicKey = publicKey;

    }

    public static LocalUser create(String newName) throws NoSuchAlgorithmException {

        String newAddress = "";
        KeyPairGenerator keyGen;
        KeyPair pair;
        PrivateKey newPrivate;
        PublicKey newPublic;

        //generate address
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        newAddress = sb.toString();

        //generate keys
        keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);

        pair = keyGen.generateKeyPair();
        newPrivate = pair.getPrivate();
        newPublic = pair.getPublic();

        return new LocalUser(newName, newAddress, newPublic, newPrivate);
    }
}
