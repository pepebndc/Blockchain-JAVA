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

            String[] mined = {"", "", ""};

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
                B = new Block(length, Long.valueOf(mined[2]), main.TFG.getCurrentMiningContents(), main.TFG.getChain().get(index - 1).getHash(), mined[0], mined[1]);
                //CHECK FOR DIFFICULTY CHANGES NEEDED

                long thisTime = Long.valueOf(mined[2]);
                long prevBlockTime = main.TFG.getChain().get((length - 10)).getTime();
                long difference = thisTime - prevBlockTime;
                int differenceInSeconds = (int) difference / 1000;

                double computation = main.TFG.getDiff() * Math.pow(600, 2) / (Math.pow(differenceInSeconds, 2));
                int newDiff = (int) Math.round(computation);

                if (main.TFG.getDiff() != newDiff) {
                    //set the new diff
                    main.TFG.setDiff(newDiff);
                    //notify the network about the change
                    Iterator<String> it = main.TFG.getHosts().iterator();
                    while (it.hasNext()) {
                        String host = it.next();
                        try {
                            if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                                TCPclient.sendNewDiff(host);
                            }
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(manage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

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
