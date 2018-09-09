import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Block {

	public int num;
	public int amount;
	public Hash prevHash;
	public long nonce;
	public Hash hash;
	
	/**
	 * Mine the possible hash
	 * @param msg, a string
	 * @return a byte array
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("sha-256");
		md.update(msg.getBytes());
		byte[] hash = md.digest();
		return hash;
	}
	
	/**
	 * Construct a block object
	 * @param num, an integer
	 * @param amount, an integer
	 * @param prevHash, a hash
	 * @throws NoSuchAlgorithmException
	 */
	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		long nonce = 1;
		Hash curHash;
		
		do {
			String msg = "" + num + amount + prevHash + nonce;
			curHash = new Hash(calculateHash(msg));
			if(!(curHash.isValid())) {
				nonce++;
			}
		}
		while(!(curHash.isValid()));
		this.nonce = nonce;
		this.hash = curHash;
	}
	
	/**
	 * Construct a block object
	 * @param num, an integer
	 * @param amount, an integer
	 * @param prevHash, a hash
	 * @param nonce, a long
	 * @throws NoSuchAlgorithmException
	 */
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		String msg = "" + num + amount + prevHash + nonce;
		Hash curHash = new Hash(calculateHash(msg));
		if (!(curHash.isValid())) {
			System.out.println("Error, invalid nonce");
		}
		this.hash = curHash;
	}
	
	/**
	 * Get the number of the block
	 * @return an integer
	 */
	public int getNum() {
		return this.num;
	}
	
	/**
	 * Get the amount of the block
	 * @return an integer
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * Get the nonce of the block
	 * @return a long integer
	 */
	public long getNonce() {
		return this.nonce;
	}
	
	/**
	 * Get the previous hash of the block
	 * @return a hash
	 */
	public Hash getPrevHash() {
		return this.prevHash;
	}
	
	/**
	 * Get the current hash of the block
	 * @return a hash
	 */
	public Hash getHash() {
		return this.hash;
	}
	
	/**
	 * Create a string of entire contents of the block
	 * @return a string
	 */
	public String toString() {
		return "Block " + this.num + " (Amount: " + this.amount +
				", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: " + this.hash;
	}
}
