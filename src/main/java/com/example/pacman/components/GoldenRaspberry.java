package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GoldenRaspberry extends GameComponent {
    private int scoreBoost = 20;
    private Position position;
    private Image imgGoldenRaspberry;
    public GoldenRaspberry(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgGoldenRaspberry = new Image(Files.newInputStream(Path.of("src/main/resources/images/goldenRaspberry.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load golden raspberry image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgGoldenRaspberry, position.getX()*SIZE, position.getY()*SIZE);
    }
}
