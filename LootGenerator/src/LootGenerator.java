import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    /**
     * Randomly selects monster to fight from list
     * @param lst, an arraylist of monsters to fight
     * @return monster, a Monster object that's an element of lst
     */
    public static Monster pickMonster(ArrayList<Monster> lst) {
    	int index = (int) (Math.random() * (lst.size()-1));
        return lst.get(index);
    }

    /**
     * A getter for the treasureClass field of a Monster object
     * @param monster, the Monster whose treasureClass field will be returned
     */
    public static String fetchTreasureClass(Monster monster) {
        return monster.getTreasureClass();
    }

    /**
     * Randomly generates a base item 
     * @param tcmap, a hash map from Strings to arraylists of Strings. The i are the names of the type of treasure (AKA the leftmost value 
     *             in the relevant row of the dataset). The values are the arraylists of all treasures of the given type. 
     * @return baseItem, a String 
     */
    public static String generateBaseItem(HashMap<String, ArrayList<String>> tcmap, String item) {
        if(tcmap.containsKey(item)) {
        	Random rand = new Random();
        	int index = rand.nextInt(3);
            String baseKey = tcmap.get(item).get(index);
            return generateBaseItem(tcmap, baseKey);
        } else {
            return item;
        }
    }

    /**
     * Randomly generates base stats for an existing base item
     * @param bsmap, a hash map from Strings to ArrayLists of Integers. The keys are the base items, and the values are the lists
     *             containing the minac and maxac for the base item
     * @param name, the name of the base item
     * @return baseStats, an int which is either the minac or the maxac for the base item
     */
    public static int generateBaseStats(HashMap<String, ArrayList<Integer>> bsmap, String name) {
        ArrayList<Integer> list = bsmap.get(name);
        Random rand = new Random();
    	int baseStats = rand.nextInt(list.get(1) - list.get(0))+ list.get(0);
        return baseStats;
    }

    /**
     * Randomly generate an Affix
     * @param list, an arraylist of potential affixes
     * @return affix, the chosen element of list
     */
    public static Affix generateAffix(ArrayList<Affix> list) {
    	Random rand = new Random();
    	int index = rand.nextInt(list.size()-1);
        return list.get(index);
    }

    /** 
     * main driver function
     * @param args, an array of Strings, not used
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Import data files
        File monsters = new File("data/large/monstats.txt");
        File treasureClasses = new File("data/large/TreasureClassEx.txt");
        File baseStats = new File("data/large/armor.txt");
        File prefix = new File("data/large/MagicPrefix.txt");
        File suffix = new File("data/large/MagicSuffix.txt");
       
        // Set up data structures
        ArrayList<Monster> monsterList = Monster.createMonsterList(monsters);
        
        // tcMap = treasureClass hash map. Keys are the the names of the type of treasure (in other words, the leftmost value 
        // in the relevant row of the dataset). The values are the arraylists of all treasures of the given type. 
        HashMap<String, ArrayList<String>> tcMap = TreasureClass.createTCMap(treasureClasses);
        
        // bsMap = base item map. Keys are the base items, and the values are the lists containing the minac and maxac for the 
        // base item
        HashMap<String, ArrayList<Integer>> bsMap = BaseStats.createBSMap(baseStats);
        
        ArrayList<Affix> prefixes = Affix.createAffixList(prefix);
        ArrayList<Affix> suffixes = Affix.createAffixList(suffix);
        Scanner in = new Scanner(System.in);
        
        do{
            Monster monster = pickMonster(monsterList);
            String baseItem = generateBaseItem(tcMap, fetchTreasureClass(monster));
            int base = generateBaseStats(bsMap, baseItem);
            
        	Random rand = new Random();
            int prefixChance = rand.nextInt(2);
            int suffixChance = rand.nextInt(2);            
            
            String itName = baseItem;
            String itStat1 = "";
            String itStat2 = "";
            
            // If there's a prefix
            if(prefixChance == 1) {
                Affix pf= generateAffix(prefixes);
                String pfName = pf.name;
                String pfStat = pf.mod1code;
                int pfValue = (int) (Math.random() * (pf.mod1max - pf.mod1min) + pf.mod1min);
                itName = pfName + " " + itName;
                itStat1 = pfValue + " " + pfStat;
            }
            
            // If there's a suffix
            if(suffixChance == 1) {
                Affix sf = generateAffix(suffixes);
                String sfName = sf.name;
                String sfStat = sf.mod1code;
                int sfValue = (int) (Math.random() * (sf.mod1max - sf.mod1min) + sf.mod1min);
                itName = itName + " " + sfName;
                itStat2 = sfValue + " " + sfStat;
            }

            // Printing prompt
            System.out.println("Fighting " + monster.monsterClass);
            System.out.println("You have slain " + monster.monsterClass);
            System.out.println(monster.monsterClass + " dropped:\n");
            System.out.println(itName);
            System.out.println("Defense: " + base);
            if(!itStat1.equals("")){
                System.out.println(itStat1);
            }
            if(!itStat2.equals("")){
                System.out.println(itStat2);
            }
            System.out.println("Fight again [y/n]?");

        } while(in.nextLine().equalsIgnoreCase("y"));     
        in.close();
    }

}
