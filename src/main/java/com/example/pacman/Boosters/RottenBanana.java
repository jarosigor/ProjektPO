package com.example.pacman.Boosters;

import com.example.pacman.GameComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RottenBanana extends GameComponent {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    private int speedBoost = -1;
    private int speedDuration=5;
    private Image imgrottenBanana;

    public RottenBanana(int posX, int posY) {
        try {
            imgrottenBanana = new Image(Files.newInputStream(Path.of("src/main/resources/images/rottenBanana.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load raspberry image!");
        }

        this.posX = posX;
        this.posY = posY;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgrottenBanana, posX, posY);}
    public int getSpeedBoost(){return speedBoost;}
}

