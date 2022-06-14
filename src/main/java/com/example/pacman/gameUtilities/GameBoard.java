//Plansza symulacji

package com.example.pacman.gameUtilities;

import com.example.pacman.*;
import com.example.pacman.components.Heart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameBoard {
    private Heart heart;
    private PacMan pacMan;
    private MapBuilder mapBuilder;
    private GraphicsContext gc;
    private Map<Position, GameBoardCell> board;

    public GameBoard(GraphicsContext gc) {
        this.gc = gc;
        heart = new Heart();
    }

    public GameBoardCell set(Position position, GameComponent gameComponent) {
        board.entrySet().forEach(entry -> {
            if (entry != null) {
                entry.getValue().getGameComponents().remove(gameComponent);
            }
        });

        GameBoardCell cell = board.getOrDefault(position, new GameBoardCell());
        cell.insert(gameComponent);
        board.put(position, cell);

        return cell;
    }

    public void printMapToConsole() {
        board.entrySet().forEach(entry -> {
            if (entry != null) {
                entry.getValue().getGameComponents().stream().forEach(gameComponent -> {
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
                        .forEach(gameComponent -> gameComponent.pick(entry.getKey())));
    }

    public GameBoardCell move(Position position, GameComponent gameComponent) {
        GameBoardCell cell = set(position, gameComponent);

        cell.check();
        return cell;
    }

    public void draw(GraphicsContext gc) {
        board.entrySet().forEach(entry -> entry.getValue().getGameComponents().stream()
                .forEach(gameComponent -> gameComponent.draw(gc)));

        gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 32));
        gc.setStroke(Color.RED);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.strokeText("SCORE: " + pacMan.getScore(), 40, 850);

        heart.draw(gc, pacMan.getHp());

    }

    public MapBuilder getMapBuilder() {
        return mapBuilder;
    }

    public void newGame() {
        board = new ConcurrentHashMap<>();
        pacMan = new PacMan();
        mapBuilder = new MapBuilder(this);
        mapBuilder.loadMap("map2.csv");
        printMapToConsole();
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public String printComponent(Position position) {
        return board.get(position).getClass().toString();
    }
}
