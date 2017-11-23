/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.security.PublicKey;

/**
 *
 * @author pepeb
 */
public class CommandMessaje implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private String command;
    private String address;
    private BlockChain blockchain;
    private Block block;
    private String contents;
    private PublicKey pubKey;
    private String name;

    CommandMessaje(String command, String address, BlockChain BC, Block B, String contents, PublicKey pubKey, String name) {
        this.command = command;
        this.address = address;
        this.blockchain = BC;
        this.block = B;
        this.contents = contents;
        this.pubKey = pubKey;
        this.name=name;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * @param contents the contents to set
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * @return the blockchain
     */
    public BlockChain getBlockchain() {
        return blockchain;
    }

    /**
     * @param blockchain the blockchain to set
     */
    public void setBlockchain(BlockChain blockchain) {
        this.blockchain = blockchain;
    }

    /**
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * @return the pubKey
     */
    public PublicKey getPubKey() {
        return pubKey;
    }

    /**
     * @param pubKey the pubKey to set
     */
    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
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



}
