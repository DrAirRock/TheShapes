package application.resources;

import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.event.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class IlluminatiConfirmed extends Application{
    
    public void start(Stage primaryStage){
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        
        StackPane other = new StackPane();
        other.setAlignment(Pos.CENTER);
        
        StackPane stack = new StackPane();
        String imagePath = "/application/resources/Illuminati-Logo.png";
        
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        image.setFitHeight(100);
        image.setFitWidth(100);
        
        
        Rectangle rectangle = new Rectangle(200, 200);
        
        other.getChildren().add(image);
        other.getChildren().add(stack);
        stack.getChildren().add(rectangle);
        
      
        Button btn = new Button("ANIMATE!");
        btn.setMinHeight(75);
        btn.setFont(Font.font("", FontWeight.BOLD, 22));
        
        
        root.getChildren().add(other);
        root.getChildren().add(btn);
        
        
        btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                RotateTransition rotate = new RotateTransition();
                rotate.setFromAngle(0);
                rotate.setToAngle(360);
                rotate.setDuration(new Duration(600));
                rotate.setNode(stack);
                
                rotate.setInterpolator(Interpolator.LINEAR);
                rotate.setCycleCount(Timeline.INDEFINITE);
                rotate.play();
                
                TranslateTransition translate = new TranslateTransition();
                translate.setToY(-300);
                //translate.setFromY(rectangle.getY());
                translate.setDuration(new Duration(5000));
                translate.setNode(rectangle);
                translate.play();
            }
        });
        
        
        
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args){
        launch();
    }
}
