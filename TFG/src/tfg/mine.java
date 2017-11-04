/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepeb
 */
public class mine implements Runnable {

    private boolean mining = true;
    private BlockChain chain;
    private String data;

    public mine(BlockChain chain, String data) {
        this.chain = chain;
        this.data = data;
    }

    @Override
    public void run() {

        while (mining) {

            //VACIAR ARCHIVO DE ANTES
            PrintWriter writer = null;

            try {
                writer = new PrintWriter("contentsBLOCK.txt");
                writer.println("");
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
            }

            int length = getChain().getChain().size();
            int index = length;
            int diff = getChain().getDiff();
            String lastHash = getChain().getChain().get(length - 1).getHash();
            String time = main.currentTime();

            String[] mined = {"", ""};
            String fileData = "";
            String fileDir = "contentsBLOCK.txt";
            try {
                fileData = main.readFile(fileDir);
                setData(fileData);

            } catch (IOException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(fileData);
            try {
                mined = Block.mineBlock(index, time, fileDir, lastHash, diff);
            } catch (NoSuchAlgorithmException | FileNotFoundException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("pepe1" + index + ex);
            } catch (IOException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("pepe2");
            }
            Block B = null;
            try {
                //ADD THE BLOCK TO THE CHAIN
                fileData = main.readFile(fileDir);
                setData(fileData);
                B = new Block(length, time, getData(), lastHash, mined[0], mined[1]);

                //SAVE A COPY OF THE CONTENTS OF EACH BLOCK
                writer = new PrintWriter("contents/BLOCK" + index + ".txt");
                fileData = main.readFile(fileDir);
                writer.println(fileData);
                writer.close();

            } catch (NoSuchAlgorithmException | FileNotFoundException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
            }

            getChain().getChain().add(B);

        }
    }

    /**
     * @return the chain
     */
    public BlockChain getChain() {
        return chain;
    }

    /**
     * @param chain the chain to set
     */
    public void setChain(BlockChain chain) {
        this.chain = chain;
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

    public void startMining() {
        mining = true;
    }

    public void terminarMining() {
        mining = false;
    }

}
