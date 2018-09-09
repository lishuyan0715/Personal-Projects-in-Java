import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode{
    String object;
    
    public GuessNode (String object){
        this.object = object; 
    }
    
    /**
     * Return a count of the number of objects the tree records
     */
    public int countObjects() {
        return 1;
    }
    
    /**
     * Query the user to input and returns an updated node
     */
    public DecisionNode guess(Scanner in) {
        System.out.print("Are you thinking of " + object + "? ");
        String answer = in.nextLine();
        while (!answer.equalsIgnoreCase("no") && !answer.equalsIgnoreCase("yes")) {
        	System.out.print("Sorry, invalid answer. Please type yes or no: ");
        	answer = in.nextLine();
        }
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Excelent, thanks!");
            return this;
        } else {
            System.out.println("Oh no, I was wrong!");
            System.out.print("What animal were you thinking of? ");
            String newObject = in.nextLine();
            while (newObject.charAt(0) == '#') {
            	System.out.println("Your animal cannot begin with a '#' character.");
            	System.out.print("Please enter a new animal: ");
            	newObject = in.nextLine();
            }
            System.out.println("What is a yes/no question that distinguishes a "
                    + this.object + " from a " + newObject + "?");
            System.out.print("(Yes corresponds to " + this.object 
                    + "; No corresponds to " + newObject + ") ");
            String newQuestion = in.nextLine();
            while (newQuestion.charAt(0) == '#' || newQuestion.charAt(newQuestion.length()-1) != '?') {
            	System.out.println("Your question cannot begin with a '#' character, and must end in a question mark.");
            	System.out.print("Please enter a new question: ");
            	newQuestion = in.nextLine();
            }
            System.out.println("Thanks!  I'll learn from this experience!");
            
            // set up new tree 
            DecisionNode newGuessNode = new GuessNode(newObject);
            DecisionNode newQuestionNode = new QuestionNode(this, newGuessNode, newQuestion);
            return newQuestionNode;
        }
    }
    
    /**
     * Write the node to a file
     */
    public void write(FileWriter out) throws IOException {
        out.write(this.object + '\n');
    }
}