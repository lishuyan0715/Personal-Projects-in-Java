import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearningGenie {

    public static void main (String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        File input = new File("data.tree");
        DecisionTree tree = new DecisionTree(input);

        System.out.println("I am the learning genie!");
        System.out.println("I can figure out whatever you are thinking of by asking questions.");
        int numObjects = tree.countObjects();
        if (numObjects == 1) {
            System.out.println("I know 1 thing!");
        } else {
            System.out.println("I know " + numObjects + " things!");
        }
        System.out.println();
        
        Boolean cont = true;
        while (cont) {
            System.out.println("Think of an animal!");
            tree.root = tree.root.guess(in);
            System.out.print("Do you want to continue? ");
            String answer = in.nextLine();
            while (!answer.equalsIgnoreCase("no") && !answer.equalsIgnoreCase("yes")) {
            	System.out.print("Sorry, invalid answer. Please type yes or no: ");
            	answer = in.nextLine();
            }
            if (answer.equalsIgnoreCase("no")) {
                cont = false;
            }
            System.out.println();
        }
        
        in.close();
        
        // write output to the file
    	FileWriter out = new FileWriter("data.tree");
        tree.root.write(out);
        out.close();
    }
    
}

