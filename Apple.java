package com.codegym.games.snake;
import com.codegym.engine.cell.*;

public class Apple extends GameObject

{
    public boolean isAlive = true;

    private static final String APPLE_SIGN = "\uD83C\uDF4E";

    Apple(int x, int y) {
        super(x, y);
    }

    public void draw(Game game){
        game.setCellValueEx(x, y, Color.NONE, APPLE_SIGN, Color.DARKRED,75);
    }

}
