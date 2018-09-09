import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface DecisionNode {
	
	/**
     * Return a count of the number of objects the tree records
     */
    public int countObjects();
    
    /**
     * Query the user to input and returns an updated node
     */
    public DecisionNode guess(Scanner in);
    
    /**
     * Write the node to a file
     */
    public void write(FileWriter out) throws IOException;
}