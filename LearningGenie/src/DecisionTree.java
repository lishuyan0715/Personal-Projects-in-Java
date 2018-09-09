import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecisionTree {
    DecisionNode root;
    
    /**
     * Construct a Decision Tree with an initial guess node
     */
    public DecisionTree() {
        this.root = new GuessNode("Dog");
    }
    
	/**
	 * Build the Decision Tree by scanning the inputed file recursively
	 * @param input, a Scanner
	 * @return updated DecisionNode
	 */
	public DecisionNode readHelper (Scanner input) {
		String nextThing = input.nextLine();
		if (nextThing.charAt(0) == '#') {
			QuestionNode newQuestion = new QuestionNode(null, null, nextThing.substring(1));
			newQuestion.yesBranch = readHelper(input);
			newQuestion.noBranch = readHelper(input);
			DecisionNode newNode = newQuestion;
			return newNode;
		} else {
			DecisionNode newObject = new GuessNode(nextThing);
			return newObject;
		}
	}
    
	/**
     * Construct a Decision Tree through the inputed file
     *  @param file, a File
     */
    public DecisionTree(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        this.root = readHelper(input);
        input.close();
    }
    
    /**
     * 
     * @return the number of objects in this decision tree
     */
    public int countObjects() {
        return this.root.countObjects();
    }
    
    /**
     * Performs the guessing game
     * @param in, a Scanner
     * @return a current DecisionNode
     */
    public DecisionNode guess(Scanner in) {
        return this.root.guess(in);
    }
}