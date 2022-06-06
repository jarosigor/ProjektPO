package com.example.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class Simulation extends Application {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;

    private int map[][];

    private static final int BACKGROUND_CODE = 0;
    private static final int WALL_CODE = 1;
    private static final int AISLE_CODE = 2;
    private static final int POINT_CODE = 3;
    private static final int APPLE_CODE = 4;
    private static final int GOLDEN_APPLE_CODE = 5;
    private static final int RASPBERRY_CODE = 6;
    private static final int GOLDEN_RASPBERRY_CODE = 7;
    private static final int BANANA_CODE = 8;
    private static final int ROTTEN_BANANA_CODE = 9;

    private static int startingPointX = SCREEN_WIDTH/2;
    private static int startingPointY = SCREEN_HEIGHT/2;

    private boolean gameStarted;
    private PacMan pacMan = new PacMan(startingPointX, startingPointY);
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("PAC-MAN");
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
                e -> {
                    try{
                        run(gc);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}
        ));
        tl.setCycleCount(Timeline.INDEFINITE);

        // game controls to present pacman s movement
        canvas.setOnMouseClicked(e -> gameStarted = true);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP) {
                pacMan.setPosY(pacMan.getPosY() - 5);
                pacMan.setDirrection(0);
            } else if (key.getCode() == KeyCode.DOWN) {
                pacMan.setPosY(pacMan.getPosY() + 5);
                pacMan.setDirrection(2);
            } else if (key.getCode() == KeyCode.RIGHT) {
                pacMan.setPosX(pacMan.getPosX() + 5);
                pacMan.setDirrection(1);
            } else if (key.getCode() == KeyCode.LEFT) {
                pacMan.setPosX(pacMan.getPosX() - 5);
                pacMan.setDirrection(3);
            } else if (key.getCode() == KeyCode.ESCAPE) {
                gameStarted = false;
            }
        });

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    public void run(GraphicsContext gc) throws IOException {

        if (gameStarted) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            pacMan.draw(gc);

        } else {
            drawStartScreen(gc);
            pacMan.reset(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        }

    }

    public void drawStartScreen(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphicsContext.setFont(Font.font(30));
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.strokeText("Start on click",SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
    }



    public static void main(String[] args) {
        launch();
    }
}