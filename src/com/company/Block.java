package com.company;

import java.util.Date;

public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block (String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash () {
        return StringUtil.applySha256(
                this.previousHash + Long.toString(this.timeStamp) + Integer.toString(nonce) + this.data
        );
    }

    public String getHash () {
        return this.hash;
    }

    public void mineBlock (int difficulty) {
        String target = new String(new char[difficulty]).replace('\u0000', '0');

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
