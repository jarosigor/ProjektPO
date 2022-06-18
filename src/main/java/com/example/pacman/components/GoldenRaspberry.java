package com.example.pacman.components;

import com.example.pacman.PacMan;
import com.example.pacman.gameUtilities.Position;
import javafx.css.Size;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GoldenRaspberry extends Booster {
    private int scoreBoost = 20;
    public GoldenRaspberry(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgBooster = new Image(Files.newInputStream(Path.of("src/main/resources/images/goldenRaspberry.gif"))
                    , SIZE, SIZE, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load golden raspberry image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgBooster, position.getX()*SIZE, position.getY()*SIZE);
    }

    @Override
    public void applyBoost(PacMan pacMan) {
        pacMan.setScore(pacMan.getScore() + scoreBoost);
    }
}
