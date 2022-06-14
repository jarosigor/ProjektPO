package com.example.pacman.components;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Apple extends GameComponent  {
    private int hpBoost = 1;
    private Image imgApple;
    protected Position position;

    public Apple(Position position) {
        this.position = new Position(position.getX(), position.getY());
        try {
            imgApple = new Image(Files.newInputStream(Path.of("src/main/resources/images/apple.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load apple image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgApple, position.getX()*SIZE, position.getY()*SIZE);

    }
    public int getHpBoost(){return hpBoost;}

}
