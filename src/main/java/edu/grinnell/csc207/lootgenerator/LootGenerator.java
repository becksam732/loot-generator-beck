package edu.grinnell.csc207.lootgenerator;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/large";
    private static ArrayList <monster>monstList = new ArrayList<monster>();
    private static ArrayList <treasure>tresList = new ArrayList<treasure>();
    private static ArrayList <armor>armList = new ArrayList<armor>();
    private static ArrayList <magic>preList = new ArrayList<magic>();
    private static ArrayList <magic>sufList = new ArrayList<magic>();


    /**
     * Constructor magic
     * 
     * @param Strings Name, mod1code, mod1min, mod1max
     */
    public static class magic {
        private String Name;
        private String mod1code;
        private String mod1min;
        private String mod1max;
        public magic (String Name, String mod1code, String mod1min, String mod1max){
            this.Name = Name;
            this.mod1code = mod1code;
            this.mod1min = mod1min;
            this.mod1max = mod1max;
        }
        public magic (){

        }
    }

    /**
     * Constructor monster
     * 
     * @param Strings Class, Type, Level, TreasureClass
     */
    public static class monster {
        private String Class;
        private String Type;
        private String Level;
        private String TreasureClass;
        public monster (String Class, String Type, String Level, String TreasureClass){
            this.Class = Class;
            this.Type = Type;
            this.Level = Level;
            this.TreasureClass = TreasureClass;
        }
        public monster (){
        }
    }

    /**
     * Constructor Treasure
     * 
     * @param Strings TreasureClass, Item1, Item2, Item3
     */
    public static class treasure {
        private String TreasureClass;
        private String Item1;
        private String Item2;
        private String Item3;
        public treasure (String TreasureClass, String Item1, String Item2, String Item3){
            this.TreasureClass = TreasureClass;
            this.Item1= Item1;
            this.Item2 = Item2;
            this.Item3 = Item3;
        }
        public treasure (){
        }
    }

    /**
     * constructor armor
     * 
     * @param Strings armorName, minac, maxac
     * 
     */
    public static class armor {
        private String armorName;
        private String minac;
        private String maxac;
        public armor (String armorName, String minac, String maxac){
            this.armorName = armorName;
            this.minac = minac;
            this.maxac = maxac;
        }
        public armor (){
        }
    }

    /**
     * void method to fill the prefix arrayList from the prefix txt
     * 
     * 
     * @throws FileNotFoundException
     */
    public static void fillPrefix () throws FileNotFoundException{
        Scanner scan = new Scanner (new File (DATA_SET + "/MagicPrefix.txt")).useDelimiter("\t");

        while (scan.hasNext ()){
            magic pre = new magic ();
            pre.Name = scan.next ().trim();
            pre.mod1code = scan.next ().trim();
            pre.mod1min = scan.next ().trim();
            pre.mod1max = scan.nextLine ().trim();  
            preList.add (pre);
        }
        scan.close (); 
    }

    /**
     * void method fillSuffix, fills the suffix arraylist from the suffix txt
     * @throws FileNotFoundException
     */
    public static void fillSuffix () throws FileNotFoundException{
        Scanner scan = new Scanner (new File (DATA_SET + "/MagicSuffix.txt")).useDelimiter("\t");

        while (scan.hasNext ()){
            magic suf = new magic ();
            suf.Name = scan.next ().trim();
            suf.mod1code = scan.next ().trim();
            suf.mod1min = scan.next ().trim();
            suf.mod1max = scan.nextLine ().trim();  
            sufList.add (suf);
        }
        scan.close (); 
    }

    /**
     * void method fills the armor arrayList from the armor.txt
     * @throws FileNotFoundException
     */
    public static void fillArmor () throws FileNotFoundException{
        Scanner scan = new Scanner (new File (DATA_SET + "/armor.txt")).useDelimiter("\t");

        while (scan.hasNext ()){
            armor arm = new armor ();
            arm.armorName = scan.next ().trim();
            arm.minac = scan.next ().trim();
            arm.maxac = scan.nextLine ().trim();  
            armList.add (arm);
        }
        scan.close (); 
    }

    /**
     * void method fillMonster, fills the monster arraylist from the monstats.txt
     * @throws FileNotFoundException
     */
    public static void fillMonster () throws FileNotFoundException{
        Scanner scan = new Scanner (new File (DATA_SET + "/monstats.txt")).useDelimiter("\t");

        while (scan.hasNext ()){
            monster mon = new monster ();
            mon.Class = scan.next ().trim();
            mon.Type = scan.next ().trim();
            mon.Level = scan.next ().trim();
            mon.TreasureClass = scan.nextLine ().trim(); //  
            monstList.add (mon);
        }
        scan.close (); 
    }

    /**
     * monster type pickMonster.
     * @return a new monster type that is pulled randomly from the list at monstats.txt
     */
    public static monster pickMonster (){
        Random rand = new Random ();
        int random = rand.nextInt (monstList.size ());
        monster mon = monstList.get (random);
        System.out.println ("Figting " + mon.Type);
        System.out.println("You have slain " + mon.Type);
        System.out.println(mon.Type + " dropped:\n");
        return mon;

    }


    /**
     * void method fillTreasure, pulls from the treasureclass.txt to fill in the treasure arraylist
     * @throws FileNotFoundException
     */
    public static void fillTreasure () throws FileNotFoundException{
        Scanner scan = new Scanner (new File (DATA_SET + "/TreasureClassEx.txt")).useDelimiter("\t");

        while (scan.hasNext ()){
            treasure tres = new treasure ();
            tres.TreasureClass = scan.next ().trim();
            tres.Item1 = scan.next ().trim();
            tres.Item2 = scan.next ().trim();
            tres.Item3 = scan.nextLine ().trim();   
            tresList.add (tres);
        }
        scan.close ();
    }

    /**
     * fetchTreasureClass
     * @return a treasure type, this treasure will be pulled from the monster selcted from pickMonster, and checked to make sure it is in the treasurelist.
     */
    public static treasure fetchTreasureClass(){
        String tr = pickMonster().TreasureClass;
        for(int i = 0; i < tresList.size(); i++){
            if(tresList.get(i).TreasureClass.equals(tr)){
                return tresList.get(i);
            }
        } return new treasure();
    }

    /**
     * generateBaseItem, recursevily obtains a string representing a base item dropped from a monster.
     * @return a string item
     */
    public static String generateBaseItem (){
        treasure tr = fetchTreasureClass();
        return generateBaseItemHelper (tr);
    }

    public static String generateBaseItemHelper (treasure tres){
        Random rand = new Random ();
        int random = 1 + rand.nextInt (3);
        String it = "";
        if(random == 1){
            it = tres.Item1;
        } else if(random == 2){
            it = tres.Item2;
        } else if(random == 3){
            it = tres.Item3;
        }
        for (int i = 0; i < tresList.size ();i++){
            if (tresList.get(i).TreasureClass.equals(it)){
                it = generateBaseItemHelper (tresList.get (i));
            }
        }
        return it;
    }

    /**
     * generateBaseStats, randomly selects the stat of an item between a predetermined range of ints.
     * @return an integer representeing the stat value of a item.
     */
    public static int generateBaseStats (){
        String gitem = generateBaseItem();
        System.out.println (gitem);
        for(int i = 0; i < armList.size(); i++){
            if(gitem.equals(armList.get(i).armorName)){
                Random rand = new Random ();
                return rand.nextInt (Integer.parseInt (armList.get(i).minac), (Integer.parseInt (armList.get(i).maxac)));
            }
        } return -1;
    }

    /**
     * randomly selects the prefix and affix that surrounds an item, and properly prints these out along with the item and its stat values.
     */
    public static void generateAffix (){
        String firstLine = "";
        String secondLine = "";
        String thirdLine = "";
        boolean preEmpty = true;
        boolean sufEmpty = true;

        Random newRand = new Random ();
        int run = newRand.nextInt (1);
        if (run == 1){
        }
        else{
            Random rand = new Random ();
            int random = rand.nextInt (preList.size ());
            magic mg = preList.get(random);

            Random stats = new Random ();
            int statRand;
            if (mg.mod1min.equals (mg.mod1max)){
                statRand = Integer.parseInt (mg.mod1min);
            }
            else{
                statRand = stats.nextInt (Integer.parseInt (mg.mod1min),Integer.parseInt (mg.mod1max));
            }
            firstLine += mg.Name;
            secondLine += statRand + " " + mg.mod1code;
            preEmpty = false;

        }
        firstLine += " " + generateBaseItem ();
        Random secondRand= new Random ();
        int secondRun = secondRand.nextInt (1);
        if (secondRun == 1){
        }
        else{
            Random rand = new Random ();
            int random = rand.nextInt (sufList.size ());
            magic mg = sufList.get(random);

            Random stats = new Random ();
            int statRand;
            if (mg.mod1min.equals (mg.mod1max)){
                statRand = Integer.parseInt (mg.mod1min);
            }
            else{
                statRand = stats.nextInt (Integer.parseInt (mg.mod1min),Integer.parseInt (mg.mod1max));
            }
            firstLine += " " + mg.Name;
            thirdLine += statRand + " " + mg.mod1code;
            sufEmpty = false;
        }

        System.out.println (firstLine);
        if (!sufEmpty){
            System.out.println (secondLine);
        }
        if (!preEmpty){
            System.out.println (thirdLine);
        }


        //sturdy helemet of the dune
        //Armor % 50
        //Lightning Damage 3
        

    }

    
            
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner (System.in);
        System.out.println("This program kills monsters and generates loot!");
        fillMonster ();
        fillTreasure ();
        fillArmor ();
        fillPrefix ();
        fillSuffix ();
       
        boolean running = true;
        while (running){
            generateAffix ();

            
            boolean valid = false;
            while(!valid){
                System.out.print ("Fight again [y/n]? ");
                String answer = scan.next ();
                if(answer.equals ("n") || answer.equals ("N") || answer.equals ("y") || answer.equals ("Y")){
                    if (answer.equals ("n") || answer.equals ("N")){
                        running = false;
                    }
                    valid = true;
                } else {
                    valid = false;
                }
            }
        }
        
    }
}
