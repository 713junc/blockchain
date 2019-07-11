
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.util.ArrayList;


public class Transaction {

  private PublicKey sender;
	private PublicKey receiver;

  public List<Input> inputs;
	public List<Output> outputs;
	private String transactionId;
	private double amount;
	private byte[] signature;


	public Transaction(PublicKey sender, PublicKey receiver, List<TransactionInput> inputs, double amount) {
    this.sender = sender;
		this.receiver = receiver;
		this.inputs = new ArrayList<Input>();
		this.outputs = new ArrayList<Output>();
    this.amount = amount;
		this.inputs = inputs;
		newHash();
	}

  private void newHash() {
    String data = sender.toString() + receiver.toString() + Double.toString(amount);
    this.transactionId = Cryptography.newHash(data);
  }

  public void newSignature(PrivateKey privateKey) {
    String data = sender.toString() + receiver.toString() + Double.toString(amount);
    signature = Cryptography.applyECDSASignature(privateKey,data);
  }

  public boolean verifySignature() {
    String data = sender.toString() + receiver.toString() + Double.toString(amount);
    return Cryptography.verifyECDSASignature(sender, data, signature);
  }

  public double getInputsAmountSum() {
    double sum = 0;
    for(Input input : inputs) {
      if(input.getUTXO() != null){
        sum += input.getUTXO().getAmount();
      }
    }
    return sum;
  }

	public boolean verifyTransaction() {
		if(!verifySignature()) {
			System.out.println("Invalid Transaction, please check your transaction");
			return false;
		}

		for(Input input : inputs) {
			input.setUTXO(BlockChain.UTXOs.get(input.getOutputId()));
		}

		outputs.add(new Output(this.receiver, transactionId, amount));
		outputs.add(new Output(this.sender, transactionId, getInputsAmountSum() - amount));

		for(Output output : outputs) {
			BlockChain.UTXOs.put(output.getId(), output);
		}

		for(Input input : inputs) {
			if(input.getUTXO() != null)
				BlockChain.UTXOs.remove(input.getUTXO().getId());
		}
		return true;
	}

  // Get Methods
  public PublicKey getSender() {
		return sender;
	}

  public PublicKey getReceiver() {
    return receiver;
  }

  public List<Input> getInputs() {
    return inputs;
  }

  public List<Output> getOutputs() {
    return outputs;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public double getAmount() {
    return amount;
  }

  public byte[] getSignature() {
		return signature;
	}

  // Set Methods
  public void setSender(PublicKey sender) {
		this.sender = sender;
	}

	public void setReceiver(PublicKey receiver) {
		this.receiver = receiver;
	}

  public void setInputs(List<Input> inputs) {
  	this.inputs = inputs;
  }

  public void setOutputs(List<Output> outputs) {
  	this.outputs = outputs;
  }

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
}
