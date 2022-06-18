package com.example.pacman;

import com.example.pacman.components.Booster;
import com.example.pacman.gameUtilities.GameBoard;
import com.example.pacman.gameUtilities.GameBoardCell;
import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
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
    private boolean gameOver = false;

    private boolean startScreenOn = true;
    private GameBoard gameBoard;

    @Override
    public void start(Stage stage) throws RuntimeException {
        stage.setTitle("PAC-MAN");
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameBoard = new GameBoard();
        gameBoard.newGame("map2.csv");

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(200),
                e -> {
                    try{
                        run(gc, stage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}
        ));
        tl.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(e -> {
            gameStarted = true;
            startScreenOn = false;
        });

        stage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (debugModeOn) {
                PacMan pacMan = gameBoard.getPacMan();
                switch(key.getCode()) {
                    case UP -> {
                        if (pacMan.canMove(gameBoard.getCell(new Position(pacMan.getPosX(), (pacMan.getPosY()) - 1)))) {
                            pacMan.setPosY(pacMan.getPosY() - 1);
                            pacMan.setDirection(0);
                        }
                    }
                    case DOWN -> {
                        if (pacMan.canMove(gameBoard.getCell(new Position(pacMan.getPosX(), (pacMan.getPosY()) + 1)))) {
                            pacMan.setPosY(pacMan.getPosY() + 1);
                            pacMan.setDirection(2);
                        }
                    }
                    case RIGHT -> {
                        if (pacMan.canMove(gameBoard.getCell(new Position(pacMan.getPosX() + 1, pacMan.getPosY())))) {
                            pacMan.setPosX(pacMan.getPosX() + 1);
                            pacMan.setDirection(1);
                        }
                    }
                    case LEFT -> {
                        if (pacMan.canMove(gameBoard.getCell(new Position(pacMan.getPosX() - 1, pacMan.getPosY())))) {
                            pacMan.setPosX(pacMan.getPosX() - 1);
                            pacMan.setDirection(3);
                        }
                    }
                    case ESCAPE -> {
                        gameStarted = false;
                        debugModeOn = false;
                        startScreenOn = true;
                    }
                }
            }
            else if (startScreenOn) {
                switch(key.getCode()) {
                    case D -> {
                        debugModeOn = true;
                        startScreenOn = false;
                    }
                    case ESCAPE -> {
                        stage.close();
                    }
                }
            }
            else if (gameStarted) {
                switch(key.getCode()){
                    case ESCAPE -> {
                        gameStarted = false;
                        startScreenOn = true;
                    }
                }
            }
        });

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();

    }

    public void run(GraphicsContext graphicsContext, Stage stage) throws IOException {

        if (gameStarted) {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            gameBoard.draw(graphicsContext);
            gameBoard.pick();
            gameBoard.check();
            if (gameBoard.getPacMan().getHp() < 1) {
                gameStarted = false;
                gameOver = true;
            }
        }
        else if (debugModeOn) {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            gameBoard.draw(graphicsContext);
            gameBoard.getBoard().entrySet().forEach(entry ->
                    entry.getValue()
                            .getGameComponents().stream().sorted()
                            .forEach(gameComponent -> {
                                if(!(gameComponent instanceof PacMan))
                                    gameComponent.pick();
                            }));

            PacMan pacMan = gameBoard.getPacMan();
            pacMan.eatBoosters(new Position(pacMan.getPosX(), pacMan.getPosY()));
            gameBoard.check();
            if (pacMan.getHp() < 1) {
                gameOver = true;
                debugModeOn = false;
            }

        } else if (startScreenOn) {
            drawStartScreen(graphicsContext);
        }
        if (gameBoard.nextRound) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            gameBoard.nextRound = false;
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

    public void drawGameOverScreen(GraphicsContext graphicsContext, Stage stage) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphicsContext.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 32));
        graphicsContext.setStroke(Color.RED);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.strokeText("!!!GAME OVER!!!", SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

    }

    public static void main(String[] args) {
        launch();
    }
}