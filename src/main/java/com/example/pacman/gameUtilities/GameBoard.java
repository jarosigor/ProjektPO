//Plansza symulacji

package com.example.pacman.gameUtilities;

import com.example.pacman.*;
import com.example.pacman.components.Heart;
import com.example.pacman.components.Wall;
import com.example.pacman.ghosts.Ghost;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameBoard {
    public boolean moveDone = true;
    private Heart heart;
    private PacMan pacMan;
    private MapBuilder mapBuilder;
    private Map<Position, GameBoardCell> board;
    public List<Ghost> ghosts;
    public boolean nextRound = false;

    public GameBoard() {
        board = new ConcurrentHashMap<>();
        mapBuilder = new MapBuilder(this);
        pacMan = new PacMan(this);
        heart = new Heart();
        ghosts = new ArrayList<>();
    }

    public void set(Position position, GameComponent gameComponent) {
        board.entrySet().forEach(entry -> {
                entry.getValue().getGameComponents().remove(gameComponent);
        });

        if (gameComponent instanceof Ghost) {
            ghosts.add((Ghost) gameComponent);
        }

        GameBoardCell cell = board.getOrDefault(position, new GameBoardCell(this));
        cell.insert(gameComponent);
        board.put(position, cell);
        if (gameComponent.getClass().equals(Wall.class)) {
            cell.isWall = true;
        }
    }

    public void printMapToConsole() {
        board.entrySet().forEach(entry -> {
            if (entry != null) {
                entry.getValue().getGameComponents().forEach(gameComponent -> {
                    System.out.println(gameComponent);
                    System.out.println(entry.getKey().getX() + " " + entry.getKey().getY());
                });
            }
        });
    }

    public void pick() {
        board.entrySet().forEach(entry ->
                entry.getValue()
                        .getGameComponents().stream().sorted()
                        .forEach(gameComponent -> gameComponent.pick()));
    }

    public void check() {
        board.entrySet().forEach(entry -> entry.getValue().getGameComponents().stream().sorted()
                .forEach(gameComponent -> gameComponent.check()));
    }

    public void draw(GraphicsContext gc) {
        board.entrySet().forEach(entry -> entry.getValue().getGameComponents().stream().sorted()
                .forEach(gameComponent -> gameComponent.draw(gc)));

        gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 32));
        gc.setStroke(Color.RED);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.strokeText("SCORE: " + pacMan.getScore(), 40, 850);

        heart.draw(gc, pacMan.getHp());

    }

    public void nextRound() {
        board.entrySet().forEach(entry -> entry.getValue().getGameComponents()
                .forEach(gameComponent -> gameComponent.nextRound()));
        nextRound = true;
    }

    public void newGame(String mapName) {
        mapBuilder.loadMap(mapName);
        pacMan.setHp(3);
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public GameBoardCell getCell(Position position) {
        for (Map.Entry<Position, GameBoardCell> entry : board.entrySet()) {
            if (entry.getKey().equals(position)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public Map<Position, GameBoardCell> getBoard() {
        return board;
    }
}
