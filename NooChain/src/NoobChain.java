import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class NoobChain {

	public static int difficulty = 3;
	public static int counter;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Block genesisBlock = new Block("Hi im the first block", "0");
		// Block secondBlock = new Block("Yo im the second block",
		// genesisBlock.hash);
		// Block thirdBlock = new Block("Hey im the third block",
		// secondBlock.hash);
		// System.out.println("Hash for block 1 : " + genesisBlock.hash);
		// System.out.println("Hash for block 2 : " + secondBlock.hash);
		// System.out.println("Hash for block 3 : " + thirdBlock.hash);

		// add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("Hi Jajang! Ini blok inisialisasi", "0"));
		System.out.println("Trying to Mine Core block... ");
		blockchain.get(0).mineBlock(difficulty);
		for(int i = 1; i<100; i++){
			blockchain.add(new Block("Block number "+i+"", blockchain.get(blockchain.size() - 1).hash));
			System.out.println("Trying to Mine block "+i+"... ");
			blockchain.get(i).mineBlock(difficulty);
			counter = i;
		}
//		blockchain.add(new Block("Hi Jajang! Ini blok pertama", "0"));
//		System.out.println("Trying to Mine block 1... ");
//		blockchain.get(0).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Hello bad boy, this is second blok", blockchain.get(blockchain.size() - 1).hash));
//		System.out.println("Trying to Mine block 2... ");
//		blockchain.get(1).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Hey dude im the third block", blockchain.get(blockchain.size() - 1).hash));
//		System.out.println("Trying to Mine block 3... ");
//		blockchain.get(2).mineBlock(difficulty); 
//		
		System.out.println("\nBlockchain is Valid: " + isChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		System.out.println("\nTotal blocks mined : "+(counter + 1));
	}

	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		// loop through blockchain to check hashes:
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			// compare registered hash and calculated hash:
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}

			// compare previous hash and registered previous hash
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}

			// check if hash is solved
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
