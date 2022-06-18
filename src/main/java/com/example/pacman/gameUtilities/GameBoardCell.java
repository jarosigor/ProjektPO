package com.example.pacman.gameUtilities;

import com.example.pacman.PacMan;
import com.example.pacman.components.Booster;
import com.example.pacman.components.Wall;
import com.example.pacman.ghosts.Ghost;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public  class GameBoardCell {
    private GameBoard gameBoard;
    private List<GameComponent> gameComponents;
    public boolean isWall = false;

    public GameBoardCell(GameBoard gameBoard) {
        gameComponents = new LinkedList();
        this.gameBoard = gameBoard;
    }

    public void insert(GameComponent gc) {
        gameComponents.add(gc);
    }

    public void remove(GameComponent gc) {
        gameComponents.remove(gc);
    }

    public List<GameComponent> getGameComponents() {
        return gameComponents;
    }

}
