import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BaseStats {
    private String name;
    private int minac;
    private int maxac;
    
    /**
     * Constructor for BaseStats objects
     */
    public BaseStats(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }
    
    /**
     * Getter for name field
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter for minac and maxac fields
     * @return items, an ArrayList of Integers. The first entry is minac; the second entry is maxac. 
     */
    public ArrayList<Integer> getItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(minac);
        items.add(maxac);
        return items;
    }
    
    /**
     * Reads from file to create a BaseStats object
     * @param in, a Scanner which is ready to read from a file with information for BaseStats objects
     * @return newBaseStats, a BaseStats object with the characteristics according to the file's descriptions
     */
    public static BaseStats createBaseStats(Scanner in) {
        String list = in.nextLine();
        String[] items = list.split("\t");
        BaseStats bs = new BaseStats(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2]));
        return bs;
    }
    
    /**
     * Creates a hash map designed to store information about BaseMap objects
     * @param file, a File with information that can be used to make BaseStats objects
     * @return map, a hash map from Strings to ArrayLists of Integers. The keys are the names of the BaseStats. The values are
     *              ArrayLists which store the minac and maxac for the given BaseStat object. 
     * @throws FileNotFoundException if the Scanner constructor cannot find the given file
     */
    public static HashMap<String, ArrayList<Integer>> createBSMap(File file) throws FileNotFoundException {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            BaseStats bs = createBaseStats(in);
            map.put(bs.getName(), bs.getItems());
        }
        return map;
    }
}
