package com.example.pacman.Boosters;

import com.example.pacman.GameComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class GoldenRaspberry extends GameComponent {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    private int scoreBoost = 20;
    private Image imgGoldenRaspberry;

    public GoldenRaspberry(int posX, int posY) {
        try {
            imgGoldenRaspberry = new Image(Files.newInputStream(Path.of("src/main/resources/images/goldenRaspberry.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load golden raspberry image!");
        }

        this.posX = posX;
        this.posY = posY;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgGoldenRaspberry, posX, posY);}
    public int getScoreBoost(){return scoreBoost;}
}

