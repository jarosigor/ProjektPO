package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PacMan {
    private int hp;
    private int posX, posY;
    private int dirrection;
    private Image imgUp, imgDown, imgLeft, imgRight;

    public PacMan(int posX, int posY) {
        try {
            imgUp = new Image(Files.newInputStream(Path.of("src/main/resources/images/up.gif")));
            imgDown = new Image(Files.newInputStream(Path.of("src/main/resources/images/down.gif")));
            imgLeft = new Image(Files.newInputStream(Path.of("src/main/resources/images/left.gif")));
            imgRight = new Image(Files.newInputStream(Path.of("src/main/resources/images/right.gif")));
        } catch (IOException e) {
            System.out.println("!Failed to load pac-man image!");
        }

        this.posX = posX;
        this.posY = posY;
        this.hp = 3;
        this.dirrection = 1;
    }

    public void draw(GraphicsContext graphicsContext) {
        if (getDirrection() == 0) {
            graphicsContext.drawImage(getImgUp(), getPosX(), getPosY());
        } else if (getDirrection() == 1) {
            graphicsContext.drawImage(getImgRight(), getPosX(), getPosY());
        } else if (getDirrection() == 2) {
            graphicsContext.drawImage(getImgDown(), getPosX(), getPosY());
        } else if (getDirrection() == 3) {
            graphicsContext.drawImage(getImgLeft(), getPosX(), getPosY());
        }
    }

    public void reset(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.hp = 3;
        this.dirrection = 1;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirrection() {
        return dirrection;
    }

    public void setDirrection(int dirrection) {
        this.dirrection = dirrection;
    }

    public Image getImgUp() {
        return imgUp;
    }

    public Image getImgDown() {
        return imgDown;
    }

    public Image getImgLeft() {
        return imgLeft;
    }

    public Image getImgRight() {
        return imgRight;
    }
}
