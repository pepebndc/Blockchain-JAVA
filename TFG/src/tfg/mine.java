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
    private String miningData;

    public mine(BlockChain chain) {
        this.chain = chain;
        this.miningData = main.TFG.getCurrentMiningContents();
    }

    @Override
    public void run() {

        while (mining) {

            //VACIAR ARCHIVO DE ANTES
            PrintWriter writer = null;

            main.TFG.setCurrentMiningContents("");

            int length = getChain().getChain().size();
            int index = length;
            int diff = getChain().getDiff();
            String lastHash = getChain().getChain().get(length - 1).getHash();
            String time = main.currentTime();

            String[] mined = {"", ""};


            try {
                mined = Block.mineBlock(index, time, miningData, lastHash, diff);
            } catch (NoSuchAlgorithmException | FileNotFoundException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("pepe1" + index + ex);
            } catch (IOException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("pepe2");
            }
            Block B = null;
            //ADD THE BLOCK TO THE CHAIN
            try {
                B = new Block(length, time, main.TFG.getCurrentMiningContents(), lastHash, mined[0], mined[1]);
            } catch (NoSuchAlgorithmException ex) {
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
     * @return the miningData
     */
    public String getMiningData() {
        return miningData;
    }

    /**
     * @param miningData the miningData to set
     */
    public void setMiningData(String miningData) {
        this.miningData = miningData;
    }

    public void startMining() {
        mining = true;
    }

    public void terminarMining() {
        mining = false;
    }

}
