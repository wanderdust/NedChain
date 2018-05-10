package com.company;

import com.google.gson.GsonBuilder;
import java.util.*;

public class NedChain {

    public static List <Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 6;

    public static void main(String[] args) {
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).getHash()));
        System.out.println("Trying to Mine block 2... ");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).getHash()));
        System.out.println("Trying to Mine block 3... ");
        blockChain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isValid());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockChainJson);
    }

    public static boolean isValid () {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < NedChain.blockChain.size(); i++) {
            currentBlock = NedChain.blockChain.get(i);
            previousBlock = NedChain.blockChain.get(i - 1);

            //Compare the registered hash and the calculated hash.
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes are not equal");
                return false;
            }

            //Compare previous hash and registered previous hash.
            if (!previousBlock.getHash().equals(previousBlock.calculateHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            //Check that the hash has been mined.
            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
