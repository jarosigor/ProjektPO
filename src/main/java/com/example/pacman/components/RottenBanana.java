package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RottenBanana extends GameComponent {
    private int speedBoost = -1;
    private int speedDuration = 5;
    private Position position;
    private Image imgRottenBanana;
    public RottenBanana(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgRottenBanana = new Image(Files.newInputStream(Path.of("src/main/resources/images/rottenBanana.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load raspberry image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgRottenBanana, position.getX()*SIZE, position.getY()*SIZE);
    }
}
