package mfn.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Fyodor on 08/09/2016.
 */
class PlayField extends JPanel implements Runnable {

    private final int CELLSIZE = 20;
    private final int CELLS_OF_WIDTH = 25;
    private final int CELLS_OF_HEIGHT = 23;
    private final int GAME_SPEED = 250;
    private final String GAME_OVER_MSG = "GAME OVER";
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 48);

    private boolean gameOver;

    private Snake snake;
    private Poison poison;
    private Main mainFrame;
    private Apple apple;

    PlayField(Main mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(new Color(255, 254, 151));
        snake = new Snake();
        apple = new Apple(CELLS_OF_WIDTH, CELLS_OF_HEIGHT);
        apple.setApple(snake);
        poison = new Poison(CELLS_OF_WIDTH, CELLS_OF_HEIGHT);
        poison.addPoison(snake, apple.x, apple.y);
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                snake.setDirection(e.getKeyCode());  //37 Left, 38 Up, 39 Right, 40 Down
            }
        });


        gameOver = false;
    }

    @Override
    public void run() {
        while (!gameOver) {
            try {
                snake.move();
                if (snake.crosItself()) gameOver = true;
                if (snake.isEatApple(apple.x, apple.y)) {
                    apple.setApple(snake);
                    poison.addPoison(snake, apple.x, apple.y);
                }
                if (snake.isEatPoison(poison)) gameOver = true;
                snake.outOfBounds(CELLS_OF_WIDTH, CELLS_OF_HEIGHT);
                repaint();
                Thread.sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);


        //paint grid

        /*
    }for (int i = 0; i < getWidth(); i++) {
            g.drawLine(0, i * CELLSIZE, getWidth() * CELLSIZE, i * CELLSIZE);

        for (int i = 0; i < getHeight(); i++) {
            g.drawLine(i * CELLSIZE, 0, i * CELLSIZE, getHeight() * CELLSIZE);
        }*/

    }

    private void render(Graphics g) {
        //paint snake
        g.setColor(Color.red);
        g.fillOval(snake.body.get(0).x * CELLSIZE, snake.body.get(0).y * CELLSIZE, CELLSIZE, CELLSIZE);
        g.setColor(Color.green);
        for (int i = 1; i < snake.body.size(); i++) {
            g.fillOval(snake.body.get(i).x * CELLSIZE, snake.body.get(i).y * CELLSIZE, CELLSIZE, CELLSIZE);
        }

        // paint apple
        g.setColor(Color.ORANGE);
        g.fillOval(apple.x * CELLSIZE, apple.y * CELLSIZE, CELLSIZE, CELLSIZE);

        //paint poison
        g.setColor(Color.magenta);
        for (Point p : poison.poison) {
            g.fillOval(p.x * CELLSIZE, p.y * CELLSIZE, CELLSIZE, CELLSIZE);
        }
        //paint gameOver
        if (gameOver) {
            g.setColor(Color.gray);
            g.fillRect(0, getHeight() / 2 - 43, getWidth(), 48);
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString(GAME_OVER_MSG, getWidth() / 5, getHeight() / 2);
        }
    }


}
