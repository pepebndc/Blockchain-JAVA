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
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pepeb
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private String ID;
    private String UserCreator;
    private String UserReciever;
    private byte[] EncryptedContents;
    private long date;
    private int type;

    Transaction(String id, String creator, String reciever, byte[] contents, long date, int type) {

        this.ID = id;
        this.UserCreator = creator;
        this.UserReciever = reciever;
        this.EncryptedContents = contents;
        this.date = date;
        this.type = type;
    }

    public static String transactionToString(Transaction t) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(t);
        return new String(out.toByteArray(), StandardCharsets.UTF_8);

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
     * @return the UserReciever
     */
    public String getUserReciever() {
        return UserReciever;
    }

    /**
     * @param UserReciever the UserReciever to set
     */
    public void setUserReciever(String UserReciever) {
        this.UserReciever = UserReciever;
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
}
