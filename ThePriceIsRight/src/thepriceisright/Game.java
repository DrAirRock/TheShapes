

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thepriceisright;
import java.util.*;

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
    private int shapes_delt = 0;
    /* NOTE THAT THESE WILL BE CASE SYSETIVE*/
    private ArrayList<String> colors = new ArrayList<String>();
    private ArrayList<String> shapes = new ArrayList<String>();
    private Map<String, String[]> shape_map = new HashMap<String, String[]>();
    
    
    //private String[] colors;
    //private String[] shapes;

    
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
    public void set_colors (String colors[]){   
        
        this.colors = new ArrayList(Arrays.asList(colors));     
    
    }
    
    /**
     * set the game shapes to be used
     * @param shapes 
     */
    public void set_shapes (String shapes[]){
    
        this.shapes = new ArrayList(Arrays.asList(shapes));   
    
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
       
       String name = "Shape" + this.shapes_delt;
       
       if(!(shape_map.containsValue(Return_array))){
            this.shape_map.put(name, Return_array);
            this.shapes_delt++;
       }
       else{
           
           deal();
           
       }
    
       return Return_array;
          
   }
    
   /**
    * Decides wheather or not to continue the game
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
