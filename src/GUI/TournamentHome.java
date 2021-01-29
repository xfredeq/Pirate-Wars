package GUI;

import Other.Tournament;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TournamentHome extends JPanel
{
    public BufferedImage image;

    public JButton back = new JButton("back");
    public JButton start = new JButton("Play");

    private final JLabel title=new JLabel("Pirate Wars");
    private final JLabel score=new JLabel("Scoreboard");
    private final JPanel list = new JPanel(new GridLayout(1, 1));
    private final JPanel pane = new JPanel(new GridLayout(2, 3));
    private JPanel pane2;
    private JPanel endPane;

    private JLabel victory;
    private JLabel winn;

    private final JLabel nextMatch=new JLabel("Next Match");
    private final JLabel lastMatch=new JLabel("Previous Match");

    public String name;

    private Tournament t;
    public String winner="";

    public TournamentHome (String name)
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.name=name;
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        score.setHorizontalAlignment(0);
        score.setFont(new Font("Arial Black", Font.BOLD, 80));
        score.setBackground(new Color(3, 24, 161, 255));
        score.setForeground(Color.YELLOW);
        score.setOpaque(true);
        score.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));

        list.setBackground(new Color(233, 233, 236, 190));
        list.setOpaque(true);

        start.setHorizontalAlignment(0);
        start.setVerticalAlignment(0);
        start.setBackground(new Color(60, 180, 17, 255));
        start.setFont(new Font("Calibri", Font.BOLD, 100));



        nextMatch.setHorizontalAlignment(0);
        nextMatch.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        nextMatch.setFont(new Font("Verdana", Font.BOLD, 30));
        nextMatch.setBackground(new Color(7, 206, 149, 255));
        nextMatch.setOpaque(true);


        lastMatch.setHorizontalAlignment(0);
        lastMatch.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        lastMatch.setFont(new Font("Verdana", Font.BOLD, 30));
        lastMatch.setBackground(new Color(210, 120, 41, 255));
        lastMatch.setOpaque(true);

        back.setHorizontalAlignment(0);
        back.setVerticalAlignment(0);
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Calibri", Font.BOLD, 30));

        victory = new JLabel("", SwingConstants.CENTER);
        victory.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
        victory.setOpaque(false);


        winn = new JLabel("", SwingConstants.CENTER);
        winn.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        winn.setOpaque(false);

        endPane = new JPanel();
        endPane.setLayout(new GridLayout(2,1));
        endPane.setOpaque(false);

        endPane.add(victory);
        endPane.add(winn);


        pane2 = new JPanel(new GridLayout(2, 1));
        pane2.add(nextMatch);
        pane2.add(lastMatch);
        //pane2.setBackground(new Color(187, 87, 10, 255));
        pane2.setOpaque(false);


        pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(score);
        pane.add(start);
        pane.add(pane2);
        pane.add(list);
        pane.add(endPane);
        pane.add(Box.createHorizontalGlue());
        pane.setOpaque(false);


    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(pane);
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createVerticalGlue());

        File imageFile = new File("graphics\\bg3.jpg");
        try
        {
            image = ImageIO.read(imageFile);
        }
        catch (IOException e)
        {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }



    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    public void setTournament (Tournament t)
    {
        this.t = t;
    }

    public void showTScoreboard()
    {

        ArrayList<String> users = this.t.getPlayers();
        ArrayList<Integer> scores = new ArrayList<>();

        for(int i=0;i<users.size();i++)
        {
            for(int j=0;j<users.size();j++)
            {

                if(users.get(i).equals(t.getPlayersOrder().get(j)))
                {
                    scores.add(t.getPoints().get(j));
                    break;
                }
            }
        }

        Integer tmp1;
        String tmp2;

        for(int i=0;i<users.size();i++)
        {
            for(int j=0;j<users.size();j++)
            {
                if(scores.get(j)<scores.get(i))
                {
                    tmp2=users.get(i);
                    users.set(i,users.get(j));
                    users.set(j,tmp2);

                    tmp1=scores.get(i);
                    scores.set(i,scores.get(j));
                    scores.set(j,tmp1);
                }
            }
        }



        list.setMaximumSize(new Dimension(600, users.size()*40));
        list.setPreferredSize(new Dimension(600, users.size()*40));
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        list.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.6f));
        list.setOpaque(true);

        list.setLayout(new GridLayout(users.size()+1, 3));

        JLabel tmp = new JLabel("position:");
        tmp.setFont(new Font("Verdana", Font.BOLD, 20));
        tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        tmp.setHorizontalAlignment(0);
        tmp.setOpaque(false);
        list.add(tmp);
        tmp = new JLabel("name:");
        tmp.setFont(new Font("Verdana", Font.BOLD, 20));
        tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        tmp.setHorizontalAlignment(0);
        tmp.setOpaque(false);
        list.add(tmp);
        tmp = new JLabel("points:");
        tmp.setFont(new Font("Verdana", Font.BOLD, 20));
        tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        tmp.setHorizontalAlignment(0);
        tmp.setOpaque(false);
        list.add(tmp);

        for(int i=0;i<users.size();i++)
        {
            tmp = new JLabel((i + 1) +".");
            tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
            tmp.setHorizontalAlignment(0);
            tmp.setOpaque(false);
            list.add(tmp);
            tmp=new JLabel(users.get(i));
            tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
            tmp.setHorizontalAlignment(0);
            tmp.setOpaque(false);
            list.add(tmp);
            tmp=new JLabel(String.valueOf(scores.get(i)));
            tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
            tmp.setHorizontalAlignment(0);
            tmp.setOpaque(false);
            list.add(tmp);
        }
    }
    public void clearScoreboard()
    {
        list.removeAll();
    }

    public void setMatches()
    {
        if(t.nextMatch.p1==-1)
        {
            this.start.setVisible(false);
            this.pane2.setVisible(false);

            victory.setText("VICTORY!");

            winner=this.t.getPlayers().get(0);

            winn.setText("Winner: " + winner);
            endPane.setBackground(Color.ORANGE);
            endPane.setOpaque(true);

            nextMatch.setText("Next match: none");
        }
        else
            nextMatch.setText("Next match: "+t.getPlayersOrder().get(t.nextMatch.p1) + " vs " +t.getPlayersOrder().get(t.nextMatch.p2));

        if(t.lastMatch.p1==-1)
            lastMatch.setText("Previous match: none");
        else
            lastMatch.setText("Previous match: "+t.getPlayersOrder().get(t.lastMatch.p1) + " vs " +t.getPlayersOrder().get(t.lastMatch.p2));
    }


}
