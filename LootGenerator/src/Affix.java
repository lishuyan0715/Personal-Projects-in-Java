import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Affix {
    public String name;
    public String mod1code;
    public int mod1min;
    public int mod1max;
    
    /**
     * Constructor for Affix objects
     */
    public Affix(String name, String mod1code, int mod1min, int mod1max) {
        this.name = name;
        this.mod1code = mod1code;
        this.mod1min = mod1min;
        this.mod1max = mod1max;
    }
    
    /**
     * Reads from a file and creates an Affix object with the properties described in the file
     * @param in, a Scanner that's set up to read from a file which contains information for Affix objects
     * @return affix, a new Affix object with the properties described in the file
     */
    public static Affix createAffix(Scanner in) {
        String list = in.nextLine();
        String[] items = list.split("\t");
        return new Affix(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]));
    }
    
    /**
     * Reads a file containing information that can be used to make at least one Affix object. Creates at least one affix 
     * and puts them in an ArrayList. Then returns the ArrayList.
     * @param file, a File containing information that can be used to make at least one Affix object. 
     * @return list, an ArrayList of all the Affixes in the given file
     * @throws FileNotFoundException if the Scanner constructor can't find the file given
     */
    public static ArrayList<Affix> createAffixList(File file) throws FileNotFoundException {
        ArrayList<Affix> list = new ArrayList<Affix>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            Affix affix = createAffix(in);
            list.add(affix);
        }
        return list;
    }
}
