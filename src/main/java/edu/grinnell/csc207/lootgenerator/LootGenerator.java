package edu.grinnell.csc207.lootgenerator;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    private static ArrayList <monster>monstList = new ArrayList<monster>();
    private static ArrayList <treasure>tresList = new ArrayList<treasure>();


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

    public static void fillMonster () throws FileNotFoundException{
        Scanner scan = new Scanner (new File ("data/large/monstats.txt"));

        while (scan.hasNext ()){
            monster mon = new monster ();
            mon.Class = scan.next ();
            mon.Type = scan.next ();
            mon.Level = scan.next ();
            mon.TreasureClass = scan.nextLine ();   
            monstList.add (mon);
        }


        scan.close ();

        
    }

    public static monster pickMonster (){
        Random rand = new Random ();
        System.out.println ("monst size" + monstList.size ());
        int random = rand.nextInt (monstList.size ());
        return monstList.get (random);

    }


    public static void fillTreasure () throws FileNotFoundException{
        Scanner scan = new Scanner (new File ("data/large/TreasureClassEx.txt")).useDelimiter("\t");

        while (scan.hasNext ()){
            treasure tres = new treasure ();
            tres.TreasureClass = scan.next ();
            tres.Item1 = scan.next ();
            tres.Item2 = scan.next ();
            tres.Item3 = scan.nextLine ();   
            tresList.add (tres);
        }


        scan.close ();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("This program kills monsters and generates loot!");
        fillMonster ();
        System.out.println (pickMonster ());
        System.out.println (monstList.get (13).TreasureClass);
        fillTreasure ();
        System.out.println (tresList.get (17).Item3);
    }
}
