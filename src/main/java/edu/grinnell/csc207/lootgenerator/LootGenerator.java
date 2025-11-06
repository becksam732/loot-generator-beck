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
    // public void toMonster (String line){
    //     char [] Class = new char [20];
    //     char [] Type = new char [20];
    //     char [] Level = new char [20];
    //     char [] TreasureClass = new char [20];
    //     for (int i = 0; i < line.length (); i++){
    //         while (line.charAt (i) != ('\t')){
    //             int j = 0;
    //             Class [j]= line.charAt (i);
    //             j++;
    //         }
    //     }
    // }
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
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("This program kills monsters and generates loot!");
        fillMonster ();
        System.out.println (pickMonster ());
    }
}
