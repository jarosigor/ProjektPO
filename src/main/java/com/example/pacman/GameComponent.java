package com.example.pacman;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameComponent implements Comparable {
    public int posX, posY;


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

}
