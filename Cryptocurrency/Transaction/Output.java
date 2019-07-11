
import java.security.PublicKey;

public class Output { // Transaction-Output

    private PublicKey receiver;
    private String id;
    private String parentId;
    private double amount;

    public Output(PublicKey receiver, String parentId, double amount) {
        this.receiver = receiver;
        this.parentId = parentId;
        this.amount = amount;
        newID();
    }

    private void newID() {
        String data = receiver.toString() + parentId + Double.toString(amount);
        this.id = Cryptography.newHash(data);
    }

    public boolean isMine(PublicKey publicKey) {
        return publicKey == receiver;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
