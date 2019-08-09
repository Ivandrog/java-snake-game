package com.codegym.games.snake;

import com.codegym.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    @Override
    public void initialize() {
        // Set the field size to 15 cells x 15 cells
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }


    private void createGame() {
        Snake snake = new Snake(WIDTH/2, HEIGHT/2);
        this.snake = snake;
        drawScene();

       // Apple apple = new Apple(7,7);
       // apple.draw(this);

    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.DARKORCHID );
            }
        }
        //apple.draw(this);
        snake.draw(this);
    }


}
