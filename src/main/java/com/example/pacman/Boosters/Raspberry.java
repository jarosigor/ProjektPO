package com.example.pacman.Boosters;

import com.example.pacman.GameComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Raspberry extends GameComponent {
        @Override
        public int compareTo(Object o) {
            return 0;
        }
        private int scoreBoost = 10;
        private Image imgRaspberry;

        public Raspberry(int posX, int posY) {
            try {
                imgRaspberry = new Image(Files.newInputStream(Path.of("src/main/resources/images/raspberry.gif")));
            } catch (IOException e) {
                System.out.println("!Failed to load raspberry image!");
            }

            this.posX = posX;
            this.posY = posY;
        }

        public void draw(GraphicsContext graphicsContext) {
            graphicsContext.drawImage(imgRaspberry, posX, posY);}
        public int getScoreBoost(){return scoreBoost;}
    }

