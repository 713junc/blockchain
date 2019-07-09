
import java.util.List;
import java.util.ArrayList;

public class MerkleTree {

	private List<String> transactions;

	public MerkleTree(List<String> transactions) {
		this.transactions = transactions;
	}

	public List<String> getRoot() {
		return buildMerkleTree(this.transactions);
	}

	private String hashPair(String hashA, String hashB) {
		String concatPair = hashA + hashB;
		String result = SHA256.hashing(concatPair);
		return result;
	}

	private List<String> buildMerkleTree(List<String> transactions){
		if(transactions.size() == 1) return transactions;

		List<String> temp = new ArrayList<>();
		for(int i=0; i<transactions.size()-1; i+=2){
			String hashA = transactions.get(i), hashB = transactions.get(i+1);
			temp.add(hashPair(hashA, hashB));
		}

		if(transactions.size()%2 == 1){ // last transaction is hashed with itself.
			String lastTransaction = transactions.get(transactions.size()-1);
			temp.add(hashPair(lastTransaction, lastTransaction));
		}
		return buildMerkleTree(temp);
	}
}
