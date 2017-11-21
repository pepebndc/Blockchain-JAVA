/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.Serializable;
import java.security.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class User implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private String name;
    private String address;
    private PublicKey publicKey;

    User(String name, String address, PublicKey publicKey) throws NoSuchAlgorithmException {

        this.name = name;
        this.address = address;
        this.publicKey = publicKey;

    }


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
}
