package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Point extends GameComponent{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    private Image imgPoint;
    private int scoreBoost=1;

    public Point(int posX, int posY) {
        try {
            imgPoint = new Image(Files.newInputStream(Path.of("src/main/resources/images/point.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load point image!");
        }

        this.posX = posX;
        this.posY = posY;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgPoint, posX, posY);

    }
    public int getScoreBoost(){return scoreBoost;}
}
