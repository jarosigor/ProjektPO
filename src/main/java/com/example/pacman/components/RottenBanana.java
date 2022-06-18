package com.example.pacman.components;

import com.example.pacman.PacMan;
import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RottenBanana extends Booster {
    public RottenBanana(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgBooster = new Image(Files.newInputStream(Path.of("src/main/resources/images/rottenBanana.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load raspberry image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgBooster, position.getX()*SIZE, position.getY()*SIZE);
    }

    @Override
    public void applyBoost(PacMan pacMan) {
        pacMan.setScore(pacMan.getScore()/2);
    }
}
