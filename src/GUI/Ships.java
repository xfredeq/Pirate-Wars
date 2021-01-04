package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    private static Integer size;

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

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (source == board[i][j]) {
                    color(i, j);
                }
            }
        }


        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (source == board[i][j]) {
                    int finalI = i;
                    int finalJ = j;
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run ( )
                        {
                            size = biggestShip;
                            boolean tab[][] = new boolean[fieldSize][fieldSize];
                            clear(tab);
                            if (board[finalI][finalJ].getBackground() == Color.GREEN)
                                if (!checkSize(finalI, finalJ, tab))
                                    board[finalI][finalJ].setBackground(Color.RED);

                            size = biggestShip;
                            clear(tab);
                            if (board[finalI][finalJ].getBackground() == Color.GREEN)
                            {
                                System.out.println("wchodzi\n");
                                //if (checkSize(finalI, finalJ, tab))
                                    nValidate(finalI,finalJ, tab);
                            }

                        }
                    });
                }
            }
        }

    }

    public void reset ( )
    {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
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

    private boolean checkSize (int i, int j, boolean tab[][])
    {
        //System.out.println("x: " + j + " y: " + i + " size: " + size);
        size--;


        tab[i][j] = true;
        boolean t[] = {true, true, true, true};
        if (i - 1 >= 0) {
            if (board[i - 1][j].getBackground() != Color.GREEN)
                t[0] = false;
        } else
            t[0] = false;
        if (i + 1 < fieldSize) {
            if (board[i + 1][j].getBackground() != Color.GREEN)
                t[1] = false;
        } else
            t[1] = false;
        if (j - 1 >= 0) {
            if (board[i][j - 1].getBackground() != Color.GREEN)
                t[2] = false;
        } else
            t[2] = false;
        if (j + 1 < fieldSize) {
            if (board[i][j + 1].getBackground() != Color.GREEN)
                t[3] = false;
        } else
            t[3] = false;

        if (t[0] == true && tab[i - 1][j] == false) {
            checkSize(i - 1, j, tab);
        } else {
            t[0] = true;
        }
        if (t[1] == true && tab[i + 1][j] == false) {
            checkSize(i + 1, j, tab);
        } else {
            t[1] = true;
        }
        if (t[2] == true && tab[i][j - 1] == false) {
            checkSize(i, j - 1, tab);
        } else {
            t[2] = true;
        }
        if (t[3] == true && tab[i][j + 1] == false) {
            checkSize(i, j + 1, tab);
        } else {
            t[3] = true;
        }

        if (t[0] && t[1] && t[2] && t[3] && size >= 0)
            return true;

        return false;

    }

    private void clear (boolean tab[][])
    {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                tab[i][j] = false;
        }
    }

    private void color (int i, int j)
    {
        /*if (board[i][j].getBackground() == Color.BLUE && shipSurface > 0) {
            board[i][j].setBackground(Color.GREEN);
            shipCount.setText(String.valueOf(--this.shipSurface));
        } else if (board[i][j].getBackground() != Color.BLUE) {
            if (board[i][j].getBackground() == Color.RED) {
                conflicts--;
                shipCount.setText(String.valueOf(++this.shipSurface));
            } else
                shipCount.setText(String.valueOf(++this.shipSurface));
            board[i][j].setBackground(Color.BLUE);
        } else if (board[i][j].getBackground() == Color.BLUE && shipSurface <= 0) {
            board[i][j].setBackground(Color.RED);
            conflicts++;
            shipCount.setText(String.valueOf(--this.shipSurface));
        }*/
        if (board[i][j].getBackground() == Color.BLUE && shipSurface > 0)
        {
            board[i][j].setBackground(Color.GREEN);
            shipCount.setText(String.valueOf(--this.shipSurface));
        }
        else if (board[i][j].getBackground() != Color.BLUE)
        {
            board[i][j].setBackground(Color.BLUE);
            shipCount.setText(String.valueOf(++this.shipSurface));
        }

    }

    private void nValidate(int i, int j, boolean tab[][])
    {
        size = biggestShip;
        clear(tab);

        if(i-1>=0)
            if(checkSize(i-1, j, tab) && board[i-1][j].getBackground() == Color.RED)
            {
                board[i-1][j].setBackground(Color.GREEN);
                System.out.println("1");
            }

        size = biggestShip;
        clear(tab);

        if(i+1<fieldSize)
            if(checkSize(i+1, j, tab) && board[i+1][j].getBackground() == Color.RED)
            {
                board[i+1][j].setBackground(Color.GREEN);
                System.out.println("2");
            }

        size = biggestShip;
        clear(tab);

        if(j-1>=0)
            if(checkSize(i, j-1, tab) && board[i][j-1].getBackground() == Color.RED)
            {
                board[i][j-1].setBackground(Color.GREEN);
                System.out.println("3");
            }

        size = biggestShip;
        clear(tab);

        if(j+1<fieldSize)
            if(checkSize(i, j+1, tab) && board[i][j+1].getBackground() == Color.RED)
            {
                board[i][j+1].setBackground(Color.GREEN);
                System.out.println("4");
            }


    }

}
