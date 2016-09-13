package mfn.game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Fyodor on 08/09/2016.
 */
class Snake {
    static final int SNAKE_INITIAL_SIZE = 5;
    private final int LEFT = 37;
    private final int UP = 38;
    private final int RIGHT = 39;
    private final int DOWN = 40;
    ArrayList<Point> body;
    private int direction;


    Snake() {
        createSnake();
    }

    void createSnake() {
        body = null;
        body = new ArrayList<>();
        for (int i = SNAKE_INITIAL_SIZE; i > 0; i--) {
            body.add(new Point(i + 2, 6));
        }
        direction = 0;
    }

    void setDirection(int direction) { //37 Left, 38 Up, 39 Right, 40 Down
        if (direction >= LEFT & direction <= DOWN) {
            if (Math.abs(this.direction - direction) != 2) {
                this.direction = direction;
            }
        }
    }

    void move() {
        int x = body.get(0).x;
        int y = body.get(0).y;
        switch (direction) {
            case LEFT:
                body.add(0, new Point(--x, y));
                body.remove(body.size() - 1);
                break;
            case UP:
                body.add(0, new Point(x, --y));
                body.remove(body.size() - 1);
                break;
            case RIGHT:
                body.add(0, new Point(++x, y));
                body.remove(body.size() - 1);
                break;
            case DOWN:
                body.add(0, new Point(x, ++y));
                body.remove(body.size() - 1);
                break;
        }
    }

    public void outOfBounds(int cellsOnWidth, int cellsOnHeight) {
        ;
        int x = body.get(0).x;
        int y = body.get(0).y;
        if (x > cellsOnWidth - 1) {
            body.set(0, new Point(0, y));
        }
        if (x < 0) {
            body.set(0, new Point(cellsOnWidth - 1, y));
        }
        if (y > cellsOnHeight - 1) {
            body.set(0, new Point(x, 0));
        }
        if (y < 0) {
            body.set(0, new Point(x, cellsOnHeight - 1));
        }
    }

    boolean isEatApple(int x, int y) {
        if (body.get(0).x == x & body.get(0).y == y) {
            body.add(new Point(x, y));
            return true;
        }
        return false;
    }

    boolean isEatPoison(Poison poison) {
        for (Point p : poison.poison) {
            if (body.get(0).x == p.x & body.get(0).y == p.y) {
                return true;
            }
        }
        return false;
    }

    boolean crosItself() {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(0).x == body.get(i).x & body.get(0).y == body.get(i).y) {
                return true;
            }
        }
        return false;
    }

    void print() {
        for (Point p : body
                ) {
            System.out.println(p.x + " " + p.y);
        }
    }
}
