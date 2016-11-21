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
import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Cylinder;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.animation.ParallelTransition;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;


/**
 *
 * @author Damon Gwinn
 */
public class Main extends Application{
    
    //Used to iterate through the shapePanes in playGame()
    private int iteration;
    
    
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
            Scene scene = new Scene(root, 1300, 650);
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
        root.setSpacing(20);
        
        
        Label welcomeMessage = new Label("Welcome to The Shapes are Right!");
        welcomeMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 36");
        welcomeMessage.setTextFill(Color.GREEN);
        
        //Configuration for the rules field
        VBox rulesField = new VBox();
        rulesField.setMinHeight(200);
        rulesField.setMaxWidth(1200);
        rulesField.setAlignment(Pos.CENTER);
        
        
        //Configuration for the rules text inside rules field
        Label rules = new Label();
        rules.setText("You are to pick the number of shapes (3, 5, or 7) and the shapes"
                + " (color and shape type). Once this is selected the game begins.\n\n You, the contestant, are "
                + "to try and guess what shape is behind the curtain (see below). The shapes behind the "
                + "curtain are random combinations of your selected shape types and colors.\n\n If you guess "
                + "the shape correctly, you get a point! If you are wrong, you get nothing...\n\n To give you a "
                + "bit of edge, the combination of shapes (i.e. 1 Red Triangle, 2 Blue Cylinders, etc...) is"
                + " given to you and you are allowed to change your guess for the following shapes after each"
                + " subsequent reveal.\n\n You have 3 trials to get as many points as possible. \nGood luck!");
        
        rules.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        rules.setWrapText(true);
        
        rules.setTextAlignment(TextAlignment.CENTER);
        
        //Adds rules to rulesField
        rulesField.getChildren().add(rules);
        
        
        
        //This will be the "cover" that goes over the shapes
        Circle cover = new Circle(70);
            
        //Mixes different gradients together
        cover.setStyle("-fx-fill:" +
                "linear-gradient(#ffd65b, #e68400)," +
                "linear-gradient(#ffef84, #f2ba44)," +
                "linear-gradient(#ffea6a, #efaa22)," +
                "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%)," +
                "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));");
        
        //Label that tells the user what the cover is
        Label coverExample = new Label("This is the curtain that goes over the shapes (Not the actual shapes)");
        coverExample.setTextFill(Color.RED);
        coverExample.setFont(Font.font("", FontWeight.BOLD, 24));
        
        
        //Config for the continue button
        Button continueBtn = new Button();
        continueBtn.setText("Continue");
        continueBtn.setFont(Font.font("", FontWeight.BOLD, 26));
        continueBtn.setMinHeight(60);
        
        //Adds to root
        root.getChildren().add(welcomeMessage);
        root.getChildren().add(rulesField);
        root.getChildren().add(continueBtn);
        root.getChildren().add(cover);
        root.getChildren().add(coverExample);
        
        
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
        viewColorsList.setMinHeight(142);
        viewColorsList.setMinWidth(150);
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
        viewShapesList.setMinHeight(142);
        viewShapesList.setMinWidth(150);
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
        root.setSpacing(10);
        
