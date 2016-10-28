/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Apps;


import thepriceisright.*;
import java.util.*;

/**
 *
 * @author sognefej
 */

public class Game_Logic_test {
    public static void Main(String args[]){
        
    
    String[] colors = {"Red" , "Blue" , "Green" , "Black"};
    String[] shapes = {"circle" , "square" , "rectangle" , "triangle"};
    
    Game G = new Game(5);
    //Game G2 = new Game(4);
    
    G.set_colors(colors);
    G.set_shapes(shapes);
    
    
   String[] delt = G.deal();
   
     for (int i=0; i < delt.length; i++ ){
       
                 System.out.println(delt[i].toString());
                         
     }
    
    }
}
