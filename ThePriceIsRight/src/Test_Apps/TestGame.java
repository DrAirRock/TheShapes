/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Apps;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import thepriceisright.Game;
import java.util.*;
import java.lang.String;

/**
 *
 * @author sognefej
 */
public class TestGame extends Application {
    
    @Override
    public void start(Stage primaryStage) {      
        
    try{
        
    
   
     
     String[] colors_array = {"Red" , "Blue" , "Green" , "Black"};
     String[] shapes_array = {"circle" , "square" , "rectangle" , "triangle"};  
     
    ArrayList<String> colors = new ArrayList(Arrays.asList(colors_array));
    ArrayList<String> shapes = new ArrayList(Arrays.asList(shapes_array));
  
    
    
    Game G = new Game(5);
    
  
   
    
    //Game G2 = new Game(4);
 
    G.set_colors(colors);
    G.set_shapes(shapes);
    
    G.remove_color("Red");
    G.remove_shape("triangle");
    
    G.add_color("Orange");
    G.add_shape("octogon");
    ArrayList<String> co = new ArrayList(Arrays.asList(colors_array));
      // System.out.println(delt[i].toString());
         TextField N = new TextField(); // Text Field
         N.setEditable(false);
         N.setText("" + G.get_number_of_shapes());
         
         TextField Trial = new TextField(); // Text Field
         Trial.setEditable(false);
         Trial.setText("" + G.get_trial());
         
         TextField Score = new TextField(); // Text Field
         Score.setEditable(false);
         Score.setText("" + G.get_score());
         
         TextField Guess = new TextField(); // Text Field
         Score.setEditable(false);
         Score.setText("");
         
                  
         TextField What = new TextField(); // Text Field
         What.setEditable(false);
         What.setText("");
         
      
        Button btn = new Button();
        btn.setText("test deal");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                 String test = "hello, you, are , coooool"; 
                 System.out.println(test);
                 String print = G.shuffle(test);
                 System.out.println(print);
                
                if(G.Continue_Game()){
                G.add_points(7);
                String[] delt = G.deal();
                System.out.println(delt[0]);
                System.out.println(delt[1] + "\n");
                Trial.setText("" + G.get_trial());
                Score.setText("" + G.get_score());
                }   else{
                Trial.setText("out of turns");
            
                }         
        }
        });
        
        Button btn2 = new Button();
        btn2.setText("test guess");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                 String guess = Guess.getText();
                 String[] guess_array = guess.split(" ");
                 System.out.println(guess_array[0]);
                 System.out.println(guess_array[1]);
               
                if(G.user_guess(0 , guess_array[0] , guess_array[1] )){
                    
                    Trial.setText("Yup");
               
                }   else{ 
                    
                    //G.add_points(7);
            
                    Trial.setText("Nope");

                }      
            
        }
        });
    
        
        
        Button btn3 = new Button();
        btn3.setText("test Dealt");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
        
                What.setText(G.What_was_dealt());
            
          }
      
        });
        
        VBox root = new VBox();
        root.getChildren().add(btn);
        root.getChildren().add(N);
        root.getChildren().add(Trial);
        root.getChildren().add(Score);
        root.getChildren().add(Guess);
        root.getChildren().add(What);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
            }catch(IllegalArgumentException ne){
        
        System.err.println(ne.getMessage());
        
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
