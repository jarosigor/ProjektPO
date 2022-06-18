package com.example.pacman.components;

import com.example.pacman.PacMan;
import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.css.Size;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Heart extends GameComponent {
    private Image imgHeart;

    public Heart() {
        try {
            imgHeart = new Image(Files.newInputStream(Path.of("src/main/resources/images/heart.png"))
                    , SIZE, SIZE, true, false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void draw(GraphicsContext gc, int hp) {
        switch (hp) {
            case 1:
                gc.drawImage(imgHeart, 700, 820);
                break;
            case 2:
                gc.drawImage(imgHeart, 700, 820);
                gc.drawImage(imgHeart, 650, 820);
                break;
            case 3:
                gc.drawImage(imgHeart, 600, 820);
                gc.drawImage(imgHeart, 650, 820);
                gc.drawImage(imgHeart, 700, 820);
                break;
        }
    }
}
