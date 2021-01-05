package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements ActionListener {
    private int fieldSize;
    private JPanel pane;
    private JLabel title = new JLabel("Pirate Wars");
    public BufferedImage image;
    private JButton[][] board1, board2;
    private int[][] tab1, tab2;

    private JLabel rnd=new JLabel("tgtg");



    private JButton reset = new JButton("GAME");
    public JButton start = new JButton("Start");
    public JButton back = new JButton("back");



    private static Integer size;

    public Game (int fieldSize, boolean[][] brd1, boolean[][] brd2)
    {
        this.fieldSize=fieldSize;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents(brd1, brd2);
        addComponents();
    }


    private void setComponents (boolean[][] brd1, boolean[][] brd2 )
    {
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setFont(new Font("Verdana", Font.PLAIN, 36));

        pane = new JPanel(new GridLayout(1, 3));
        pane.setMaximumSize(new Dimension(1800, 600));
        pane.setPreferredSize(new Dimension(1800, 600));
        pane.setOpaque(false);

        JPanel pane1 = new JPanel(new GridLayout(fieldSize, fieldSize));
        JPanel pane2 = new JPanel(new GridLayout(fieldSize, fieldSize));

        pane.add(pane1);
        pane.add(Box.createHorizontalGlue());
        pane.add(pane2);

        tab1=new int[fieldSize][fieldSize];
        tab2=new int[fieldSize][fieldSize];

        int x=0;
        for(int i=0;i<fieldSize;i++)
        {
            for(int j=0;j<fieldSize;j++)
            {
                if(brd1[i][j]==true)
                    tab1[i][j]=1;
                else
                    tab1[i][j]=0;

                if(brd2[i][j]==true)
                {tab2[i][j]=1;
                rnd.setText(String.valueOf(++x));}
                else
                    tab2[i][j]=0;
            }
        }


        board1 = new JButton[fieldSize][fieldSize];
        board2 = new JButton[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++)
        {
            JButton[] tmp1 = new JButton[fieldSize];
            JButton[] tmp2 = new JButton[fieldSize];
            for (int j = 0; j < fieldSize; j++)
            {
                tmp1[j] = new JButton();
                tmp1[j].addActionListener(this);
                pane1.add(tmp1[j]);
                if(tab1[i][j]==1)
                    tmp1[j].setBackground(Color.GREEN);
                else
                    tmp1[j].setBackground(Color.BLUE);


                tmp2[j] = new JButton();
                tmp2[j].addActionListener(this);
                pane2.add(tmp2[j]);
                if(tab2[i][j]==1)
                    tmp2[j].setBackground(Color.GREEN);
                else
                    tmp2[j].setBackground(Color.BLUE);
                tmp2[j].setText(j+" "+i);
                tmp1[j].setText(j+" "+i);
            }
            board1[i] = tmp1;
            board2[i] = tmp2;
        }

    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(reset);
        add(Box.createVerticalGlue());
        add(rnd);
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

    }


    private void clear (boolean tab[][])
    {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                tab[i][j] = false;
        }
    }




}
