package com.example.pacman.ghosts;

import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RedGhost extends Ghost {
    private Image imgRedGhost;
    public RedGhost(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgRedGhost = new Image(Files.newInputStream(Path.of("src/main/resources/images/ghost2.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load Red Ghost image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgRedGhost, position.getX()*SIZE, position.getY()*SIZE);
    }
}
