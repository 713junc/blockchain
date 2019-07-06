
public class BlockChainApp {

	public static void main(String[] args) {

		BlockChain blockChain = new BlockChain();
		Miner miner = new Miner();

		Block block0 = new Block(0,"transaction1",Constants.GENESIS_PREV_HASH);
		miner.mining(block0, blockChain);

		Block block1 = new Block(1,"transaction2",blockChain.getBlockChain().get(blockChain.size()-1).getHash());
		miner.mining(block1, blockChain);

		Block block2 = new Block(2,"transaction3",blockChain.getBlockChain().get(blockChain.size()-1).getHash());
		miner.mining(block2, blockChain);

	  System.out.println("\n" + "BlockChain:\n"+blockChain);
	  System.out.println("Mining Reward: " + miner.getReward());
	}
}
/*

0-0000000000000000000000000000000000000000000000000000000000000000-000006bee86369be3cc9eb4a418c0ca6594efcf47b700ba5ad43c07213471e9c-transaction1- has just mined
Hash value: 000006bee86369be3cc9eb4a418c0ca6594efcf47b700ba5ad43c07213471e9c
1-000006bee86369be3cc9eb4a418c0ca6594efcf47b700ba5ad43c07213471e9c-00000412b7c37227d8ba3164535137ffc003fbe98be52fb6032210fa10caaea0-transaction2- has just mined
Hash value: 00000412b7c37227d8ba3164535137ffc003fbe98be52fb6032210fa10caaea0
2-00000412b7c37227d8ba3164535137ffc003fbe98be52fb6032210fa10caaea0-000000957325fac17fa78edc5f5b2fc4cb1d68ff449e52778e0276e39a5e465c-transaction3- has just mined
Hash value: 000000957325fac17fa78edc5f5b2fc4cb1d68ff449e52778e0276e39a5e465c
BlockChain:
0-0000000000000000000000000000000000000000000000000000000000000000-000006bee86369be3cc9eb4a418c0ca6594efcf47b700ba5ad43c07213471e9c-transaction1-
1-000006bee86369be3cc9eb4a418c0ca6594efcf47b700ba5ad43c07213471e9c-00000412b7c37227d8ba3164535137ffc003fbe98be52fb6032210fa10caaea0-transaction2-
2-00000412b7c37227d8ba3164535137ffc003fbe98be52fb6032210fa10caaea0-000000957325fac17fa78edc5f5b2fc4cb1d68ff449e52778e0276e39a5e465c-transaction3-

Mining Reward: 30.0

*/
