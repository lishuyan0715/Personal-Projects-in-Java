import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Monster {
    public String monsterClass;
    public String type;
    public String level;
    public String treasureClass;
    
    // Constructor
    public Monster(String monsterClass, String type, String level, String treasureClass) {
        this.monsterClass = monsterClass;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }
    
    /**
     * Getter for monsterClass field
     * @return monsterClass, a String storing the given object's treasureClass
     */
    public String getMonsterClass() {
        return this.monsterClass;
    }
    
    /**
     * Getter for treasureClass field
     * @return treasureClass, a String storing the given object's treasureClass
     */
    public String getTreasureClass() {
        return this.treasureClass;
    }
    
    /**
     * Reads from file and makes a monster based on what it reads
     * @param in, a Scanner that'll read from a file
     * @return monster, a new Monster object with the characteristics the user inputs
     */
    public static Monster createMonster(Scanner in) {
        String line = in.nextLine();
        String[] items = line.split("\t");
        return new Monster(items[0], items[1], items[2], items[3]);
    }
    
    /**
     * Creates a list of Monster objects
     * @param file, a File that holds information about monsters 
     * @return monsterList, an ArrayList of Monster objects
     * @throws FileNotFoundException if the file is not found by the Scanner constructor
     */
    public static ArrayList<Monster> createMonsterList(File file) throws FileNotFoundException {
        ArrayList<Monster> monsterList = new ArrayList<Monster>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            monsterList.add(createMonster(in));
        }
        return monsterList;
    }
}
