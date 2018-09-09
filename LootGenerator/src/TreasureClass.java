import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TreasureClass {
    private String tclass; // Name of the treasure class
    private String item1; 
    private String item2;
    private String item3;
    
    /**
     * TreasureClass constructor
     */
    public TreasureClass(String tclass, String item1, String item2, String item3) {
        this.tclass = tclass;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
    
    /**
     * Getter for tclass field
     * @return tclass, a String
     */
    public String gettClass() {
        return tclass;
    }
    
    /**
     * Getter for the item1, item2, and item3 fields
     * @return itemList, an ArrayList of Strings. The first String is item1, the second is item2, and the third is item3.
     */
    public ArrayList<String> getItems() {
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        return itemList;
    }
    
    /**
     * Reads from file to create new TreasureClass object
     * @param in, a Scanner that will read from a file containing information for TreasureClass objects
     * @return newTreasureClass, a TreasureClass object with the properties described in the file
     */
    public static TreasureClass createTreasureClass(Scanner in) {
        String line = in.nextLine();
        String[] treasures = line.split("\t");
        TreasureClass newtc = new TreasureClass(treasures[0], treasures[1], treasures[2], treasures[3]);
        return newtc;
    }
    
    /**
     * Creates and returns a hash map which stores information about TreasureClass objects
     * @param file, a File containing information for TreasureClass objects
     * @return map, a HashMap from Strings to ArrayLists of Strings. The keys are the names of the TreasureClass objects. 
     *              The values are the lists including item1, item2, and item3.
     */
    public static HashMap<String, ArrayList<String>> createTCMap(File file) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            TreasureClass treasure = createTreasureClass(in);
            map.put(treasure.gettClass(), treasure.getItems());
        }
        return map;
    }
}
