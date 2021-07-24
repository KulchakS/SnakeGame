package Test.Objects;

import Test.SnakeGame;

public class Apple {

    public int positionY;
    public int positionX;

    public Apple(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public void setRandomPosition() {
        positionX = (int) (Math.random() * (SnakeGame.WIDTH - 1));
        positionY = (int) (Math.random() * (SnakeGame.HEIGHT - 1));
    }
}
