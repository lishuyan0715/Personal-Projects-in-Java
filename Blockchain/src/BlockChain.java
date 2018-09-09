import java.security.NoSuchAlgorithmException;
import java.lang.IllegalArgumentException;
public class BlockChain {

	//node class
	public static class Node {

		Block block;
		Node next;

		public Node(Block block) {
			this.block = block;
			this.next = null;
		}
	}
	Node first;
	Node last;

	/**
	 * Construct a Block Chain
	 * @param initial, the amount of money that Alice hold at the beginning
	 * @throws NoSuchAlgorithmException
	 */
	public BlockChain(int initial) throws NoSuchAlgorithmException {
		Block first = new Block(0, initial, null);
		Node node = new Node(first);
		this.first = node;
		this.last = node;
	}

	/**
	 * Creates a new block that has a valid hash
	 * @param amount, an integer
	 * @return a block
	 * @throws NoSuchAlgorithmException
	 */
	public Block mine(int amount) throws NoSuchAlgorithmException {
		int n = this.last.block.getNum() + 1;
		Hash hash = this.last.block.getHash();
		Block b = new Block(n, amount, hash);
		return b;
	}

	/**
	 * Get the size of current block chain
	 * @return an integer, the number of elements in the block chain
	 */
	public int getSize() {
		return this.last.block.getNum() + 1;
	}

	/**
	 * Append the inputed block after the block chain
	 * @param blk, a block that was mined
	 * @throws IllegalArgumentException
	 */
	public void append(Block blk) throws IllegalArgumentException {
		if(blk.hash.isValid()) {
			Node newNode = new Node(blk);
			this.last.next = newNode;
			this.last = newNode;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Remove the last block in the block chain
	 * @return a boolean (True if it actually remove something; False if it is not)
	 */
	public boolean removeLast() {
		if(this.getSize() == 1) {
			return false;
		} else {
			int end = this.getSize();
			Node penultimate = this.first;
			for(int i = 1; i < end - 1; i++) {
				penultimate = penultimate.next;
			}
			System.out.println("removing");
			System.out.println(penultimate.block.toString());
			System.out.println(this.last.block.toString());
			this.last = penultimate;
			penultimate.next = null;
			System.out.println(penultimate.block.toString());
			System.out.println(this.last.block.toString());
			return true;
		}
	}

	/**
	 * Get the hash of last block of the block chain
	 * @return a hash
	 */
	public Hash getHash() {
		return this.last.block.getHash();
	}

	/**
	 * Check the validity of the block chain
	 * @return a boolean (True if the block chain is valid; false if it is not)
	 */
	public boolean isValidBlockChain() {
		Node curr = this.first;
		int bob  = 0;
		int alice = curr.block.getAmount();
		for (int i = 1; i < this.getSize(); i++) {
			if (!curr.block.hash.isValid()) {
				return false;
			}
			curr = curr.next;
			alice += curr.block.getAmount();
			bob -= curr.block.getAmount();
			if (alice < 0 || bob < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Print Alice and Bob's current balance
	 */
	public void printBalance() {
		Node curr = this.first;
		int alice = curr.block.getAmount();
		int bob = 0;
		for (int i = 1; i < this.getSize(); i++) {
			curr = curr.next;
			alice += curr.block.getAmount();
			bob -= curr.block.getAmount();
		}
		System.out.println("Alice: " + alice + ", Bob: " + bob);
	}

	/**
	 * Converts all contents in the block chain to a string
	 * @return a string
	 */
	public String toString() {
		String ret = "";
		Node curr = this.first;
		for (int i = 0; i < this.getSize(); i++) {
			ret += curr.block.toString() + "\n";
			curr = curr.next;
		}
		return ret;
	}
}
