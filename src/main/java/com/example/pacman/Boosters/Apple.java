package com.example.pacman.Boosters;

import com.example.pacman.GameComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


    public class Apple extends GameComponent  {
        @Override
        public int compareTo(Object o) {
            return 0;
        }
        private int hpBoost = 1;
        private Image imgApple;

        public Apple(int posX, int posY) {
            try {
                imgApple = new Image(Files.newInputStream(Path.of("src/main/resources/images/apple.gif")));
            } catch (IOException e) {
                System.out.println("!Failed to load apple image!");
            }
        this.posX=posX;
        this.posY=posY;
        }

        public void draw(GraphicsContext graphicsContext) {
            graphicsContext.drawImage(imgApple, posX, posY);

        }
        public int getHpBoost(){return hpBoost;}

    }

