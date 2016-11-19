/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thepriceisright;
import java.util.*;
import java.util.Collections;
import java.lang.String;

/**
 *
 * @author sognefej
 */
public class Game implements Comparable<Game>{
    
    protected static Random ran_num = new Random();
    
    /* min and max of the shapes they are allowd */
    final private int MinN = 3;
    final private int MaxN = 7;
    
    private int Number_of_shapes = -1;
    private int trial = 0;
    private int score = 0;
    //private int current_shape = 0;
    private short shapes_delt = 0;
    
    /* NOTE THAT THESE WILL BE CASE SYSETIVE*/
    private ArrayList<String> colors = new ArrayList<>();
    private ArrayList<String> shapes = new ArrayList<>();
    
    private Map< Integer, String[]> shape_map = new HashMap<>();
    
    
    //private String[] colors;
    //private String[] shapes;

    
    public Game(){
        
        this(0);
        
    }
    
    /**
     * This makes a new instance of a game HashMap
     * 
     * @param Number_of_shapes
     * @throws IllegalArgumentException 
     */
    public Game (int Number_of_shapes) throws IllegalArgumentException{
        
        
        if((Number_of_shapes % 2 == 0 || Number_of_shapes < this.MinN) || (Number_of_shapes > this.MaxN)){
            
            throw new IllegalArgumentException ("The interger must odd and be bewtwen " + this.MinN + " and " + this.MaxN);
            
        }
        else{
    
            this.Number_of_shapes = Number_of_shapes;
        
        }
    
    }
    
    
    /**
     * set the game colors to be used
     * @param colors 
     */
    public void set_colors (ArrayList<String> colors){   
        
        this.colors = new ArrayList(colors);
    
    }
    
    /**
     * set the game shapes to be used
     * @param shapes 
     */
    public void set_shapes (ArrayList<String> shapes){
    
        this.shapes = new ArrayList(shapes);
    
    }
    
    public void add_color (String color){
        
        this.colors.add(color);
        
    }
    
    public void add_shape (String shape){
        
        this.shapes.add(shape);
       
    }
    
    public void remove_color (String color){
        
        this.colors.remove(color);
    
    }
    
    public void remove_shape (String shape){
        
        this.shapes.remove(shape);
    }
    
    public ArrayList<String> get_colors (){
        
        return this.colors;
        
    }
    public ArrayList<String> get_shapes (){
        
        return this.shapes;
        
    }
    
    public int get_number_of_shapes (){
        
        return this.Number_of_shapes;
        
    }
    
   /** 
    * deals out the shapes should be based on this.N
    * @return {Color, Shape}
    */
   public String[] deal(){
        
       int randomColor = ran_num.nextInt(this.colors.size());
       int randomShape = ran_num.nextInt(this.shapes.size());
      
       String Return_array[] = {this.colors.get(randomColor), this.shapes.get(randomShape)};
       
       int name = this.shapes_delt;
      
       this.shape_map.put(name, Return_array);
       
       this.shapes_delt++;
       
       return Return_array;
          
   }
    
   
   public String shuffle(String ls){

      String[] ls1 = ls.split(",");
       
   
      List<String> list = new ArrayList<>(Arrays.asList(ls1));
      //int i = list.size();
     // list.remove(i-1);
      Collections.shuffle(list);
        
            
           String ret = "";
           for(String str: list){
               ret = ret + " " + str + "(s)"  + ",";
           }
           
           return ret;
 }
  
   public String What_was_dealt(){
      
       Map<String, Integer> dealt_map = new HashMap<>();
       String[] k = {};
       Integer i = 0;
       String nkey = "";
       String ret = "";
       String shuffle = "";
       
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            //k = this.shape_map.get(pair.getKey());
            
       
            // test = G.What_was_dealt();
          Iterator it = this.shape_map.entrySet().iterator();
          
          while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //System.err.println(pair.getKey() + " = " + pair.getValue());
                            
           k = this.shape_map.get(pair.getKey());
           nkey = k[0] + " " + k[1];
           
            if (dealt_map.containsKey(nkey)){
            
                i = dealt_map.get(nkey) + 1;
                //System.err.println("hello");
                dealt_map.put(nkey,i);
            
            }
             else{
                //System.err.println("nooooooooo");
                dealt_map.put(nkey, 1);
            
            }
            
             it.remove(); 
    }
       
          
              Iterator it2 = dealt_map.entrySet().iterator();
                    while (it2.hasNext()) {
                         Map.Entry pair = (Map.Entry)it2.next();
                            //System.err.println(pair.getKey() + " = " + pair.getValue());
                            shuffle = shuffle + pair.getValue() +  " " + pair.getKey() + ",";
                            it2.remove(); 
                        }
                    
          ret = shuffle(shuffle);
          ret = ret.substring(0, ret.length()-1);
          return "There are: " + ret;  
   }
    
       
   /**
    * Decides whether or not to continue the game
    * @return boolean 
    */
   public boolean Continue_Game(){
       
       if(this.trial < 3 ){

           this.trial++;
           return true;
           
       }
       else{

           return false;

       }
       
   }
   
   /**
    * this gets the current trial number 
    * 
    * @return this.trial  
    */
   public int get_trial(){
       
       return this.trial;
   
   }
   
  
   public void reset_trials(){
       
       this.trial = 0;
       //this.current_shape = 0;
       this.shape_map.clear();
       
   }
   /**
    * add points to the score
    * 
    * @param points 
    */
   public void add_points( int points ){
       
       this.score = this.score+points;
   }
   
   /**
    * gets the game score
    * 
    * @return this.score
    */
   public int get_score( ){
       
       return this.score;
   
   }
   
   public void reset_score() {
       
       this.score = 0;

   }
   
   /* GAMER INPUT */
   
   public boolean user_guess(int i, String color, String shape){
       
       String guess = color + "," + shape;
       
       String[] guess_array = guess.split(",");
       
       String[] cs = this.shape_map.get(i);
       
       if(Arrays.equals(cs, guess_array)){
           
           return true;
       
       }
       
       else{
        
           return false;
           
       }
 }
   
   
 
    
   
    @Override 
    public int compareTo(Game other) throws IllegalArgumentException{
            if (this.Number_of_shapes != other.Number_of_shapes){
            throw new IllegalArgumentException ("Number of shapes not equal!");
        } 

        if(this.score > other.score){
            return this.score;
        }
        
        
        else if(this.score == other.score){
            return 0;
        }

        else{
            return -5;
        }
   
   
    }
}
