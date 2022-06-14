package com.example.pacman.gameUtilities;

import java.util.ArrayList;
import java.util.List;

public  class GameBoardCell {
    private List<GameComponent> gameComponents;
    public GameBoardCell() {
        gameComponents = new ArrayList<>();
    }
    public GameBoardCell(GameComponent gameComponent) {
        gameComponents = new ArrayList<>();
        insert(gameComponent);
    }
    public void insert(GameComponent gc) {
        gameComponents.add(gc);
    }
    public void remove(GameComponent gc) {
        gameComponents.remove(gc);
    }

    public void check() {

    }

    public List<GameComponent> getGameComponents() {
        return gameComponents;
    }
}
