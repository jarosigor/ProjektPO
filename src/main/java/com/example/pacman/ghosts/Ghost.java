package com.example.pacman.ghosts;

import com.example.pacman.PacMan;
import com.example.pacman.gameUtilities.GameBoard;
import com.example.pacman.gameUtilities.GameBoardCell;
import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Ghost extends GameComponent {
    protected Position initialPosition;
    protected int posX, posY;
    protected int damage;
    protected GameBoard gameBoard;
    protected int direction = 0;

    public void attack(PacMan pacMan) {
        pacMan.setHp(pacMan.getHp() - damage);
        if (pacMan.getHp() > 0) {
            gameBoard.nextRound();
        }

    }

    @Override
    public int drawOrder() {
        return 999;
    }


    protected boolean canMove(GameBoardCell cell) {
        if (cell != null) {
            return !cell.isWall;
        } else {
            return false;
        }
    }

    @Override
    public void check() {
        super.check();
        PacMan pacMan = gameBoard.getPacMan();
        if (posX == pacMan.getPosX() && posY == pacMan.getPosY()) {
            attack(pacMan);
        }
    }

    @Override
    public void nextRound() {
        super.nextRound();

        posX = initialPosition.getX();
        posY = initialPosition.getY();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
