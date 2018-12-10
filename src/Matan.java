import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sweeper.*;
import Sweeper.Box;

public class Matan extends JFrame {

    private Game game;
    private JPanel panel;
    private JLabel label;

    private final int cols = 10;
    private final int rows = 10;
    private final int bombs = 15;
    private final int image_size = 50;

    public static void main(String[] args) {
        new Matan();
    }

    private Matan() {
        game = new Game(cols, rows, bombs);
        game.start();
        setImage();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initPanel() {

        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                    coord.x * image_size, coord.y * image_size, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / image_size;
                int y = e.getY() / image_size;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton (coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton (coord);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * image_size, Ranges.getSize().y * image_size));
        add (panel);
    }

    private void initLabel() {
        label = new JLabel("Welcome");
        add(label, BorderLayout.SOUTH);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED:
                return "Think twice";
            case BOMBED:
                return "You lose";
            case WINNER:
                return "Congratulations";
            default:
                return "Welcome";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arabic sweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }

    private Image getImage(String name) {
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private void setImage() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }
}

