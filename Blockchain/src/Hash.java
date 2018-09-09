import java.util.Arrays;
public class Hash {

	private byte[] data;

	/**
	 * Constructor for a hash object
	 * @param data, a byte array
	 */
	public Hash(byte[] data) {
		this.data = data;
	}
	
	/**
	 * Get data from a hash
	 * @return a byte array
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * Check the validity of a hash
	 * @return a boolean(true or false)
	 */
	public boolean isValid() {
		if ((this.data[0] == 0) &&
			(this.data[1] == 0) &&
			(this.data[2] == 0)){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Convert byte array to a string
	 * @return a string
	 */
	public String toString() {
		byte[] data = this.data;
		String ret = new String("");
		for (int i = 0; i < data.length; i++) {
			int temp = Byte.toUnsignedInt(data[i]);
			ret = ret + Integer.toHexString(temp);
		}
		return ret;
	}
	
	/**
	 * Check the equality of two hashes
	 * @param other, a hash
	 * @return a boolean (True or false)
	 */
	public boolean equals(Object other) {
		if (other instanceof Hash) {
			Hash o = (Hash)other;
			return Arrays.equals(o.data, this.data);
		}
		else {
			return false;
		}
	}
	
	
}
