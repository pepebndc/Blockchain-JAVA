/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pepeb
 */
public class BlockChain implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private String name;
    private int diff;
    private List<Block> chain;
    private String currentMiningContents;
    private List<String> hosts;
    private List<User> users;
    
    BlockChain(String name) {
        this.name = name;
        this.diff = 5; //avoid an extreme amount of blocks mined
        this.chain = new ArrayList<>();
        this.currentMiningContents = "";
        this.hosts = new ArrayList<>();
        this.users = new ArrayList<>();

    }

    public boolean validateChain() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] hashes = new String[this.getChain().size()];
        int length = this.getChain().size();

        Iterator<Block> iterator = this.getChain().iterator();

        while (iterator.hasNext()) {

            Block b = iterator.next();

            String hash = b.getHash();
            int index = b.getIndex();
            String hashPreviousBlock = b.getLastHash();
            long time = b.getTime();
            String data = b.getData();
            int nonce = b.getNonce();

            hashes[index] = hash;
            String newHash = Block.computeHash(index, time, data, hashPreviousBlock, nonce);

            if (index != 0) {
                if (index != 1) {
                    if (index < (length - 1)) {
                        if (!hash.equals(newHash)) {
                            System.out.println("Different recomputed hash in block " + (index - 1));
                            return false;
                        }

                        if (!hashPreviousBlock.equals(hashes[(index - 1)])) {
                            System.out.println("Different previous hash in block " + (index - 1));
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

    /**
     * @return the currentMiningContents
     */
    public String getCurrentMiningContents() {
        return currentMiningContents;
    }

    /**
     * @param currentMiningContents the currentMiningContents to set
     */
    public void setCurrentMiningContents(String currentMiningContents) {
        this.currentMiningContents = currentMiningContents;
    }

    /**
     * @return the hosts
     */
    public List<String> getHosts() {
        return hosts;
    }

    /**
     * @param hosts the hosts to set
     */
    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
