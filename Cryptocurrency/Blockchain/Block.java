
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Block {    
    private int id;
    private int nonce;
    private String hash;
    private String previousHash;
    public List<Transaction> transactions;
    private long timeStamp;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.transactions = new ArrayList<Transaction>();
        this.timeStamp = new Date().getTime();
        newHash(); 
    }
    
    public void newHash() {
        String hashData = Integer.toString(id) + previousHash + Long.toString(timeStamp) + transactions.toString() + Integer.toString(nonce);
        String hashVal = Cryptography.newHash(hashData);
        this.hash = hashVal;
    }

    public void incrementNonce() {
        this.nonce++;
    }
        
    public boolean addTransaction(Transaction transaction) {
        if(transaction == null) return false;
        
        if((!previousHash.equals(Constants.GENESIS_PREV_HASH))) {
            if((!transaction.verifyTransaction())) return false;
        }
        transactions.add(transaction);
        System.out.println("Transaction is succesfully added to the block " + this);
        return true;
    }

    public String getHash() {
        return this.hash;
    }
}
