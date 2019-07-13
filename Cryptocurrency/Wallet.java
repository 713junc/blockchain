
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Wallet {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Wallet() {
        KeyPair keyPair = Cryptography.ellipticCurveCrypto();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public double calculateBalance() {
        double balance = 0;
        for (Map.Entry<String, Output> item : BlockChain.UTXOs.entrySet()){
            Output transactionOutput = item.getValue();
            if(transactionOutput.isMine(publicKey)) {
                balance += transactionOutput.getAmount() ;
            }
        }
        return balance;
    }

    public Transaction transferMoney(PublicKey receiver, double amount) {
        if(calculateBalance() < amount) return null;

        List<Input> inputs = new ArrayList<Input>();
        for (Map.Entry<String, Output> item : BlockChain.UTXOs.entrySet()) {
            Output UTXO = item.getValue();
            if(UTXO.isMine(this.publicKey)) {
                inputs.add(new Input(UTXO.getId()));
            } 
        }
        Transaction newTransaction = new Transaction(publicKey, receiver , inputs, amount);
        newTransaction.newSignature(privateKey);
        return newTransaction;
    }
}
