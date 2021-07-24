import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        add(new GameSteps());
        setVisible(true);
    }

    public static void main(String[] args) {
        SnakeGame sg = new SnakeGame();
    }
}

class test{
    for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
        g.setColor(Color.darkGray);
        g.drawLine(x,0,x,HEIGHT * SCALE);
    }
        for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
        g.setColor(Color.darkGray);
        g.drawLine(0,x,WIDTH * SCALE, x);
    }
}
