package es.upv.joblanu1.blockchain;


import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.security.PublicKey;
import java.util.List;

/**
 *
 * @author pepeb
 */
public class CommandMessaje implements Serializable {

    private static long serialVersionUID = 7526472295622776147L;

    private String command;
    private String address;
    private BlockChain blockchain;
    private Block block;
    private String contents;
    private PublicKey pubKey;
    private String name;
    private byte [] encoded;
    private Transaction transaction;
    private List <Transaction> transactionList;
    private Document document;


    CommandMessaje(String command, String address, BlockChain BC, Block B, String contents, PublicKey pubKey, String name, byte[] encoded, Transaction transaction,List <Transaction> transactionList, Document doc ) {
        this.command = command;
        this.address = address;
        this.blockchain = BC;
        this.block = B;
        this.contents = contents;
        this.pubKey = pubKey;
        this.name=name;
        this.encoded=encoded;
        this.transaction=transaction;
        this.transactionList=transactionList;
        this.document=doc;
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

    /**
     * @return the encoded
     */
    public byte[] getEncoded() {
        return encoded;
    }

    /**
     * @param encoded the encoded to set
     */
    public void setEncoded(byte[] encoded) {
        this.setEncoded(encoded);
    }



    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the transactionList
     */
    public List <Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * @param transactionList the transactionList to set
     */
    public void setTransactionList(List <Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }



}
