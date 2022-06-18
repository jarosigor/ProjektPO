package com.example.pacman.components;

import com.example.pacman.PacMan;
import com.example.pacman.gameUtilities.GameComponent;
import com.example.pacman.gameUtilities.Position;
import javafx.scene.image.Image;

public abstract class Booster extends GameComponent {
    protected Position position;
    protected Image imgBooster;

    public void applyBoost(PacMan pacMan) {}

    @Override
    public int drawOrder() {
        return 1;
    }
}
