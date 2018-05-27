package es.upv.joblanu1.blockchain;

/**
 *
 * @author pepeb
 */
public class Document {

    private static long serialVersionUID = 7526472295622776147L;

    private String name;
    private String [] owner;
    private String hash;

    Document (String name, String owner, String hash){
        this.hash=hash;
        this.name=name;
        this.owner[0]=owner;
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
     * @return the owner
     */
    public String[] getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String[] owner) {
        this.owner = owner;
    }


    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}
