package com.example.pacman;

import com.example.pacman.components.Booster;
import com.example.pacman.components.Wall;
import com.example.pacman.gameUtilities.GameBoard;
import com.example.pacman.gameUtilities.GameBoardCell;
import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import com.example.pacman.ghosts.Ghost;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class PacMan extends GameComponent {
    private Position initialPosition;
    private int posX, posY;
    private int hp;
    private int score;
    private int direction;
    private GameBoard gameBoard;
    private Image imgUp, imgDown, imgLeft, imgRight;


    public PacMan(GameBoard gameBoard) {
        hp = 3;
        direction = 1;
        this.gameBoard = gameBoard;

        try {
            imgUp = new Image(Files.newInputStream(Path.of("src/main/resources/images/up.gif")), SIZE, SIZE, true, false);
            imgDown = new Image(Files.newInputStream(Path.of("src/main/resources/images/down.gif")), SIZE, SIZE, true, false);
            imgLeft = new Image(Files.newInputStream(Path.of("src/main/resources/images/left.gif")), SIZE, SIZE, true, false);
            imgRight = new Image(Files.newInputStream(Path.of("src/main/resources/images/right.gif")), SIZE, SIZE, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load pac-man image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        if (direction == 0) {
            graphicsContext.drawImage(imgUp, posX*SIZE, posY*SIZE);
        }
        else if (direction == 1) {
            graphicsContext.drawImage(imgRight, posX*SIZE, posY*SIZE);
        }
        else if (direction == 2) {
            graphicsContext.drawImage(imgDown, posX*SIZE, posY*SIZE );
        }
        else if (direction == 3) {
            graphicsContext.drawImage(imgLeft, posX*SIZE, posY*SIZE);
        }
    }

    @Override
    public void pick() {
        Position newPosition = chooseNewPosition();
        posX = newPosition.getX();
        posY = newPosition.getY();
        eatBoosters(newPosition);
    }

    public void eatBoosters(Position position) {
        GameBoardCell cell = gameBoard.getCell(position);
        for (GameComponent gameComponent : cell.getGameComponents()) {
            if (gameComponent instanceof Booster) {
                ((Booster) gameComponent).applyBoost(this);
                cell.remove(gameComponent);
            }
        }
    }

    @Override
    public void check() {
        super.check();
        for (Ghost ghost : gameBoard.ghosts) {
            if (posX == ghost.getPosX() && posY == ghost.getPosY()) {
                ghost.attack(this);
            }
        }
    }

    private Position chooseNewPosition() {
        Position goUp = new Position(posX, posY - 1);
        Position goDown = new Position(posX, posY + 1);
        Position goRight = new Position(posX + 1, posY);
        Position goLeft = new Position(posX - 1, posY);
        ArrayList<Integer> possibleDirections = new ArrayList<>();
        if (canMove(gameBoard.getCell(goUp))) {
            possibleDirections.add(0);
        }
        if (canMove(gameBoard.getCell(goRight))) {
            possibleDirections.add(1);
        }
        if (canMove(gameBoard.getCell(goDown))) {
            possibleDirections.add(2);
        }
        if (canMove(gameBoard.getCell(goLeft))) {
            possibleDirections.add(3);
        }

        Random random = new Random();
        switch (possibleDirections.get(random.nextInt(possibleDirections.size()))) {
            case 0 -> {
                direction = 0;
                return goUp;
            }
            case 1 -> {
                direction = 1;
                return goRight;
            }
            case 2 -> {
                direction = 2;
                return goDown;
            }
            case 3 -> {
                direction = 3;
                return goLeft;
            }
        }

        return new Position(posX, posY);
    }

    public boolean canMove(GameBoardCell cell) {
        if (cell != null) {
            return !cell.isWall;
        } else {
            return false;
        }
    }

    @Override
    public int drawOrder() {
        return 998;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
        posX = initialPosition.getX();
        posY = initialPosition.getY();
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
