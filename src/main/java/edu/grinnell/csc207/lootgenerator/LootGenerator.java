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
        Scanner scan = new Scanner (new File (DATA_SET + "/monstats.txt"));

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
        int random = rand.nextInt (monstList.size ());
        return monstList.get (random);

    }


    public static void fillTreasure () throws FileNotFoundException{
        Scanner scan = new Scanner (new File (DATA_SET + "/TreasureClassEx.txt")).useDelimiter("\t");

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

    public treasure fetchTreasureClass(){
        for(int i = 0; i < tresList.size(); i++){
            String tr = pickMonster().TreasureClass;
            if(tresList.get(i).TreasureClass.equals(tr)){
                return tresList.get(i);
            }
        } return new treasure();
    }


    public String generateBaseItem (){
        treasure temp = fetchTreasureClass();
        String Item = temp.Item1;
        while(!Item.substring(0,3).equals("armo")){
            String tr = pickMonster().TreasureClass;
            Random rand = new Random ();
            int random = 1 + rand.nextInt (3);
            if(random == 1){
                Item = tresList.get(i).Item1;
                tr = tresList.get(i).Item1;
            } else if(random == 2){
                Item = tresList.get(i).Item2;
                tr = tresList.get(i).Item2;
            } else if(random == 3){
                Item = tresList.get(i).Item3;
                tr = tresList.get(i).Item3;
            }
        }
        return Item;
    }
            
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("This program kills monsters and generates loot!");
        fillMonster ();
        fillTreasure ();
        //System.out.println (pickMonster ());
        //System.out.println (monstList.get (13).TreasureClass);
        System.out.println (tresList.get (13).TreasureClass);
        System.out.println (generateBaseItem());
    }
}
