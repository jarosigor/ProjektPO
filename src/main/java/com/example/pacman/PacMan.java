package com.example.pacman;

import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PacMan extends GameComponent {
    private Position position;
    private Position initialPosition;
    private int posX, posY;
    private int hp;
    private int score;
    private int dirrection;
    private Image imgUp, imgDown, imgLeft, imgRight;


    public PacMan() {
        hp = 3;
        dirrection = 1;

        try {
            imgUp = new Image(Files.newInputStream(Path.of("src/main/resources/images/up.gif")), 40, 40, true, false);
            imgDown = new Image(Files.newInputStream(Path.of("src/main/resources/images/down.gif")), 40, 40, true, false);
            imgLeft = new Image(Files.newInputStream(Path.of("src/main/resources/images/left.gif")), 40, 40, true, false);
            imgRight = new Image(Files.newInputStream(Path.of("src/main/resources/images/right.gif")), 40, 40, true, false);
        } catch (IOException e) {
            System.out.println("!Failed to load pac-man image!");
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        if (dirrection == 0) {
            graphicsContext.drawImage(imgUp, position.getX()*SIZE, position.getY()*SIZE);
        } else if (dirrection == 1) {
            graphicsContext.drawImage(imgRight, position.getX()*SIZE, position.getY()*SIZE);
        } else if (dirrection == 2) {
            graphicsContext.drawImage(imgDown, position.getX()*SIZE, position.getY()*SIZE);
        } else if (dirrection == 3) {
            graphicsContext.drawImage(imgLeft, position.getX()*SIZE, position.getY()*SIZE);
        }
    }

    public void chooseDirrection() {

    }

    @Override
    public void pick(Position p) {

    }

    public void canMove() {

    }

    public void reset(int posX, int posY) {
        this.hp = 3;
        this.dirrection = 1;
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

    public int getDirrection() {
        return dirrection;
    }

    public void setDirrection(int dirrection) {
        this.dirrection = dirrection;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
        position = new Position(initialPosition.getX(), initialPosition.getY());
    }

    public void setPosition(Position pos) {
        position = pos;
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
