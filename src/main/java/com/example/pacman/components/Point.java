package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Point extends GameComponent {
    private Position position;
    private int scoreBoost = 1;
    private Image imgPoint;
    public Point(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgPoint = new Image(Files.newInputStream(Path.of("src/main/resources/images/point.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load point image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgPoint, position.getX()*SIZE, position.getY()*SIZE);
    }

    @Override
    public int drawOrder() {
        return 998;
    }
}
