import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Grin {
    
	/**
	 * decodes the .grin file denoted by infile and writes the output to the .grin file denoted by outfile.
	 * @param infile
	 * @param outfile
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
    public static void decode(String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);
        
        if(in.readBits(32)== 0x736) {
        HuffmanTree ht = new HuffmanTree(in);
        ht.decode(in, out);
        }
        
        else {
        	throw new IllegalArgumentException();
        }
    }
    
    /**
     * Create a mapping from 8-bit sequences to number-of-occurrences of those sequences in the given file. 
     * @param file, a valid file
     * @return chars, a HashMap
     * @throws IOException
     */
    public static Map<Short, Integer> createFrequencyMap(String file) throws IOException{
        BitInputStream in = new BitInputStream(file);
        Map<Short,Integer> chars = new HashMap<>();
        while(in.hasBits()) {
            char nextChar = (char)in.readBits(8);
            System.out.println(nextChar);
            short next = (short) nextChar;
            if (chars.containsKey(next)) {
                chars.put((short)next, chars.get(next)+1);
            }else {
                chars.put(next, 1);
            }
        }
        return chars;
    }
    
    /**
     * Encodes the given file denoted by infile and writes the output to the .grin file denoted by outfile
     * @param infile, a file
     * @param outfile, a file
     * @throws IOException
     */
    public static void encode(String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);
        HuffmanTree ht = new HuffmanTree(createFrequencyMap(infile));
        ht.encode(in, out);

    }
    public static void main(String[] args) throws IOException {
        if (args[0].equals("encode")) {
            encode(args[1], args[2]);
        } else if (args[0].equals("decode")) {
            decode(args[1], args[2]);
        } else {
            System.out.println("Usage: Grin <encode|decode> <infile> <outfile>");
        }
       
       
    }
}
