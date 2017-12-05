/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

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
    private String UserCreator;
    private String UserReceiver;
    private byte[] EncryptedContents;
    private byte[] EncryptedContentsAgreed;
    private byte[] AESkeySent;
    private byte[] AESkeyAgreed;
    private long date;
    private int type;

    Transaction(String id, String creator, String Receiver, byte[] contents, byte[] contentsAgreed, byte[] AESkeySent, byte[] AESkeyAgreed, long date, int type) {

        this.ID = id;
        this.UserCreator = creator;
        this.UserReceiver = Receiver;
        this.EncryptedContents = contents;
        this.EncryptedContentsAgreed = contentsAgreed;
        this.AESkeyAgreed = AESkeyAgreed;
        this.AESkeySent = AESkeySent;
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
    public String getUserCreator() {
        return UserCreator;
    }

    /**
     * @param UserCreator the UserCreator to set
     */
    public void setUserCreator(String UserCreator) {
        this.UserCreator = UserCreator;
    }

    /**
     * @return the UserReceiver
     */
    public String getUserReceiver() {
        return UserReceiver;
    }

    /**
     * @param UserReceiver the UserReceiver to set
     */
    public void setUserReceiver(String UserReceiver) {
        this.UserReceiver = UserReceiver;
    }

    /**
     * @return the EncryptedContents
     */
    public byte[] getEncryptedContents() {
        return EncryptedContents;
    }

    /**
     * @param EncryptedContents the EncryptedContents to set
     */
    public void setEncryptedContents(byte[] EncryptedContents) {
        this.EncryptedContents = EncryptedContents;
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
     * @return the EncryptedContentsAgreed
     */
    public byte[] getEncryptedContentsAgreed() {
        return EncryptedContentsAgreed;
    }

    /**
     * @param EncryptedContentsAgreed the EncryptedContentsAgreed to set
     */
    public void setEncryptedContentsAgreed(byte[] EncryptedContentsAgreed) {
        this.EncryptedContentsAgreed = EncryptedContentsAgreed;
    }

    /**
     * @return the AESkeySent
     */
    public byte[] getAESkeySent() {
        return AESkeySent;
    }

    /**
     * @param AESkeySent the AESkeySent to set
     */
    public void setAESkeySent(byte[] AESkeySent) {
        this.AESkeySent = AESkeySent;
    }

    /**
     * @return the AESkeyAgreed
     */
    public byte[] getAESkeyAgreed() {
        return AESkeyAgreed;
    }

    /**
     * @param AESkeyAgreed the AESkeyAgreed to set
     */
    public void setAESkeyAgreed(byte[] AESkeyAgreed) {
        this.AESkeyAgreed = AESkeyAgreed;
    }
}
