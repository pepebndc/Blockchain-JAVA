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
        this.miningData = main.getTFG().getCurrentMiningContents();
    }

    @Override
    public void run() {

        while (mining) {

            main.getTFG().setCurrentMiningContents("");

            String[] mined = {"", "", ""};

            try {
                mined = Block.mineBlock();
            } catch (NoSuchAlgorithmException | FileNotFoundException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
            }
            Block B = null;
            try {
                //ADD THE BLOCK TO THE CHAIN
                int length = main.getTFG().getChain().size();
                B = new Block(length, Long.valueOf(mined[2]), main.getTFG().getCurrentMiningContents(), main.getTFG().getChain().get(length - 1).getHash(), mined[0], mined[1]);

                //CHECK FOR DIFFICULTY CHANGES NEEDED IF BLOCK IS MULTIPLE OF 10
                if (length % 10 == 0) {
                    long thisTime = Long.valueOf(mined[2]);
                    long prevBlockTime = main.getTFG().getChain().get((length - 10)).getTime();
                    long difference = thisTime - prevBlockTime;
                    int differenceInSeconds = (int) difference / 1000;
                    System.out.println("difference in seconds: " + differenceInSeconds);

                    int newDiff = main.TFG.getDiff();

                    if (differenceInSeconds > 750) {
                        newDiff = main.getTFG().getDiff() - 1;
                    }

                    if (differenceInSeconds < 450) {
                        newDiff = main.getTFG().getDiff() + 1;
                    }
                    //set the new diff
                    System.out.println("new diff after comprobation: " + newDiff);
                    main.getTFG().setDiff(newDiff);

                    //notify the network about the new difficulty
                    Iterator<String> it = main.getTFG().getHosts().iterator();
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
                }//end of difficulty changes

                //EVERY MINED BLOCK:add to our blockchain and empty mining contents
                main.getTFG().getChain().add(B);
                main.getTFG().setCurrentMiningContents("");

                //notify the network about the new block and the new (empty) mining contents
                Iterator<String> it = main.getTFG().getHosts().iterator();
                while (it.hasNext()) {
                    String host = it.next();
                    try {
                        if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                            TCPclient.sendNewBlock(host, B);
                            TCPclient.sendNewContent(host);
                        }
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(manage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(mine.class.getName()).log(Level.SEVERE, null, ex);
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

    public void endMining() {
        mining = false;
    }

}
