package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Raspberry extends GameComponent {
    protected Position position;
    private int scoreBoost = 10;
    private Image imgRaspberry;

    public Raspberry(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgRaspberry = new Image(Files.newInputStream(Path.of("src/main/resources/images/raspberry.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load raspberry image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgRaspberry, position.getX()*SIZE, position.getY()*SIZE);
    }
}
