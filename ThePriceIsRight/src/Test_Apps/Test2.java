package Test_Apps;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;

/**
 *
 * @author damon
 */
public class Test2 extends Application{
    
    public void start(Stage primaryStage){
        String hex = "FF0000";
    
        Color color = Color.web(hex);
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        
        Circle circle = new Circle(100f);
        circle.setFill(color);
        
        root.getChildren().add(circle);
        
        Scene scene = new Scene(root,700,700);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch();
    }
    
    
    
    
}
