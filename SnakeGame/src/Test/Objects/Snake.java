package Test.Objects;

import Test.SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Snake {

    public int length = 3;
    public int direction = 2;
    public int[] snakeX = new int[SnakeGame.WIDTH * SnakeGame.SCALE];
    public int[] snakeY = new int[SnakeGame.HEIGHT * SnakeGame.SCALE];

    public Snake(int x1, int y1, int x2, int y2) {
        snakeX[0] = x1;
        snakeX[1] = x2;
        snakeY[0] = y1;
        snakeY[1] = y2;

        snakeX[2] = x2 - (x1 - x2);
        snakeY[2] = y2 - (y1 - y2);
    }

    public void move() {

        for(int i = length; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        if(direction == 0) {
            snakeY[0]--;
        } else if(direction == 1) {
            snakeY[0]++;
        } else if(direction == 2) {
            snakeX[0]++;
        } else if(direction == 3) {
            snakeX[0]--;
        }
    }
}