        //If the 3 trials have not been used
        if(game.Continue_Game()){

            this.iteration = 0;

            //Gets the number of shapes
             int numShapes = game.get_number_of_shapes();

            //Getting a dealing of shapes to use with the game
            ArrayList<String[]> shapesDealt = new ArrayList<>();      
            for (int i=0; i<numShapes; i++){
                shapesDealt.add(game.deal());
            }

            //Setting up the box that holds the shapes. Size is based on number of shapes
            HBox shapeBox = new HBox();
            shapeBox.setAlignment(Pos.CENTER);
            if (numShapes == 3){
                shapeBox.setSpacing(150);
            }
            else if (numShapes == 5){
                shapeBox.setSpacing(80);
            }

            else if (numShapes == 7){
                shapeBox.setSpacing(30);
            }


            //Creates an array of stack panes. The size is determined by number of shapes
            ArrayList<VBox> shapePanes = new ArrayList();
            for (int i=0; i<numShapes; i++){
                shapePanes.add(new VBox(30));
            }

            //Drawes the shapes onto the screen with the covers
            //Covers are returned in an ArrayList for use with revealShapes method
            ArrayList<Circle> covers = drawShapes(shapePanes, shapesDealt);


            ObservableList<String> colorsList = FXCollections.observableList(game.get_colors());
            ObservableList<String> shapesList = FXCollections.observableList(game.get_shapes());

            ArrayList<ListView> colorSelections = new ArrayList<ListView>();
            ArrayList<ListView> shapeSelections = new ArrayList<ListView>();
            ArrayList<Label> statusLabels = new ArrayList<Label>();


            //Creates two list views displaying the possible color and shapes options
            //This is done for each dealt shape
            for (int i = 0; i<numShapes; i++){

                VBox listHolder = new VBox(10);
                listHolder.setAlignment(Pos.CENTER);

                ListView<String> viewColorsList = new ListView<String>();      
                viewColorsList.setItems(colorsList);
                viewColorsList.setMaxHeight(142);
                viewColorsList.setMaxWidth(150);
                viewColorsList.setEditable(false);

                ListView<String> viewShapesList = new ListView<String>();      
                viewShapesList.setItems(shapesList);
                viewShapesList.setMaxHeight(142);
                viewShapesList.setMaxWidth(150);
                viewShapesList.setEditable(false);

                Label statusLabel = new Label("Guess");
                statusLabel.setFont(Font.font("", FontWeight.BOLD, 22));

                //for each shape pane
                VBox shapePane = shapePanes.get(i);

                listHolder.getChildren().add(viewColorsList);
                listHolder.getChildren().add(viewShapesList);
                listHolder.getChildren().add(statusLabel);

                shapePane.getChildren().add(listHolder);

                colorSelections.add(viewColorsList);
                shapeSelections.add(viewShapesList);
                statusLabels.add(statusLabel);
            }
            
            
            //Displays non-sequential types and numbers of shapes
            Label displayShapeCombo = new Label();
            displayShapeCombo.setFont(Font.font("", FontWeight.BOLD, 16));
            displayShapeCombo.setText(game.What_was_dealt());
            displayShapeCombo.setWrapText(true);

            //Button that the user presses to "lock in" guesses
            Button guessBtn = new Button("Lock In");
            guessBtn.setMinHeight(50);
            guessBtn.setMinWidth(100);
            guessBtn.setFont(Font.font("", FontWeight.BOLD, 20));



            //Holds the trial and score labels
            HBox scoreTrialBox = new HBox(50);
            scoreTrialBox.setAlignment(Pos.CENTER);

            //Label displaying the trial number to the user
            Label trialLabel = new Label();
            trialLabel.setFont(Font.font("", FontWeight.BOLD, 24));
            trialLabel.setText("Trial: " + game.get_trial());

            //Label displaying the score to the user
            Label scoreLabel = new Label();
            scoreLabel.setFont(Font.font("", FontWeight.BOLD, 24));
            scoreLabel.setText("Score: " + game.get_score());

            //Adds labels to box
            scoreTrialBox.getChildren().add(trialLabel);
            scoreTrialBox.getChildren().add(scoreLabel);
            
            //Holds the cancelButton and guessBtn
            HBox buttonHolder = new HBox(100);
            buttonHolder.setAlignment(Pos.CENTER);
            
            
            //Quits the game and returns to the beginning
            Button cancelButton = new Button("Restart");
            cancelButton.setMinHeight(50);
            cancelButton.setMinWidth(100);
            cancelButton.setFont(Font.font("", FontWeight.BOLD, 20));
            


            //Button that allows user to continue to the next trial
            //Is only displayed after all shapes have a guess (see event handler for guessBtn)
            Button continueBtn = new Button("Continue");
            continueBtn.setMinHeight(50);
            continueBtn.setMinWidth(100);
            continueBtn.setFont(Font.font("", FontWeight.BOLD, 20));
            
            buttonHolder.getChildren().add(guessBtn);
            buttonHolder.getChildren().add(cancelButton);


            //Displays errors to user
            Label errorLabel = new Label();
            errorLabel.setTextFill(Color.RED);
            errorLabel.setFont(Font.font("", FontWeight.BOLD, 16));


            //Adds all shape panes to the shape box
            shapeBox.getChildren().addAll(shapePanes);

            //Adds to root     
            root.getChildren().add(shapeBox);
            root.getChildren().add(displayShapeCombo);
            root.getChildren().add(buttonHolder);
            root.getChildren().add(scoreTrialBox);
            root.getChildren().add(errorLabel);

            
            //Returns the user to the rules screen
            cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    showRules(root);
                }
            });
            
            
            //Runs code that resets the dealing while keeping trial and score the same   OR
            //Runs code that finishes the game (depending on if the trials are up
            //This is done using recursion
            continueBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    playGame(root, game);
                }
            });


            //Runs code that checks that the user provided guesses
            //Runs code that tests the user's guesses and increments score respectively
            //Runs code that ensures trial logic is upheld
            guessBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    boolean continueEvent = true;

                    //Checks that current shapePane has a selection
                    ListView colorSelection = colorSelections.get(iteration);
                    ListView shapeSelection = shapeSelections.get(iteration);

                    if(colorSelection.getSelectionModel().getSelectedItems().isEmpty() ||
                            shapeSelection.getSelectionModel().getSelectedItems().isEmpty() ){
                        
                         errorLabel.setText("Must select a color and shape type for current shape!");
                         continueEvent = false;
                       }

                    //Runs if the user provided a guess for every shape
                    if (continueEvent){
                        errorLabel.setText("");

                        //Gets all available information into variables

                        String colorGuess = colorSelection.getSelectionModel().getSelectedItem().toString();
                        String shapeGuess = shapeSelection.getSelectionModel().getSelectedItem().toString();

                        colorSelection.setItems(FXCollections.observableList(Collections.singletonList(colorGuess)));
                        shapeSelection.setItems(FXCollections.observableList(Collections.singletonList(shapeGuess)));

                        Label statusLabel = statusLabels.get(iteration);
                        
                        //Reveals the shape using animation
                        revealShape(covers.get(iteration));

                        //System.out.println(colorGuess);
                        //System.out.println(shapeGuess);

                        //If the user guessed correctly
                        if (game.user_guess(iteration, colorGuess, shapeGuess)){
                            game.add_points(1);
                            scoreLabel.setText("Score: " + game.get_score());

                            //System.out.println("Correct");

                            statusLabel.setTextFill(Color.GREEN);
                            statusLabel.setFont(Font.font("", FontWeight.BOLD, 22));
                            statusLabel.setText("CORRECT!");                     
                        }

                        //If the user was wrong
                        else{
                            //System.out.println("Wrong");

                            statusLabel.setTextFill(Color.RED);
                            statusLabel.setFont(Font.font("", FontWeight.BOLD, 22));
                            statusLabel.setText("WRONG...");    
                        }

                        //Iteration is just the current shape we are on
                        iteration++;

                        //If all shapes have been guessed on, remove guessBtn and add continueBtn
                        //CancelButton is moved to be horizontal to continueBtn
                        if (iteration >= numShapes){
                            buttonHolder.getChildren().remove(guessBtn);
                            buttonHolder.getChildren().remove(cancelButton);
                            
                            buttonHolder.getChildren().add(continueBtn);
                            buttonHolder.getChildren().add(cancelButton);
                            
                        }
                    }

                }
            });
        }
        
        //User is out of trials
        else{
            finalScreen(root, game);
        }
    
    }
    
    private ArrayList<Circle> drawShapes(ArrayList<VBox> shapePanes, ArrayList<String[]> shapes){
        
        //This is returned to the calling method
        //Used in another method to "reveal" the shapes using animation
        ArrayList<Circle> covers = new ArrayList<Circle>();
        
        //Draws shapes
        int limit = shapePanes.size();
        for (int i=0; i<limit; i++){
            
            //The stack pane that stacks the shapes with the cover
            StackPane shapeWithCover = new StackPane();
            
            //Gets the current shape pane to be drawn on
            VBox shapePane = shapePanes.get(i);
            String color = shapes.get(i)[0]; //position 0 is color
            String shape = shapes.get(i)[1];//position 1 is shape
            
            //Draws shapes for Illuminati (NOTE: These are images edited in GIMP)
            if(shape == "Illuminati"){
                String imagePath = "/application/resources/Illuminati-" + color + ".png";             
                ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
                image.setFitHeight(75);
                image.setFitWidth(75);
                
                shapeWithCover.getChildren().add(image);
            }
            
            //Draws shapes for Cylinders
            else if (shape == "Cylinder"){
                Cylinder cylinder = new Cylinder(30, 75);
                
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
                
                shapeWithCover.getChildren().add(cylinder);
                
            }
            
            //Draws shapes for Circles
            else if (shape =="Circle"){
                Circle circle = new Circle(50);
                if (color == "Yellow"){
                    circle.setStyle("-fx-fill: gold");
                }
                else{
                    circle.setStyle("-fx-fill: " + color);
                }
                
                shapeWithCover.getChildren().add(circle);
                
            }
            
            //Draws shapes for squares
            else if (shape == "Square"){
                Rectangle rectangle = new Rectangle(75, 75);
                if (color == "Yellow"){
                    rectangle.setStyle("-fx-fill: gold");
                }
                else{
                    rectangle.setStyle("-fx-fill: " + color);
                }
                
                shapeWithCover.getChildren().add(rectangle);
            }
            
            
            //This will be the "cover" that goes over the shapes
            Circle cover = new Circle(60);
            
            //Mixes different gradients together
            cover.setStyle("-fx-fill:" +
                    "linear-gradient(#ffd65b, #e68400)," +
                    "linear-gradient(#ffef84, #f2ba44)," +
                    "linear-gradient(#ffea6a, #efaa22)," +
                    "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%)," +
                    "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));");
            
            //Adds to cover ArrayList
            covers.add(cover);
            
            //Adds cover on top of the shape
            shapeWithCover.getChildren().add(cover);
            
            //Adds to current shapePane
            shapePane.getChildren().add(shapeWithCover);
            
        }
      return covers;
    }
    
    
    private void revealShape(Circle cover){
        //The cover is going to translate while fading from view
        
        ParallelTransition fullAnimation = new ParallelTransition();
        
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(new Duration(1000));
        translate.setNode(cover);
        translate.setToY(-100);
        translate.setInterpolator(Interpolator.LINEAR);
        
        FadeTransition fade = new FadeTransition();
        fade.setDuration(new Duration(1000));
        fade.setNode(cover);
        fade.setToValue(0);
        fade.setInterpolator(Interpolator.LINEAR);
        
        fullAnimation.getChildren().add(translate);
        fullAnimation.getChildren().add(fade);
        fullAnimation.play();
        
    }
    
    
    private void finalScreen(VBox root, Game game){
        
        root.setSpacing(100);
        
        //Tells the user their final score
        Label finalScore = new Label("FINAL SCORE: " + game.get_score());
        finalScore.setFont(Font.font("", FontWeight.BOLD, 75));
        finalScore.setTextFill(Color.GREEN);
        
        //Tells the user they are out of trials
        Label finalPrompt = new Label("You have run out of trials");
        finalPrompt.setTextFill(Color.RED);
        finalPrompt.setFont(Font.font("", FontWeight.BOLD, 30));
        
        //Holds the reset and quit buttons
        HBox buttonBox = new HBox(50);
        buttonBox.setAlignment(Pos.CENTER);
        
        //Button that returns user to rules screen
        Button resetBtn = new Button("Restart");
        resetBtn.setMinHeight(50);
        resetBtn.setMinWidth(100);
        resetBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        
        //Quits the entire game
        Button quitBtn = new Button("Quit");
        quitBtn.setMinHeight(50);
        quitBtn.setMinWidth(100);
        quitBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        
        buttonBox.getChildren().add(resetBtn);
        buttonBox.getChildren().add(quitBtn);
        
        
        //Adds to root
        root.getChildren().add(finalScore);
        root.getChildren().add(finalPrompt);
        root.getChildren().add(buttonBox);
        
        
        //Returns the user to rules screen
        resetBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                showRules(root);
            }
        });
        
        
        //Quits the entire game
        quitBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                Platform.exit();
            }
        });
    }
    
    public static void main(String[] args){
        launch();
    }
    
}
