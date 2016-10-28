package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;


/**
 *
 * @author damon
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage){
        String css = this.getClass().getResource("/application/resources/style.css").toExternalForm();
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(12);
        
        
        Button btn = new Button();
        btn.setMinWidth(100);
        btn.setMinHeight(50);
        btn.setFont(new Font(20));
        btn.setText("BUTTON!");
        
        Button btn2 = new Button();
        btn2.setMinWidth(100);
        btn2.setMinHeight(50);
        btn2.setFont(new Font(20));
        btn2.setText("BUTTON!");
        
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        
        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(css);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    
    public static void main(String[] args){
        launch();
    }
    
}
