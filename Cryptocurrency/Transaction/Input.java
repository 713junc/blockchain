
public class Input { // Transaction-Input

    private String outputID;
    private Output UTXO;

    public Input(String outputID) {
        this.outputID = outputID;
    }

    public String getOutputID() {
        return outputID;
    }
    
    public Output getUTXO() {
        return UTXO;
    }

    public void setOutputId(String outputID) {
        this.outputID = outputID;
    }

    public void setUTXO(Output uTXO) {
        UTXO = uTXO;
    }
}
