/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 *
 * @author pepeb
 */
public class main {

    static BlockChain TFG;
    private static LocalUser localUser;
    static String challengeStored;

    public static void main() throws NoSuchAlgorithmException, IOException {

    }

    public static String currentTime() {

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new java.util.Date());
        return timeStamp;
    }

    //prints out the contents of the blockchain on the command line
    static void showBlockchain(BlockChain BC) {
        Iterator<Block> iterator = BC.getChain().iterator();
        while (iterator.hasNext()) {
            Block b = iterator.next();
            String hash = b.getHash();
            int index = b.getIndex();
            String data = b.getData();
            String lastHash = b.getLastHash();
            int nonce = b.getNonce();

            System.out.println("Block" + index);
            System.out.println("Data =" + data);
            System.out.println("Hash =" + hash);
            System.out.println("Last Hash=" + lastHash);
            System.out.println("Nonce=" + nonce);

            System.out.println("  ");

        }
    }

    public static String readFile(String file) throws IOException {
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

        } catch (Exception e) {
            return "";

        }
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
}

 