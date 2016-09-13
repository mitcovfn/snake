package mfn.game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Fyodor on 08/09/2016.
 */
class Main extends JFrame {
    private final int WIDTH = 507;
    private final int HEIGHT = 490;
    private final String TITLE = "Snake";

    private PlayField playField;

    Main() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - WIDTH) / 2;
        int y = (dim.height - HEIGHT) / 2;
        setLocation(x, y);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
        playField = new PlayField(this);
        add(playField, BorderLayout.CENTER);
        new Thread(playField).start();


        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
