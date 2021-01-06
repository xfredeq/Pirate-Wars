package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ships extends JPanel implements ActionListener {
    private int fieldSize;
    private int shipSurface;
    private int maxShipSurface;
    private int biggestShip;
    private int conflicts = 0;
    public BufferedImage image;
    private JButton reset = new JButton("Reset");
    public JButton start = new JButton("Ready");
    public JButton back = new JButton("back");
    private JLabel title = new JLabel("Pirate Wars");
    private JPanel pane;
    private JLabel shipCount = new JLabel();
    private JButton[][] board;

    private static Integer size;

    public Ships (int fieldSize, int biggestShip, int shipSurface)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.fieldSize = fieldSize;
        this.biggestShip = biggestShip;
        this.shipSurface = shipSurface;
        this.maxShipSurface = shipSurface;
        shipCount.setText(String.valueOf(this.shipSurface));
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

        pane = new JPanel(new GridLayout(fieldSize, fieldSize));
        pane.setMaximumSize(new Dimension(400, 400));
        pane.setPreferredSize(new Dimension(400, 400));

        reset.addActionListener(this);

        board = new JButton[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            JButton[] tmp = new JButton[fieldSize];
            for (int j = 0; j < fieldSize; j++) {
                tmp[j] = new JButton();
                tmp[j].addActionListener(this);
                pane.add(tmp[j]);
                tmp[j].setBackground(Color.BLUE);
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


        for (int i = 0; i < fieldSize; i++)
        {
            for (int j = 0; j < fieldSize; j++)
            {
                int finalI = i;
                int finalJ = j;
                size = biggestShip;
                boolean tab[][] = new boolean[fieldSize][fieldSize];
                clear(tab);

                if (source == board[i][j])
                {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run ( )
                        {
                            if (board[finalI][finalJ].getBackground() == Color.GREEN)
                                if (!checkSize(finalI, finalJ, tab))
                                {
                                    board[finalI][finalJ].setBackground(Color.RED);
                                    conflicts++;
                                }
                        }
                    });

                }
                if (board[finalI][finalJ].getBackground() == Color.RED)
                    nValidate(finalI,finalJ, tab);
            }
        }

    }

    public void reset ( )
    {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                this.board[i][j].setBackground(Color.BLUE);
        }
        shipSurface=maxShipSurface;
        shipCount.setText(String.valueOf(shipSurface));
        conflicts=0;
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

        if (size >= 0)
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
        if (board[i][j].getBackground() == Color.BLUE && shipSurface > 0)
        {
            board[i][j].setBackground(Color.GREEN);
            shipCount.setText(String.valueOf(--this.shipSurface));
        }
        else if (board[i][j].getBackground() == Color.GREEN)
        {
            board[i][j].setBackground(Color.BLUE);
            shipCount.setText(String.valueOf(++this.shipSurface));
        }
        else if (board[i][j].getBackground() == Color.RED)
        {
            board[i][j].setBackground(Color.BLUE);
            conflicts--;
            shipCount.setText(String.valueOf(++this.shipSurface));
        }

    }

    private void nValidate(int i, int j, boolean tab[][])
    {
        size = biggestShip;
        clear(tab);
        if (checkSize(i, j, tab))
        {
            board[i][j].setBackground(Color.GREEN);
            conflicts--;
        }
    }

    public int getConflicts ( )
    {
        return conflicts;
    }
    public int getShipSurface ( )
    {
        return shipSurface;
    }

    public boolean[][] getBoard()
    {
        boolean brd[][]=new boolean[fieldSize][fieldSize];
        clear(brd);
        for(int i=0;i<fieldSize;i++)
        {
            for(int j=0;j<fieldSize;j++)
            {
                if(board[i][j].getBackground()==Color.GREEN)
                brd[i][j]=true;
            }
        }
        return brd;
    }

    private boolean checkRandomSize (int i, int j, boolean brd[][], boolean tab[][])
    {
        size--;

        tab[i][j] = true;
        boolean t[] = {true, true, true, true};
        if (i - 1 >= 0) {
            if (!brd[i - 1][j])
                t[0] = false;
        } else
            t[0] = false;
        if (i + 1 < fieldSize) {
            if (!brd[i + 1][j])
                t[1] = false;
        } else
            t[1] = false;
        if (j - 1 >= 0) {
            if (!brd[i][j - 1])
                t[2] = false;
        } else
            t[2] = false;
        if (j + 1 < fieldSize) {
            if (!brd[i][j + 1])
                t[3] = false;
        } else
            t[3] = false;

        if (t[0] == true && tab[i - 1][j] == false) {
            checkRandomSize(i - 1, j, brd, tab);
        } else {
            t[0] = true;
        }
        if (t[1] == true && tab[i + 1][j] == false) {
            checkRandomSize(i + 1, j, brd, tab);
        } else {
            t[1] = true;
        }
        if (t[2] == true && tab[i][j - 1] == false) {
            checkRandomSize(i, j - 1, brd, tab);
        } else {
            t[2] = true;
        }
        if (t[3] == true && tab[i][j + 1] == false) {
            checkRandomSize(i, j + 1, brd, tab);
        } else {
            t[3] = true;
        }

        if (size >= 0)
            return true;

        return false;

    }

    public boolean[][] makeRandomBoard ( )
    {
        boolean brd[][] = new boolean[fieldSize][fieldSize];
        boolean visited[][] = new boolean[fieldSize][fieldSize];
        clear(visited);
        clear(brd);
        int surface = maxShipSurface;
        Random rand = new Random();

        while(surface>0)
        {
            clear(visited);
            size=biggestShip;

            int r1=rand.nextInt(fieldSize);
            int r2=rand.nextInt(fieldSize);

            //out.println("all " + r2 + " " + r1 + " " + surface);
            if (brd[r1][r2] == false)
            {
                if (checkRandomSize(r1, r2, brd, visited))
                {
                    brd[r1][r2] = true;
                    //System.out.println("tak " + r2 + " " + r1);
                    surface--;
                }
                else
                {
                    //System.out.println("nie " + r2 + " " + r1);
                    brd[r1][r2] = false;
                    //surface++;
                }
            }
        }


        return brd;
    }
}
