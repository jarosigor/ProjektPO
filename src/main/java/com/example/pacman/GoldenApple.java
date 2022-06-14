package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GoldenApple extends GameComponent{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    private int hpBoost = 2;
    private Image imgGoldenApple;
    public GoldenApple(int posX, int posY) {
        try {
            imgGoldenApple = new Image(Files.newInputStream(Path.of("src/main/resources/images/goldenApple.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load golden apple image!");
        }
        this.posX=posX;
        this.posY=posY;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgGoldenApple, posX, posY);

    }
    public int getHpBoost(){return hpBoost;}

}
