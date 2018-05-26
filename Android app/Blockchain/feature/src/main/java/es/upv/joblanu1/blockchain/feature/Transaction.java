package es.upv.joblanu1.blockchain.feature;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.Compiler.command;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author pepeb
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private String ID;

    private String Contents;
    private List <String> users;
    private List <byte[]> signatures;
    private long date;
    private int type;

    Transaction(String id, List <String> users, String contents, List <byte[]> signatures, long date, int type) {

        this.ID = id;
        this.users = users;
        this.Contents = contents;
        this.signatures=signatures;
        this.date = date;
        this.type = type;
    }

    public static String transactionToString(Transaction t) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(t);
        return new String(out.toByteArray(), StandardCharsets.UTF_8);

    }

    public static byte[] decryptSHA(byte[] contentsToDecrypt, PublicKey pubKey, PrivateKey privKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher decrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        if (pubKey==null) {
            System.out.println("detected private key");
            decrypt.init(Cipher.DECRYPT_MODE, privKey);
        } else {
            System.out.println("detected public key");
            decrypt.init(Cipher.DECRYPT_MODE, pubKey);
        }

        return decrypt.doFinal(contentsToDecrypt);
    }

    public static byte[] encryptSHA(byte[] contentsToDecrypt, PublicKey pubKey, PrivateKey privKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher encrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        System.out.println("encrypting with SHA, public key: "+pubKey);
        System.out.println("private key: "+privKey);
        if (pubKey==null) {
            System.out.println("detected private key");
            encrypt.init(Cipher.ENCRYPT_MODE, privKey);
        } else {
            System.out.println("detected public key");
            encrypt.init(Cipher.ENCRYPT_MODE, pubKey);
        }

        return encrypt.doFinal(contentsToDecrypt);
    }

    public static byte[] encryptAES(byte[] contentsToEncrypt, SecretKey AESkey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encrypt.init(Cipher.ENCRYPT_MODE, AESkey, new IvParameterSpec(new byte[16]));
        return encrypt.doFinal(contentsToEncrypt);
    }

    public static byte[] decryptAES(byte[] contentsToDecrypt, SecretKey AESkey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encrypt.init(Cipher.DECRYPT_MODE, AESkey, new IvParameterSpec(new byte[16]));
        return encrypt.doFinal(contentsToDecrypt);
    }

    public static String transactionListToString(List<Transaction> t) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(t);
        return new String(out.toByteArray(), StandardCharsets.UTF_8);

    }

    public static String formatedTransactionList(List<Transaction> t) {
        Iterator<Transaction> it = t.iterator();
        String formatedString = "List of transactions:";
        while (it.hasNext()) {
            formatedString = formatedString + "\n ----------- \n" + it.next().getID();
        }
        return formatedString;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the UserCreator
     */


    /**
     * @return the EncryptedContents
     */
    public String getContents() {
        return Contents;
    }

    /**
     * @param EncryptedContents the EncryptedContents to set
     */
    public void setContents(String EncryptedContents) {
        this.Contents = EncryptedContents;
    }

    /**
     * @return the date
     */
    public long getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the signatures
     */
    public List <byte[]> getSignatures() {
        return signatures;
    }

    /**
     * @param signatures the signatures to set
     */
    public void setSignatures(List <byte[]> signatures) {
        this.signatures = signatures;
    }

    /**
     * @return the users
     */
    public List <String> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List <String> users) {
        this.users = users;
    }


}

