package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Wall extends GameComponent{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    private Image imgWall;

    public Wall(int posX, int posY) {
        try {
            imgWall = new Image(Files.newInputStream(Path.of("src/main/resources/images/wall.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load wall image!");
        }
        this.posX=posX;
        this.posY=posY;
    }
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgWall, posX, posY);

    }
}
