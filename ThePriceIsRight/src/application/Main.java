package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 *
 * @author damon
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage){
        String css = this.getClass().getResource("/application/resources/style.css").toExternalForm();
        
        primaryStage.setTitle("The Shapes are Right!");
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        
        
        showRules(root);
        System.out.println("Test");
        
        
        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(css);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    public void showRules(VBox root){
        root.getChildren().clear();
        root.setSpacing(30);
        
        VBox rulesField = new VBox();
        rulesField.setMinHeight(300);
        rulesField.setMaxWidth(650);
        rulesField.setAlignment(Pos.CENTER);
        rulesField.setStyle( "-fx-background-color: linear-gradient(#FFFFFF, #FFFFEE);"
        );
        
        
        Label rules = new Label();
        rules.setText("Test");
        rules.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        
        rulesField.getChildren().add(rules);
        
        
        Button continueBtn = new Button();
        continueBtn.setText("Continue");
        continueBtn.setFont(Font.font(18));
        continueBtn.setMinHeight(45);
        continueBtn.setMaxWidth(110);
        
        
        root.getChildren().add(rulesField);
        root.getChildren().add(continueBtn);
    }
    
    
    public static void main(String[] args){
        launch();
    }
    
}
