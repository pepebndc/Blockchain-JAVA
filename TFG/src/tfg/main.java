/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 *
 * @author pepeb
 */
public class main {

    public static BlockChain TFG;

    public static void main() throws NoSuchAlgorithmException, IOException {

        //Creating the BlockChain object
        TFG = new BlockChain("Blockchain of my TFG");
        TFG.setDiff(4);

        //Manually creating 2 genesis blocks
        Block genesis1 = new Block(0, currentTime(), "genesis1", "0", "0", "0");
        TFG.getChain().add(genesis1);
        Block genesis2 = new Block(1, currentTime(), "genesis2", "0", "0", "0");
        TFG.getChain().add(genesis2);
    }

    public static void newBlock(BlockChain chain, String data) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        int length = chain.getChain().size();
        int index = length;
        int diff = chain.getDiff();
        String lastHash = chain.getChain().get(length - 1).getHash();
        String time = currentTime();

        String[] mined = Block.mineBlock(index, time, data, lastHash, diff);
        Block b = new Block(length, time, data, lastHash, mined[0], mined[1]);

        chain.getChain().add(b);

    }

    public static String currentTime() {

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new java.util.Date());
        return timeStamp;
    }

    //prints out the contents of the blockchain on the command line
    static void showBlockchain(BlockChain BC) {
        Iterator<Block> iterator = BC.getChain().iterator();
        while (iterator.hasNext()) {
            Block b = iterator.next();
            String hash = b.getHash();
            int index = b.getIndex();
            String data = b.getData();
            String lastHash = b.getLastHash();
            int nonce = b.getNonce();

            System.out.println("Block" + index);
            System.out.println("Data =" + data);
            System.out.println("Hash =" + hash);
            System.out.println("Last Hash=" + lastHash);
            System.out.println("Nonce=" + nonce);

            System.out.println("  ");

        }
    }

    public static String readFile(String file) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            reader.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            return "";

        }
    }
}
