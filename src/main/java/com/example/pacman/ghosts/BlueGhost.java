package com.example.pacman.ghosts;

import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BlueGhost extends Ghost {
    private Image imgBlueGhost;
    public BlueGhost(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgBlueGhost = new Image(Files.newInputStream(Path.of("src/main/resources/images/ghost4.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load Blue Ghost image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgBlueGhost, position.getX()*SIZE, position.getY()*SIZE);
    }
}
