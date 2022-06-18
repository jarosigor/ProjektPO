package com.example.pacman.gameUtilities;

import javafx.geometry.Pos;

public class Position {
    private int X = 0;
    private int Y = 0;

    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "X: " + X + " Y: " + Y;
    }

    @Override
    public boolean equals(Object obj) {
        return  (((Position) obj).getX() == this.getX() && ((Position)obj).getY() == this.getY());
    }
}
