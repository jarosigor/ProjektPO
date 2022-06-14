package com.example.pacman;

import com.example.pacman.gameUtilities.GameBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class Simulation extends Application {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 900;
    private boolean gameStarted;
    private boolean debugModeOn;
    private GameBoard gameBoard;

    @Override
    public void start(Stage stage) throws RuntimeException {

        System.out.println("Weszliśmy!");
        stage.setTitle("PAC-MAN");
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameBoard = new GameBoard(gc);

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
                e -> {
                    try{
                        run(gc);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}
        ));
        tl.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(e -> {
            gameBoard.newGame();
            gameStarted = true;
        });
        stage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (debugModeOn) {
                switch(key.getCode()) {
                    case UP -> {
                        gameBoard.getPacMan().setPosY(gameBoard.getPacMan().getPosY() - 5);
                        gameBoard.getPacMan().setDirrection(0);
                    }
                    case DOWN -> {
                        gameBoard.getPacMan().setPosY(gameBoard.getPacMan().getPosY() + 5);
                        gameBoard.getPacMan().setDirrection(2);
                    }
                    case RIGHT -> {
                        gameBoard.getPacMan().setPosX(gameBoard.getPacMan().getPosX() + 5);
                        gameBoard.getPacMan().setDirrection(1);
                    }
                    case LEFT -> {
                        gameBoard.getPacMan().setPosX(gameBoard.getPacMan().getPosX() - 5);
                        gameBoard.getPacMan().setDirrection(3);
                    }
                    case ESCAPE -> {
                        gameStarted = false;
                        debugModeOn = false;
                    }
                }
            }
            else {
                switch(key.getCode()) {
                    case D -> {
                        gameBoard.newGame();
                        debugModeOn = true;
                    }
                    case ESCAPE -> {
                        gameStarted = false;
                        debugModeOn = false;
                    }
                }
            }
        });

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    public void run(GraphicsContext graphicsContext) throws IOException {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        if (gameStarted) {
            gameBoard.draw(graphicsContext);

        }
        else if (debugModeOn) {
            gameBoard.draw(graphicsContext);

        } else {
            drawStartScreen(graphicsContext);
        }

    }

    public void drawStartScreen(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphicsContext.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 32));
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.strokeText("Start on click",SCREEN_WIDTH/2, SCREEN_HEIGHT/3);
        graphicsContext.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 25));
        graphicsContext.strokeText("For debug mode press D", SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
    }

    public static void main(String[] args) {
        launch();
    }
}