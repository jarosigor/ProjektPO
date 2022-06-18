package com.example.pacman.gameUtilities;

import javafx.scene.canvas.GraphicsContext;

public class GameComponent implements Comparable {
    protected final int SIZE = 40;
    public GameComponent() {}

    public void pick() {

    }

    public void draw(GraphicsContext gc) {

    }

    public void check(){

    }

    public int drawOrder() {
        return 0;
    }

    public void nextRound() {}
    @Override
    public int compareTo(Object o) {
        return Integer.compare(drawOrder(), ((GameComponent) o).drawOrder());
    }

}
