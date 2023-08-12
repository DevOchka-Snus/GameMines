import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mines.Box;
import mines.Coordinates;
import mines.Game;
import mines.Range;

public class GameMiner extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int BOMBS  = 10;
    private final int IMAGE_SIZE = 50;
    public static void main(String[] args) {
        new GameMiner().setVisible(true);
    }
    private GameMiner() {
        game = new Game(COLUMNS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }
    private void initPanel() {
        panel = new JPanel()
            {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    for (Coordinates coord : Range.getAllCoords()) {
                        g.drawImage((Image) game.getBox(coord).image,
                                coord.getX() * IMAGE_SIZE,
                                coord.getY() * IMAGE_SIZE,this);
                    }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinates coord = new Coordinates(x,y);
                if(e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if(e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Range.getSize().getX() * IMAGE_SIZE,
                Range.getSize().getY() * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED -> {return "THINK TWICE!";}
            case BOMBED -> {return "YOU LOSE!";}
            case WINNER -> {return "YOU WIN!";}
            default -> {return "WELCOME!";}
        }
    }

    private void initLabel() {
        label = new JLabel();
        add(label, BorderLayout.SOUTH);
    }
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Game Miner");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }
    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }
    private Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}