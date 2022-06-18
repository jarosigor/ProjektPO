package com.example.pacman.ghosts;

import com.example.pacman.PacMan;
import com.example.pacman.gameUtilities.GameBoard;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class WhiteGhost extends Ghost {
    private Image imgWhiteGhost;

    public WhiteGhost(Position position, GameBoard gameBoard) {
        initialPosition = position;
        this.gameBoard = gameBoard;
        posX = position.getX();
        posY = position.getY();
        this.damage = 2;
        try {
            imgWhiteGhost = new Image(Files.newInputStream(Path.of("src/main/resources/images/ghost3.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load raspberry image!");
        }
    }

    @Override
    public void pick() {
        Position newPosition = chooseNewPosition();
        posX = newPosition.getX();
        posY = newPosition.getY();
        // ewentualny atak na pac-mana
    }

    public Position chooseNewPosition() {
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

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(imgWhiteGhost, posX*SIZE, posY*SIZE);
    }

}
