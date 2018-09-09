
public class Node {
    Node left;
    Node right;

    public short value;
    public int freq;
    
    /**
     * Construct a leaf
     * @param value, a short
     * @param freq, an integer
     */
    public Node (short value, int freq) {
        this.value = value;
        this.freq = freq;
    }
    

    /**
     * Construct a interior node
     * @param freq, an integer
     */
    public Node (int freq) {
        this.value = (short) -1;
        this.freq = freq;
        this.left = null;
        this.right = null;
        
    }
    
    /**
     * Get the size of current node
     * @return size, an integer
     */
    int size() {
        if(this.right == null && this.left == null) {
            return 0;
        } else if (this.left == null) {
            return 1 + right.size();
        } else if (this.right == null) {
            return 1 + left.size();
        } else {
            return 1 + left.size() + right.size();
        }
    }
}
