/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
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

            String[] mined = {"", ""};

            try {
                mined = Block.mineBlock();
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
                B = new Block(length, main.currentTime(), main.TFG.getCurrentMiningContents(), main.TFG.getChain().get(index - 1).getHash(), mined[0], mined[1]);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
            }

            //add to our blockchain
            getChain().getChain().add(B);

            //notify the network
            Iterator<String> it = main.TFG.getHosts().iterator();
            while (it.hasNext()) {
                String host = it.next();
                try {
                    if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                        TCPclient.sendNewBlock(host, B);
                    }
                } catch (UnknownHostException ex) {
                    Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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
