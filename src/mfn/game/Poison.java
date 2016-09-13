package mfn.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fyodor on 13/09/2016.
 */
public class Poison {

    ArrayList<Point> poison = new ArrayList<>();
    private Random random = new Random();
    private int cellsOfWidth;
    private int cellsOfHeight;

    Poison(int x, int y) {
        cellsOfWidth = x;
        cellsOfHeight = y;
    }

    void addPoison(Snake snake, int appleX, int appleY) {
        int x;
        int y;
        do {
            x = random.nextInt(cellsOfWidth);
            y = random.nextInt(cellsOfHeight);
            if (appleX == x & appleY == y) continue;
            if (isSameCeelPoison(x, y)) continue;
        } while (containsInSnake(snake, x, y));
        poison.add(new Point(x, y));
    }

    boolean isSameCeelPoison(int x, int y) {
        for (Point p : poison) {
            if (p.x == x & p.y == y) return true;
        }
        return false;
    }

    boolean containsInSnake(Snake snake, int x, int y) {
        for (Point p : snake.body) {
            if (p.x == x & p.y == y) return true;
        }
        return false;
    }
}
