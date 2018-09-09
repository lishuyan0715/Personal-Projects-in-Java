import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode {
    DecisionNode yesBranch;
    DecisionNode noBranch;
    String question;
    
    /**
     * Construct a QuestionNode
     * @param yesBranch, a DecisionNode
     * @param noBranch, a DecisionNode
     * @param question, a String
     */
    public QuestionNode (DecisionNode yesBranch, DecisionNode noBranch, String question) {
        this.yesBranch = yesBranch;
        this.noBranch = noBranch;
        this.question = question;
    }
    
    /**
     * Return a count of the number of objects the tree records
     */
    public int countObjects() {
        return this.yesBranch.countObjects() + this.noBranch.countObjects();
    }
    
    /**
     * Query the user to input and returns an updated node
     */
    public DecisionNode guess(Scanner in) {
        System.out.print(question + " ");
        String answer = in.nextLine();
        while (!answer.equalsIgnoreCase("no") && !answer.equalsIgnoreCase("yes")) {
        	System.out.print("Sorry, invalid answer. Please type yes or no: ");
        	answer = in.nextLine();
        }
        if (answer.equalsIgnoreCase("yes")) {
            this.yesBranch = this.yesBranch.guess(in);
        } else {
            this.noBranch = this.noBranch.guess(in);
        }
        return this;
    }
    
    /**
     * Write the node to a file
     */
    public void write(FileWriter out) throws IOException {
        out.write('#' + this.question + '\n');
        this.yesBranch.write(out);
        this.noBranch.write(out);
    }
}