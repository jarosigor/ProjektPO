package com.example.pacman.Boosters;

import com.example.pacman.GameComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Banana extends GameComponent {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    private int speedBoost = 1;
    private int speedDuration=5;
    private Image imgBanana;

    public Banana(int posX, int posY) {
        try {
            imgBanana = new Image(Files.newInputStream(Path.of("src/main/resources/images/banana.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load raspberry image!");
        }

        this.posX = posX;
        this.posY = posY;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgBanana, posX, posY);}
    public int getSpeedBoost(){return speedBoost;}
}
