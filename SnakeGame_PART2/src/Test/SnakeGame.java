package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Test.Objects.*;


public class SnakeGame extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public int score = 3;
    public int maxScore;
    public boolean check = true;

    Snake snake = new Snake(8,8,7,8);
    Apple apple = new Apple((int) (Math.random() * (WIDTH - 1)), (int) (Math.random() * (HEIGHT - 1)));
    Timer timer = new Timer(250, this);

    public SnakeGame() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH * SCALE, HEIGHT * SCALE);

        g.setColor(Color.RED);
        g.fillOval(apple.positionX * SCALE + 4, apple.positionY * SCALE + 4, SCALE - 8, SCALE - 8);

        for(int i = 1; i < snake.length; i++) {
            g.setColor(Color.green);
            g.fillOval(snake.snakeX[i] * SCALE + 3, snake.snakeY[i] * SCALE + 3, SCALE - 6, SCALE - 6);
            g.setColor(Color.orange);
            g.fillOval(snake.snakeX[0] * SCALE + 3, snake.snakeY[0] * SCALE + 3, SCALE - 6, SCALE - 6);
        }

        if(score == 50) {
            if(score > maxScore) {
                maxScore = score;
            }

            String win = "You Win!";
            String playAgain = "Если вы хотите начать игру заново нажмите: \"SPACE\"";
            String lastScore = "Твой счёт: " + score;
            String lastMaxScore = "Рекорд: " + maxScore;
            Font result = new Font("Arial", Font.BOLD, 80);
            Font printScore = new Font("Arial", Font.BOLD, 40);
            Font again = new Font("Arial", Font.BOLD, 15);

            g.setColor(Color.green);
            g.setFont(result);
            g.drawString(win, 150, WIDTH * SCALE / 2 - 30);

            g.setColor(Color.yellow);
            g.setFont(printScore);
            g.drawString(lastScore, 60, WIDTH * SCALE / 2 + 18);
            g.drawString(lastMaxScore, 380, WIDTH * SCALE / 2 + 18);

            g.setColor(Color.white);
            g.setFont(again);
            g.drawString(playAgain, 100, WIDTH * SCALE - 50);
            timer.stop();
            check = false;
        }

        for (int i = 1; i < snake.length; i++) {
            if((snake.snakeX[0] == snake.snakeX[i] && snake.snakeY[0] == snake.snakeY[i])
                    || (snake.snakeY[0] > SnakeGame.HEIGHT - 2) || (snake.snakeY[0] < 0)
                    || (snake.snakeX[0] > SnakeGame.WIDTH - 1) || (snake.snakeX[0] < 0))
            {
                if(score > maxScore) {
                    maxScore = score;
                }

                String lose = "Game Over!";
                String playAgain = "Если вы хотите начать игру заново нажмите: \"SPACE\"";
                String lastScore = "Твой счёт: " + score;
                String lastMaxScore = "Рекорд: " + maxScore;
                Font over = new Font("Arial", Font.BOLD, 80);
                Font printScore = new Font("Arial", Font.BOLD, 40);
                Font again = new Font("Arial", Font.BOLD, 15);

                g.setColor(Color.RED);
                g.setFont(over);
                g.drawString(lose, 90, WIDTH * SCALE / 2 - 30);

                g.setColor(Color.yellow);
                g.setFont(printScore);
                g.drawString(lastScore, 60, WIDTH * SCALE / 2 + 18);
                g.drawString(lastMaxScore, 380, WIDTH * SCALE / 2 + 18);

                g.setColor(Color.white);
                g.setFont(again);
                g.drawString(playAgain, 100, WIDTH * SCALE - 50);
                timer.stop();
                check = false;
            }
        }
    }

    public static void main(String[] args) {
        jFrame = new JFrame("Snake");
        jFrame.setSize(WIDTH * SCALE + 14, HEIGHT * SCALE + 6);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new SnakeGame());

        jFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        snake.move();
        if(snake.snakeX[0] == apple.positionX && snake.snakeY[0] == apple.positionY) {
            apple.setRandomPosition();
            snake.length++;
            score++;
        }
        for(int i = 1; i < snake.length; i++) {
            if(snake.snakeX[i] == apple.positionX && snake.snakeY[i] == apple.positionY) {
                apple.setRandomPosition();
            }
        }
        repaint();
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();
            if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && snake.direction != 1) {
                snake.direction = 0;
            }
            if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && snake.direction != 0) {
                snake.direction = 1;
            }
            if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && snake.direction != 3) {
                snake.direction = 2;
            }
            if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && snake.direction != 2) {
                snake.direction = 3;
            }
            if (key == KeyEvent.VK_SPACE && !check) {
                check = true;
                snake.length = 3;
                score = 3;
                snake.direction = 2;
                snake.snakeX[0] = 8;
                snake.snakeY[0] = 8;
                timer.start();
            }
        }
    }
}
