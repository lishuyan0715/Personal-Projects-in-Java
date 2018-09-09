import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


public class HuffmanTree {

	public Node root;

	class compareNode implements Comparator<Node> {

		@Override
		/**
		 * Override the compare in Node
		 */
		public int compare(Node first, Node second) {
			return first.freq - second.freq;
		}
	}

	/**
	 *  Construct a HuffmanTree by a HashMap of values and frequencies
	 * @param m, a map
	 */
	public HuffmanTree(Map<Short, Integer> m) {
		PriorityQueue<Node> pq = new PriorityQueue<>(new compareNode());
		for (Map.Entry<Short, Integer> entry : m.entrySet())
		{
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}
		pq.add(new Node((short) 256, 1));

		while(pq.size() > 1) {
			Node newnode = buildNewNode(pq.poll(), pq.poll());
			pq.add(newnode);
		}
		this.root = pq.poll();
	}

	/**
	 * Construct a HuffmanTree with input BitInputStream
	 * @param in, a BitInputStream
	 */
	public HuffmanTree(BitInputStream in) {
		
		this.root = preOrderBuild(in);

	}

	/**
	 * Build a internal node with two inputed node
	 * @param l
	 * @param r
	 * @return t, a node
	 */
	public Node buildNewNode(Node l, Node r) {
		Node t = new Node((short) -1, (l.freq + r.freq));
		t.left = l;
		t.right = r;

		return t;
	}

	/**
	 * Recursively build the tree by pre-order traversal
	 * @param in, BitInputStream 
	 * @return n, the root
	 */
	public Node preOrderBuild (BitInputStream in) {
		if(in.readBit() == 0) {
			Node leaf = new Node((short)in.readBits(9), 0);
			return leaf;
		}
		else {
			Node interiorNode = new Node((short) -1,0);
			interiorNode.left = preOrderBuild(in);
			interiorNode.right = preOrderBuild(in);
			return interiorNode;
		}
	}

	/**
	 * Write the HuffmanTree to  the given file as a stream of bits 
	 * and Keep track the paths of nodes
	 * @param out, a BitInputStream 
	 * @param current, a node
	 * @param paths, an ArrayList
	 * @param path, an ArrayList
	 */
	public void serializeHelper(BitOutputStream out, Node current, Map<Short, ArrayList<Integer>> paths, ArrayList<Integer> path) {
		if (current.left == null && current.right == null) {
			out.writeBit(0);
			out.writeBits(current.value, 9);
			paths.put((short)current.value, path);
		} else {
			out.writeBit(1);
			ArrayList<Integer> leftPath = new ArrayList<>(path);
			leftPath.add(0);
			serializeHelper(out, current.left, paths, leftPath);
			ArrayList<Integer> rightPath = new ArrayList<>(path);
			rightPath.add(1);
			serializeHelper(out, current.right, paths, rightPath);
		}
	}

	/**
	 * Writes the HuffmanTree to the given file as a stream of bits in a serialized format
	 * @param out, a BitInputStream 
	 * @return map, a HashMap
	 */
	public Map<Short, ArrayList<Integer>> serialize(BitOutputStream out) {
		Map<Short, ArrayList<Integer>> paths = new HashMap<>();
		serializeHelper(out, root, paths, new ArrayList<Integer>());
		return paths;
	}


	/**
	 * Encodes the file given as a stream of bits into a compressed format using this Huffman tree.
	 * @param in, a BitInputStream
	 * @param out, a BitOutputStream
	 */
	public void encode(BitInputStream in, BitOutputStream out) {
		out.writeBits(0x736, 32);
		Map<Short, ArrayList<Integer>> paths = this.serialize(out);

		while(in.hasBits()) {
			short next = (short) in.readBits(8);
			ArrayList<Integer> path = paths.get(next);
			for (int i = 0 ; i < path.size(); i++) {
				out.writeBit(path.get(i));
			}
		}
		ArrayList<Integer> path = paths.get((short)256);
		for (int i = 0 ; i < path.size(); i++) {
			out.writeBit(path.get(i));
		}

	}

	/**
	 * Decodes a stream of huffman codes from a file given as a stream of bits into their uncompressed form, saving the results to the given output stream.
	 * @param in, a BitInputStream
	 * @param out, a BitOutputStream
	 */
	public void decode(BitInputStream in, BitOutputStream out) {
		Node current = this.root;
		while(in.hasBits()) {
			if(in.readBit() == 0) {
				current = current.left;
				if(current.left == null && current.right == null) {
					if (current.value == 256) {
						break;
					} else {
						out.writeBits(current.value, 8);
					}
					current = this.root;
				}
			} else {
				current = current.right;
				if(current.left == null && current.right == null) {
					if (current.value == 256) {
						break;
					} else {
						out.writeBits(current.value, 8);
					}
					current = this.root;
				}
			}		
		}
	}

}
