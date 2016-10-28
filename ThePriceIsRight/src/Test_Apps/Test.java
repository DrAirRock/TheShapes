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

/**
 *
 * @author sognefej
 */
public class Test extends Application {
    
    @Override
    public void start(Stage primaryStage) {      
        
    try{
        
    String[] colors = {"Red" , "Blue" , "Green" , "Black"};
    String[] shapes = {"circle" , "square" , "rectangle" , "triangle"};
    
    
    Game G = new Game(5);
    //Game G2 = new Game(4);
 
    G.set_colors(colors);
    G.set_shapes(shapes);
    
    G.remove_color("Red");
    G.remove_shape("triangle");
    
    G.add_color("Orange");
    G.add_shape("octogon");
 
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
         
      
        Button btn = new Button();
        btn.setText("test deal");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
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
        
                        
    
        VBox root = new VBox();
        root.getChildren().add(btn);
        root.getChildren().add(N);
        root.getChildren().add(Trial);
        root.getChildren().add(Score);
        
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
