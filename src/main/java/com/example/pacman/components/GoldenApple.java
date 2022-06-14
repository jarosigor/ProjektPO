package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GoldenApple extends GameComponent {
    private Position position;
    private int hpBoost = 2;
    private Image imgGoldenApple;
    public GoldenApple(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgGoldenApple = new Image(Files.newInputStream(Path.of("src/main/resources/images/goldenApple.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load golden apple image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgGoldenApple, position.getX()*SIZE, position.getY()*SIZE);
    }
}
