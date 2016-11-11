package application;

import thepriceisright.*; //Game logic: AUTHOR = Eric Sognefest

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.beans.binding.Bindings;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.FontWeight;
import java.util.Iterator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Cylinder;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;


/**
 *
 * @author Damon Gwinn
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage){
        try{
            //Gets the css file
            String css = this.getClass().getResource("/application/resources/style.css").toExternalForm();

            primaryStage.setTitle("The Shapes are Right!");

            //Creates the root object
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);

            //Shows the first screen, showRules
            showRules(root);

            //Below sets the css and other root properties
            Scene scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(css);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void showRules(VBox root){
        //Clears root and sets spacing
        root.getChildren().clear();
        root.setSpacing(30);
        
        //Configuration for the rules field
        VBox rulesField = new VBox();
        rulesField.setMinHeight(400);
        rulesField.setMaxWidth(750);
        rulesField.setAlignment(Pos.CENTER);
        rulesField.setStyle( "-fx-background-color: linear-gradient(#FFFFFF, #FFFFEE);"
        );
        
        //Configuration for the rules text inside rules field
        Label rules = new Label();
        rules.setText("PlaceHolder");
        rules.setStyle("-fx-font-weight: bold; -fx-font-size: 30");
        
        //Adds rules to rulesField
        rulesField.getChildren().add(rules);
        
        
        //Config for the continue button
        Button continueBtn = new Button();
        continueBtn.setText("Continue");
        continueBtn.setFont(Font.font("", FontWeight.BOLD, 26));
        continueBtn.setMinHeight(60);
        
        //Adds to root
        root.getChildren().add(rulesField);
        root.getChildren().add(continueBtn);
        
        
        //Handler for continueBtn. Just moves to the next screen, selectNumShapes.
        continueBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                selectNumShapes(root);
            }
         });
        
    }
    
    
    private void selectNumShapes(VBox root){
        //Clears root
        root.getChildren().clear();
        root.setSpacing(30);
        
        
        //Prompt asking the user to select number of shapes
        Label prompt = new Label();
        prompt.setText("Select number of shapes:");
        prompt.setStyle("-fx-font-weight: bold; -fx-font-size: 40");
        
        
        //Box holding the three buttons horizontally
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(80);
        
        
        //Below are Configs for the 3 button, 5 button, and 7 button respectively
        
        Button threeBtn = new Button();
        threeBtn.setText("3");
        threeBtn.setFont(Font.font(80));
        threeBtn.setMinSize(250, 250);
        
        Button fiveBtn = new Button();
        fiveBtn.setText("5");
        fiveBtn.setFont(Font.font(80));
        fiveBtn.setMinSize(250, 250);
        
        Button sevenBtn = new Button();
        sevenBtn.setText("7");
        sevenBtn.setFont(Font.font(80));
        sevenBtn.setMinSize(250, 250);
        
        
        //Adds buttons to button box
        buttonBox.getChildren().add(threeBtn);
        buttonBox.getChildren().add(fiveBtn);
        buttonBox.getChildren().add(sevenBtn);
        
        //Adds to root
        root.getChildren().add(prompt);
        root.getChildren().add(buttonBox);
        
        
        //Below handlers set number of sides for game to 3, 5, and 7 respectively
        //All handlers move on to shapesAndColors screen
        
        threeBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //The game logic from package thepriceisright
                Game game = new Game(3);
                
                shapesAndColors(root, game);
            }
        });
        
        
        fiveBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //The game logic from package thepriceisright
                Game game = new Game(5);
                
                shapesAndColors(root, game);
            }
        });
        
        
        sevenBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //The game logic from package thepriceisright
                Game game = new Game(7);
                
                shapesAndColors(root, game);
            }
        });
        
    }
    
    
    private void shapesAndColors(VBox root, Game game){
        
        root.getChildren().clear();
        root.setSpacing(25);
        
        //Prompts the user to select colors and shapes
        Label prompt = new Label();
        prompt.setText("Select colors and types of shapes:");
        prompt.setStyle("-fx-font-weight: bold; -fx-font-size: 26");
        
        //HBox that holds the 2 list views
        HBox listHolder = new HBox();
        listHolder.setSpacing(80);
        listHolder.setAlignment(Pos.CENTER);
        
        //The available colors
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Yellow");
        colors.add("Green");
        
        //Creates an observable list that keeps track of user selections
        ObservableList<String> colorsList = FXCollections.observableList(colors);
        
        //Displays the list to the user
        //Sets the items to colors and allows for multiple selections
        ListView<String> viewColorsList = new ListView<>();      
        viewColorsList.setItems(colorsList);
        viewColorsList.setMaxHeight(142);
        viewColorsList.setMaxWidth(150);
        viewColorsList.setEditable(false);
        viewColorsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
        //Available Shapes
        ArrayList<String> shapes = new ArrayList<>();
        shapes.add("Cylinder");
        shapes.add("Circle");
        shapes.add("Square");
        shapes.add("Illuminati");
        
        //Observable list for shapes
        ObservableList<String> shapesList = FXCollections.observableList(shapes);
        
        //List view for shapes allowing for multiple selections
        ListView<String> viewShapesList = new ListView<>();
        viewShapesList.setItems(shapesList);
        viewShapesList.setMaxHeight(142);
        viewShapesList.setMaxWidth(150);
        viewShapesList.setEditable(false);
        viewShapesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
        //Adds list views to list holder
        listHolder.getChildren().add(viewColorsList);
        listHolder.getChildren().add(viewShapesList);
        
        
        //Continue Button
        Button continueBtn = new Button();
        continueBtn.setText("Continue");
        continueBtn.setFont(Font.font("",FontWeight.BOLD, 22));
        continueBtn.setMinHeight(60);
        
        
        //Gives instructions for multiple selection to user
        Label instructionLabel = new Label("NOTE: Hold ctrl (or command) and click for multiple selections");
        instructionLabel.setFont(Font.font(14));
        
        
        //Displays errors to user
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font("", FontWeight.BOLD, 26));
        
        
        //Adds to root
        root.getChildren().add(prompt);
        root.getChildren().add(listHolder);
        root.getChildren().add(continueBtn);
        root.getChildren().add(instructionLabel);
        root.getChildren().add(errorLabel);
        
        
        //Handler for clicking the Continue Button
        //  Makes sure both listViews have at least one selection and sets error field accordingly
        //  Sets selections in the game logic and continues to next screen
        continueBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //Tests if the selections are empty
                if(viewColorsList.getSelectionModel().getSelectedItems().isEmpty() ||
                        viewShapesList.getSelectionModel().getSelectedItems().isEmpty() ){                    
                    errorLabel.setText("Must select at least one color and shape");
                }
                
                //Sets selections in game logic and moves on
                else{
                    ArrayList<String> colors= new ArrayList(viewColorsList.getSelectionModel().getSelectedItems());
                    game.set_colors(colors);
                    
                    ArrayList<String> shapes = new ArrayList(viewShapesList.getSelectionModel().getSelectedItems());
                    game.set_shapes(shapes);
                    
                    playGame(root, game);
                }
            }
        });
        
    }
    
    
    private void playGame(VBox root, Game game){
        root.getChildren().clear();
        root.setSpacing(30);
        
        //Gets the number of shapes
         int numShapes = game.get_number_of_shapes();
         
        //Getting a dealing of shapes to use with the game
        ArrayList<String[]> shapes = new ArrayList<>();      
        for (int i=0; i<numShapes; i++){
            shapes.add(game.deal());
        }
        
        //Setting up the box that holds the shapes
        HBox shapeBox = new HBox();
        shapeBox.setAlignment(Pos.CENTER);
        if (numShapes == 3){
            shapeBox.setSpacing(150);
        }
        else if (numShapes == 5){
            shapeBox.setSpacing(80);
        }
        
        else if (numShapes == 7){
            shapeBox.setSpacing(50);
        }
        
        
        //Creates an array of stack panes. The size is determined by number of shapes
        ArrayList<VBox> shapePanes = new ArrayList();
        for (int i=0; i<numShapes; i++){
            shapePanes.add(new VBox(20));
        }
        
        //Drawes the shapes onto the screen (will add a cover later)
        drawShapes(shapePanes, shapes);
        
      
        
        shapeBox.getChildren().addAll(shapePanes);
        root.getChildren().add(shapeBox);
    }
    
    
    private void drawShapes(ArrayList<VBox> shapePanes, ArrayList<String[]> shapes){
        
        int limit = shapePanes.size();
        for (int i=0; i<limit; i++){
            VBox shapePane = shapePanes.get(i);
            String color = shapes.get(i)[0]; //position 0 is color
            String shape = shapes.get(i)[1];//position 1 is shape
            
            if(shape == "Illuminati"){               
                String imagePath = "/application/resources/Illuminati-" + color + ".png";             
                ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
                image.setFitHeight(110);
                image.setFitWidth(110);
                
                shapePane.getChildren().add(image);
            }
            
            else if (shape == "Cylinder"){
                Cylinder cylinder = new Cylinder(40, 100);
                
                PhongMaterial material = new PhongMaterial();
                material.setSpecularColor(Color.BLACK);
                if (color == "Red"){
                    material.setDiffuseColor(Color.RED);
                }
                else if (color == "Blue"){
                    material.setDiffuseColor(Color.BLUE);
                }
                else if (color == "Yellow"){
                    material.setDiffuseColor(Color.YELLOW);
                }
                else if (color == "Green"){
                    material.setDiffuseColor(Color.GREEN);
                }
                else{
                    System.err.println("meow");
                }
                
                cylinder.setMaterial(material);
                
                shapePane.getChildren().add(cylinder);
                
            }
            
            else if (shape =="Circle"){
                Circle circle = new Circle(55);
                if (color == "Yellow"){
                    circle.setStyle("-fx-fill: gold");
                }
                else{
                    circle.setStyle("-fx-fill: " + color);
                }
                
                shapePane.getChildren().add(circle);
                
            }
            
            else if (shape == "Square"){
                Rectangle rectangle = new Rectangle(100, 100);
                if (color == "Yellow"){
                    rectangle.setStyle("-fx-fill: gold");
                }
                else{
                    rectangle.setStyle("-fx-fill: " + color);
                }
                
                shapePane.getChildren().add(rectangle);
            }
            
            
        }
    }
    
    
    public static void main(String[] args){
        launch();
    }
    
}
