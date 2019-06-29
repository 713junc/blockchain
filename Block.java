
import java.util.Date;

public class Block { // Blocks are cryptographically linked together based on the hash values.

  private int id;
  private int nonce;
  private String hash;
  private String prevHash;
  private String transaction;
  private long timeStamp;

  public Block(int id, String prevHash, String transaction) {
    this.id = id;
    this.prevHash = prevHash;
    this.transaction = transaction;
    this.timeStamp = new Date().getTime();
    newHash();
  }

  public void newHash() {
    String hashData = Integer.toString(id) + prevHash + Long.toString(timeStamp) + Integer.toString(nonce) + transaction.toString();
    String hashVal = SHA256.hashing(hashData);
    this.hash = hashVal;
  }

  public String getHash() {
    return this.hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getPrevHash() {
    return this.prevHash;
  }

  public void setPrevHash(String prevHash) {
    this.prevHash = prevHash;
  }
}
