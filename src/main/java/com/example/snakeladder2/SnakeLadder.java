package com.example.snakeladder2;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

public class SnakeLadder extends Application {

    // Declaring global Pane
    Pane root;

    // Declaring global Button
    Button player1Button;
    Button player2Button;
    Button startButton;

    // Declaring  global ImageView
    ImageView boardImage;

    // Declaring global variables
    public static final int tileSize = 40, height = 10, width = 10;
    int lowerLine = tileSize*height;
    public int diceValue;
    boolean firstPlayerTurn = true, secondPlayerTurn = false, gameStarted = false;
    String player1Name = "Player 1";
    String player2Name = "Player 2";

    // Declaring Label
    Label rolledDiceValueLabel;

    // Declaring global object reference variable
    Player fisrtPlayer;
    Player secondPlayer;

    Pane createContent() {

        // Initializing Pane and setting its dimension
        root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+50);

        // Creating 10X10 matrix
        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        // Adding image
        Image img = new Image("C:\\Users\\91800\\IdeaProjects\\SnakeLadder2\\src\\main\\istockphoto-531466314-1024x1024.jpg");
        boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitHeight(tileSize*height);
        boardImage.setFitWidth(tileSize*width);

        // Initializing Button
        player1Button = new Button("Player 1");
        player2Button = new Button("Player 2");
        startButton = new Button("Start Game");

        // Event handling for player1Button
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();
                        fisrtPlayer.movePlayer(diceValue);
                        if(fisrtPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(fisrtPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startButton.setDisable(false);
                            startButton.setText("Start Game");
                        }
                    }
                }
                firstPlayerTurn = false;
                secondPlayerTurn = true;

            }
        });

        // Event handling for player2Button
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(secondPlayerTurn){
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if(secondPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(secondPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startButton.setDisable(false);
                            startButton.setText("Start Game");
                        }
                    }
                    secondPlayerTurn = false;
                    firstPlayerTurn = true;
                }

            }
        });

        // Event handling for startButton
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                firstPlayerTurn = true;
                secondPlayerTurn = false;
                startButton.setText("Game Ongoing");
                startButton.setDisable(true);
                root.getChildren().clear();
                root.getChildren().addAll(playerName());
            }
        });

        // Setting position of buttons
        player1Button.setTranslateX(20);
        player1Button.setTranslateY(lowerLine+15);
        player2Button.setTranslateX(90);
        player2Button.setTranslateY(lowerLine+15);
        startButton.setTranslateX(300);
        startButton.setTranslateY(lowerLine+15);

        // Initializing label and setting its position
        rolledDiceValueLabel = new Label("Let's Play !!");
        rolledDiceValueLabel.setTranslateX(160);
        rolledDiceValueLabel.setTranslateY(lowerLine+15);

        // Adding all the element in th frame
        root.getChildren().addAll(boardImage, player1Button, player2Button, rolledDiceValueLabel, startButton);

        return root;
    }

    // Generating dice value
    private void setDiceValue() {
        // Math.random generate value between 0 to 1
        diceValue = (int)(Math.random()*6+1);
        rolledDiceValueLabel.setText("Dice Value : " +diceValue);
    }

    // Setting players name
    private GridPane playerName() {

        // Initializing TextField, Label and Button
        TextField player1textField = new TextField();
        TextField player2textField = new TextField();
        Label player1Label = new Label("Enter Player 1 Name");
        Label player2Label = new Label("Enter Player 2 Name");
        Button playButton = new Button("Let's Play!");

        // Event handling for playButton
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!player1textField.getText().isEmpty() || !player1textField.getText().isBlank()){
                    player1Name = player1textField.getText();
                }

                if(!player2textField.getText().isEmpty() || !player2textField.getText().isBlank()){
                    player2Name = player2textField.getText();
                }

                // Creating object of Player class
                fisrtPlayer = new Player(tileSize-4, Color.BLACK, player1Name);
                secondPlayer = new Player(tileSize-10, Color.WHITE, player2Name);

                player1Button.setText(player1Name);
                player2Button.setText(player2Name);

                root.getChildren().clear();
                root.getChildren().addAll(boardImage, player1Button, player2Button, fisrtPlayer.getCoin(), secondPlayer.getCoin(), rolledDiceValueLabel, startButton);

            }
        });

        // Creating new pane, setting its dimension, alignment and style
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(width*tileSize, height*tileSize+50);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color: #87CEEB");
        gridPane.setAlignment(Pos.CENTER);

        // Adding Label, Button, TextField in pane
        gridPane.add(player1Label, 0, 0);
        gridPane.add(player1textField, 1, 0);
        gridPane.add(player2Label, 0, 1);
        gridPane.add(player2textField, 1, 1);
        gridPane.add(playButton, 1, 2);

        return gridPane;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}