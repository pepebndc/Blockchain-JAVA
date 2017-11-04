/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pepeb
 */
public class BlockChain {

    private String name;
    private int diff;
    private List<Block> chain;

    BlockChain(String name) {
        this.name = name;
        this.diff = 0;
        chain = new ArrayList<>();

    }
        public boolean validateChain() throws NoSuchAlgorithmException {
        String[] hashes = new String[this.getChain().size()];
        int length = this.getChain().size();

        Iterator<Block> iterator = this.getChain().iterator();

        while (iterator.hasNext()) {

            Block b = iterator.next();

            String hash = b.getHash();
            int index = b.getIndex();
            String hashPreviousBlock = b.getLastHash();
            String time = b.getTime();
            String data = b.getData();
            int nonce = b.getNonce();

            hashes[index] = hash;
            String newHash = Block.computeHash(index, time, data, hashPreviousBlock, nonce);

            if (index != 0) {
                if (index != 1) {
                    if (index < (length - 1)) {
                        if (!hash.equals(newHash)) {
                            System.out.println("Different recomputed hash in block " + index);
                            return false;
                        }

                        if (!hashPreviousBlock.equals(hashes[(index - 1)])) {
                            System.out.println("Different previous hash in block " + index);
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the chain
     */
    public List<Block> getChain() {
        return chain;
    }

    /**
     * @param chain the chain to set
     */
    public void setChain(List<Block> chain) {
        this.chain = chain;
    }

    /**
     * @return the diff
     */
    public int getDiff() {
        return diff;
    }

    /**
     * @param diff the diff to set
     */
    public void setDiff(int diff) {
        this.diff = diff;
    }

}
