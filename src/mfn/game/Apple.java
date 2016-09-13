package mfn.game;

import java.awt.*;
import java.util.Random;


/**
 * Created by Fyodor on 13/09/2016.
 */
public class Apple extends Point {

    Random random = new Random();
    private int cellsOfWidth;
    private int cellsOfHeight;

    Apple(int x, int y) {
        cellsOfWidth = x;
        cellsOfHeight = y;
    }

    void setApple(Snake snake) {
        do {
            x = random.nextInt(cellsOfWidth);
            y = random.nextInt(cellsOfHeight);
        } while (containsInSnake(snake, x, y));
    }

    boolean containsInSnake(Snake snake, int x, int y) {
        for (Point p : snake.body) {
            if (p.x == x & p.y == y) return true;
        }
        return false;
    }
}
