
import java.util.List;
import java.util.ArrayList;

public class MerkleTreeApp {

  public static void main(String[] args){

    List<String> transactions = new ArrayList<>();
    transactions.add("blockchain");
    transactions.add("cryptocurrency");
    transactions.add("bitcoin");
    transactions.add("california");
    transactions.add("sd");
    transactions.add("TX1");
    transactions.add("TX2");

    MerkleTree merkleTree = new MerkleTree(transactions);
    System.out.println(merkleTree.getRoot().get(0));
  }
}
/*
[Result]
a115ee723598b05d14026588f8dd4a0357bf2acb0527c7f8c0504657ad313c6c
*/
