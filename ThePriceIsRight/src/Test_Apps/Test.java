/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Apps;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        
           
    String[] colors = {"Red" , "Blue" , "Green" , "Black"};
    String[] shapes = {"circle" , "square" , "rectangle" , "triangle"};
    
    Game G = new Game(5);
    //Game G2 = new Game(4);

    G.set_colors(colors);
    G.set_shapes(shapes);


 
      // System.out.println(delt[i].toString());

        Button btn = new Button();
        btn.setText("test deal");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String[] delt = G.deal();
                System.out.println(delt[0]);
                System.out.println(delt[1] + "\n");
            }
        });
        
                        
    
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
