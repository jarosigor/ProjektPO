package com.example.pacman.gameUtilities;

import com.example.pacman.components.*;
import com.example.pacman.ghosts.BlueGhost;
import com.example.pacman.ghosts.GreenGhost;
import com.example.pacman.ghosts.RedGhost;
import com.example.pacman.ghosts.WhiteGhost;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapBuilder {
    private GameBoard gameBoard;
    private static final String BACKGROUND_CODE = "0";
    private static final String WALL_CODE = "1";
    private static final String POINT_CODE = "3";
    private static final String APPLE_CODE = "4";
    private static final String GOLDEN_APPLE_CODE = "5";
    private static final String RASPBERRY_CODE = "6";
    private static final String GOLDEN_RASPBERRY_CODE = "7";
    private static final String BANANA_CODE = "8";
    private static final String ROTTEN_BANANA_CODE = "9";
    private static final String GREEN_GHOST_CODE = "GG";
    private static final String RED_GHOST_CODE = "RG";
    private static final String BLUE_GHOST_CODE = "BG";
    private static final String WHITE_GHOST_CODE = "WG";
    private static final String PAC_MAN_CODE = "P";


    public static final int SIZE = 20;

    public MapBuilder(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void loadMap(String fileName) {
        List<String[]> list = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/maps/" + fileName));
            list = bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Position position = new Position(0, 0);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                position.setX(i);
                position.setY(j);
                if (!list.get(i)[j].isBlank()) {
                    System.out.println(Integer.getInteger(list.get(i)[j]));
                    switch (list.get(i)[j]) {
                        case PAC_MAN_CODE:
                            gameBoard.getPacMan().setInitialPosition(position);
                            gameBoard.set(position, gameBoard.getPacMan());
                            break;
                        case WALL_CODE:
                            gameBoard.set(position, new Wall(position));
                            break;
                        case POINT_CODE:
                            gameBoard.set(position, new Point(position));
                            break;
                        case APPLE_CODE:
                            gameBoard.set(position, new Apple(position));
                            break;
                        case GOLDEN_APPLE_CODE:
                            gameBoard.set(position, new GoldenApple(position));
                            break;
                        case RASPBERRY_CODE:
                            gameBoard.set(position, new Raspberry(position));
                            break;
                        case GOLDEN_RASPBERRY_CODE:
                            gameBoard.set(position, new GoldenRaspberry(position));
                            break;
                        case BANANA_CODE:
                            gameBoard.set(position, new Banana(position));
                            break;
                        case ROTTEN_BANANA_CODE:
                            gameBoard.set(position, new RottenBanana(position));
                            break;
                        case GREEN_GHOST_CODE:
                            gameBoard.set(position, new GreenGhost(position));
                            break;
                        case BLUE_GHOST_CODE:
                            gameBoard.set(position, new BlueGhost(position));
                            break;
                        case RED_GHOST_CODE:
                            gameBoard.set(position, new RedGhost(position));
                            break;
                        case WHITE_GHOST_CODE:
                            gameBoard.set(position, new WhiteGhost(position));
                    }
                }

            }
        }

    }

    public static void main(String[] args) {

    }


}
