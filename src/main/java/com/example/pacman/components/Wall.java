package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Wall extends GameComponent {
    private Position position;
    private Image imgWall;
    public Wall(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgWall = new Image(Files.newInputStream(Path.of("src/main/resources/images/wall.gif"))
                    , SIZE, SIZE, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load wall image!");
        }
    }

    @Override
    public int drawOrder() {
        return 9999;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgWall, position.getX()*SIZE, position.getY()*SIZE);
    }
}
