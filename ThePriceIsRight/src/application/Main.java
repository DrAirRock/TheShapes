package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


/**
 *
 * @author damon
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage){
       try{
        primaryStage.setTitle("The Shapes are Right!");
        String css = this.getClass().getResource("/application/res/style.css").toExternalForm();
        
        VBox root = new VBox();
        
        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(css);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
          } catch(Exception e) {
			e.printStackTrace();
        }
        
    }
    
    
    public static void main(String[] args){
        launch();
    }
    
}
