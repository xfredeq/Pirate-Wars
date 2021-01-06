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
    private final int fieldSize;
    private int p1;
    private int p2;
    private boolean turn=false;
    private int[][] tab1, tab2;
    private String winner="";
    private String user1;
    private String user2;
    private String gameMode;

    private JPanel pane, controlPane;

    private final JLabel title = new JLabel("Pirate Wars");
    private final JLabel vs = new JLabel("vs", SwingConstants.CENTER);
    private JLabel points1;
    private JLabel points2;
    private JLabel victory;
    private JLabel winn;

    private JPanel endPane;

    public BufferedImage image;

    private JButton[][] board1, board2;
    public JButton sur1 = new JButton("Surrender");
    public JButton sur2 = new JButton("Surrender");
    public JButton turn1 = new JButton();
    public JButton turn2 = new JButton();
    public JButton back = new JButton("Back to main menu");


    public Game (String mode, int fieldSize, boolean[][] brd1, boolean[][] brd2,int shipSurface, String user1, String user2)
    {
        this.fieldSize = fieldSize;
        this.p1 = shipSurface;
        this.p2 = shipSurface;
        this.user1 = user1;
        this.user2 = user2;
        this.gameMode=mode;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents(brd1, brd2);
        addComponents();

    }


    private void setComponents (boolean[][] brd1, boolean[][] brd2)
    {
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        vs.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setVisible(false);

        sur1.addActionListener(this);
        sur2.addActionListener(this);

        JLabel player1;
        if(user1.equals(""))
        {
            player1 =new JLabel("Guest1", SwingConstants.CENTER);
            user1="Guest1";
        }
        else
            player1 =new JLabel(user1, SwingConstants.CENTER);

        JLabel player2;
        if(user2.equals(""))
        {
            player2 =new JLabel("Guest2", SwingConstants.CENTER);
            user2="Guest2";
        }
        else
            player2 =new JLabel(user2, SwingConstants.CENTER);

        points1=new JLabel("ships: " + p1, SwingConstants.CENTER);
        points2=new JLabel("ships: " + p2, SwingConstants.CENTER);


        title.setFont(new Font("Verdana", Font.PLAIN, 36));

        pane = new JPanel(new GridLayout(1, 3));
        pane.setMaximumSize(new Dimension(1800, 600));
        pane.setPreferredSize(new Dimension(1800, 600));
        pane.setOpaque(false);

        JPanel pane1 = new JPanel(new GridLayout(fieldSize, fieldSize));
        JPanel pane2 = new JPanel(new GridLayout(fieldSize, fieldSize));

        endPane = new JPanel();
        endPane.setLayout(new GridLayout(2,1));
        endPane.setOpaque(false);

        victory = new JLabel("", SwingConstants.CENTER);
        victory.setFont(new Font("Verdana", Font.PLAIN, 36));
        victory.setOpaque(false);

        winn = new JLabel("", SwingConstants.CENTER);
        winn.setFont(new Font("Verdana", Font.PLAIN, 24));
        winn.setOpaque(false);

        endPane.add(victory);
        endPane.add(winn);

        pane.add(pane1);
        pane.add(endPane);
        pane.add(pane2);

        controlPane = new JPanel(new GridLayout(1, 9));
        controlPane.setMaximumSize(new Dimension(1800, 100));
        controlPane.setPreferredSize(new Dimension(1800, 100));
        controlPane.setOpaque(false);
        controlPane.add(turn1);
        controlPane.add(points1);
        controlPane.add(sur1);
        controlPane.add(player1);
        controlPane.add(vs);
        controlPane.add(player2);
        controlPane.add(turn2);
        controlPane.add(points2);
        controlPane.add(sur2);

        turn1.setBackground(Color.GREEN);
        turn2.setBackground(Color.RED);

        tab1=new int[fieldSize][fieldSize];
        tab2=new int[fieldSize][fieldSize];

        for(int i=0;i<fieldSize;i++)
        {
            for(int j=0;j<fieldSize;j++)
            {
                if(brd2[i][j])
                    tab1[i][j]=1;
                else
                    tab1[i][j]=0;

                if(brd1[i][j])
                    tab2[i][j]=1;
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
                tmp1[j].setBackground(Color.BLUE);


                tmp2[j] = new JButton();
                tmp2[j].addActionListener(this);
                pane2.add(tmp2[j]);
                tmp2[j].setBackground(Color.BLUE);
                /*tmp2[j].setText(j+" "+i);
                /tmp1[j].setText(j+" "+i); */
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
        add(controlPane);
        add(pane);
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createVerticalGlue());

        File imageFile = new File("graphics\\bg2.jpg");
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
        if(gameMode.equals("easy"))
            easyGame(source);
        else if(gameMode.equals("guest") || gameMode.equals("player"))
            vsPlayerGame(source);

        if(source==sur1)
        {
            winner=user2;
            showEnd();
        }
        else if(source==sur2)
        {
            winner=user1;
            showEnd();
        }

    }


    // 0-woda, 1-statek, 2-trafiony, 3-pudło, 4-zatopiony

    private void vsPlayerGame (Object source )
    {
            for (int i = 0; i < fieldSize; i++)
            {
                for (int j = 0; j < fieldSize; j++)
                {
                    if (p1 > 0 && p2 > 0)
                    {
                        if (!turn && source == board1[i][j])
                        {
                            if (tab1[i][j] == 1) {
                                board1[i][j].setBackground(Color.pink);
                                tab1[i][j] = 2;
                                points2.setText("ships: " + (--p2));
                            } else if (tab1[i][j] == 0)
                            {
                                board1[i][j].setBackground(Color.BLACK);
                                tab1[i][j] = 3;
                                turn = true;
                                turn1.setBackground(Color.RED);
                                turn2.setBackground(Color.GREEN);
                            }
                        }

                        if (turn && source == board2[i][j])
                        {
                            if (tab2[i][j] == 1) {
                                board2[i][j].setBackground(Color.pink);
                                tab2[i][j] = 2;
                                points1.setText("ships: " + (--p1));
                            } else if (tab2[i][j] == 0)
                            {
                                board2[i][j].setBackground(Color.BLACK);
                                tab2[i][j] = 3;
                                turn = false;
                                turn2.setBackground(Color.RED);
                                turn1.setBackground(Color.GREEN);
                            }
                        }
                    }
                    else
                    {
                        if(p1==0)
                            winner = user2;
                        else
                            winner = user1;

                        showEnd();
                    }
                }

            }

    }

    private void easyGame(Object source)
    {

    }

    private void showEnd()
    {
        victory.setText("VICTORY!");
        winn.setText("Winner: "+winner);
        endPane.setBackground(Color.ORANGE);
        endPane.setOpaque(true);
        back.setVisible(true);
    }

}
