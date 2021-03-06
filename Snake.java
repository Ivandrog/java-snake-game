package com.codegym.games.snake;
import com.codegym.engine.cell.*;
//import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;
//this is from bendingFix branch
public class Snake extends GameObject
{
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private  List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x,y);
        GameObject first = new GameObject(x,y);
        GameObject second = new GameObject(x + 1, y);
        GameObject third = new GameObject(x + 2,y);
        snakeParts.add(first);
        snakeParts.add(second);
        snakeParts.add(third);
    }

    public void draw(Game game) {



            if (!isAlive) {
                for (GameObject snakePart : snakeParts) {
                    game.setCellValueEx(snakePart.x, snakePart.y, Color.NONE, BODY_SIGN, Color.RED, 75);

                }
                game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
            }
            else {
                for (GameObject snakePart : snakeParts) {
                    game.setCellValueEx(snakePart.x, snakePart.y, Color.NONE, BODY_SIGN, Color.ALICEBLUE, 75);
                }
                game.setCellValueEx(x, y, Color.NONE, HEAD_SIGN, Color.BLUE, 75);
            }

        }



        //game.setCellValueEx(x + 2, y, Color.NONE, BODY_SIGN, Color.ALICEBLUE, 75);

        /*
        else {
            game.setCellValueEx(x, y, Color.NONE, HEAD_SIGN, Color.BLUE, 75);

            game.setCellValueEx(x + 1, y, Color.NONE, BODY_SIGN, Color.ALICEBLUE, 75);
            game.setCellValueEx(x + 2, y, Color.NONE, BODY_SIGN, Color.ALICEBLUE, 75);
        }*/

 public void setDirection(Direction direction) {
        GameObject snakeHead = createNewHead();

        if((this.direction==Direction.LEFT && direction != Direction.RIGHT && snakeParts.get(0).x != snakeParts.get(1).x) || (this.direction==Direction.RIGHT && direction != Direction.LEFT && snakeParts.get(0).x != snakeParts.get(1).x) || (this.direction==Direction.UP && direction != Direction.DOWN && snakeParts.get(0).y != snakeParts.get(1).y) || (this.direction==Direction.DOWN && direction != Direction.UP && snakeParts.get(0).y != snakeParts.get(1).y))
        this.direction = direction;
    }


    public void move(Apple apple) {
        GameObject snakeHead = createNewHead();
        if (snakeHead.x<0 || snakeHead.x>=SnakeGame.WIDTH || snakeHead.y<0 || snakeHead.y>=SnakeGame.HEIGHT) 
            isAlive = false;
        else if ((apple.x == snakeHead.x) && (apple.y == snakeHead.y)) {
            apple.isAlive = false;
            snakeParts.add(0, snakeHead);
        }
        else if (checkCollision(createNewHead())) {
                isAlive = false;
        }
        else {
            snakeParts.add(0, snakeHead);
            removeTail();
        }
    }

    public void removeTail(){
        
        snakeParts.remove(snakeParts.size()-1);
    }

    public GameObject createNewHead(){
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        switch (direction) {
            case LEFT:
                x = headX - 1;
                break;
            case UP:
                y = headY-1;
                break;
            case RIGHT:
                x = headX+1;
                break;
            case DOWN:
                y = headY+1;
                break;
        }
        return new GameObject(x,y);
    }

   public boolean checkCollision(GameObject bodySegment) {
        boolean bodyKnot = false;
        for (int i = 0; i < snakeParts.size(); i++) {
                if ((bodySegment.x == snakeParts.get(i).x) && (bodySegment.y == snakeParts.get(i).y)) {
                    isAlive = false;
                    bodyKnot = true;
                }


        }
        return bodyKnot;
    }

    public int getLength() {
        return snakeParts.size();
    }


}