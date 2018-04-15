/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

/**
 *
 * @author pepeb
 */
public class main {

    static BlockChain TFG;
    private static LocalUser localUser;
    static String challengeStored;
    private static boolean mining;

    public static void main() throws NoSuchAlgorithmException, IOException {

    }

    public static String currentTime() {

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new java.util.Date());
        return timeStamp;
    }

    /**
     * @return the TFG
     */
    public static BlockChain getTFG() {
        return TFG;
    }

    /**
     * @param aTFG the TFG to set
     */
    public static void setTFG(BlockChain aTFG) {
        TFG = aTFG;
    }

    /**
     * @return the localUser
     */
    public static LocalUser getLocalUser() {
        return localUser;
    }

    /**
     * @param aLocalUser the localUser to set
     */
    public static void setLocalUser(LocalUser aLocalUser) {
        localUser = aLocalUser;
    }

    /**
     * @return the mining
     */
    public static boolean isMining() {
        return mining;
    }

    /**
     * @param aMining the mining to set
     */
    public static void setMining(boolean aMining) {
        mining = aMining;
    }

    public static String findHash(String plaintext) throws UnsupportedEncodingException {
        String hashText = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(plaintext.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashText = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashText;
    }

    public static String BytesToHash(byte[] file) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(file);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hashText = sb.toString();
        return hashText;
    }

}
