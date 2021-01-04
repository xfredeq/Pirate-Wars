package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ships extends JPanel implements ActionListener {
    private int fieldSize = 8;
    private int shipSurface;
    private int biggestShip;
    private int maxShipSurface;
    private int conflicts = 0;
    public BufferedImage image;
    private JButton reset = new JButton("Reset");
    public JButton start = new JButton("Start");
    public JButton back = new JButton("back");
    private JLabel title = new JLabel("Pirate Wars");
    private JPanel pane = new JPanel(new GridLayout(fieldSize, fieldSize));
    private JLabel shipCount = new JLabel();
    private JButton[][] board;

    public Ships ( )
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        shipCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        //shipCount.setText(String.valueOf(this.shipSurface));

        pane.setMaximumSize(new Dimension(500, 500));
        pane.setPreferredSize(new Dimension(500, 500));

        reset.addActionListener(this);

        board = new JButton[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            JButton[] tmp = new JButton[fieldSize];
            for (int j = 0; j < fieldSize; j++) {
                tmp[j] = new JButton();
                tmp[j].addActionListener(this);
                pane.add(tmp[j]);
                tmp[j].setBackground(Color.blue);
            }
            board[i] = tmp;
        }

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.PLAIN, 36));
    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(reset);
        add(Box.createVerticalGlue());
        add(shipCount);
        add(pane);
        add(Box.createVerticalGlue());
        add(start);
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createVerticalGlue());

        File imageFile = new File("graphics\\bg3.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }


    @Override
    public void paintComponent (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }


    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource();

        if (source == reset) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run ( )
                {
                    reset();
                    shipSurface = maxShipSurface;
                    shipCount.setText(String.valueOf(shipSurface));
                }
            });
        }
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (source == board[i][j])
                {
                    if (board[i][j].getBackground() == Color.BLUE && shipSurface > 0)
                    {
                        board[i][j].setBackground(Color.GREEN);
                        shipCount.setText(String.valueOf(--this.shipSurface));
                    }
                    else if (board[i][j].getBackground() != Color.BLUE)
                    {
                        if(board[i][j].getBackground() == Color.RED)
                            conflicts--;
                        else
                            shipCount.setText(String.valueOf(++this.shipSurface));
                        board[i][j].setBackground(Color.BLUE);
                    }
                    else if (board[i][j].getBackground() == Color.BLUE && shipSurface <= 0)
                    {
                        board[i][j].setBackground(Color.RED);
                        conflicts++;
                    }
                }
            }
        }
    }

    public void reset ( )
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                this.board[i][j].setBackground(Color.blue);
        }
    }

    public void getParams (int fieldSize, int biggestShip, int maxShipSurface)
    {
        this.fieldSize = fieldSize;
        this.biggestShip = biggestShip;
        this.maxShipSurface = maxShipSurface;
        this.shipSurface = maxShipSurface;
        shipCount.setText(String.valueOf(this.shipSurface));
    }
}
