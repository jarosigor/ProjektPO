package com.example.pacman.ghosts;

import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GreenGhost extends Ghost {
    private Image imgGreenGhost;
    public GreenGhost(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgGreenGhost = new Image(Files.newInputStream(Path.of("src/main/resources/images/ghost1.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load Green Ghost image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgGreenGhost, position.getX()*SIZE, position.getY()*SIZE);
    }
}
