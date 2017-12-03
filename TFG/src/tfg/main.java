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
import java.util.List;

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

 