

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
public class Game{
    
    protected static Random ran_num = new Random();
    
    
    final private int MinN = 3;
    final private int MaxN = 7;
    private int N = -1;
    private int trial = 0;
    private int score = 0;
    private String[] colors;
    private String[] shapes;

    
    /**
     * This makes a new instance of a game 
     * 
     * @param N
     * @throws IllegalArgumentException 
     */
    public Game (int N) throws IllegalArgumentException{
        
        if (N % 2 == 0){
            throw new IllegalArgumentException ("Integer Must Be odd");
        }
        
        else if((N < this.MinN) || (N > this.MaxN)){
            
            throw new IllegalArgumentException ("The interger must be bewtwen " + this.MinN + " and " + this.MaxN);
            
        }
        else{
            this.N = N;
        }
    
    }
    
    public void set_colors (String colors[]){
        
        this.colors = colors;
        
    }
    
    public void set_shapes (String shapes[]){
        
        this.shapes = shapes;
        
    }
   /** 
    * deals out the shapes
    * @return 
    */
   public String[] deal(){
       
       int randomColor = ran_num.nextInt((this.colors.length - 0 ) + 0) + 1;
       int randomShape = ran_num.nextInt((this.shapes.length - 0 ) + 0) + 1;
      
       String Return_array[] = {colors[randomColor], shapes[randomShape]};
       
       return Return_array;
          
   }
    
   /**
    * Decides wheather or not to continue the game
    * @return boolean 
    */
   public boolean Continue_Game(){
       
       this.trial++;
       
       if(this.trial < 3 ){
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
   
   /**
    * add points to the score
    * 
    * @param points 
    */
   public void add_points( int points ){
       
       this.score = points;
   }
   
   /**
    * gets the game score
    * 
    * @return this.score
    */
   public int get_score( ){
       
       return this.score;
   
   }
    
}
