package GUI;

import Other.Tournament;
import Other.Users;

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
    private final JLabel title=new JLabel("Pirate Wars");
    private final JLabel score=new JLabel("Scoreboard");
    private final JPanel list = new JPanel(new GridLayout(1, 1));
    private final JPanel pane = new JPanel(new GridLayout(2, 3));

    private JLabel nextMatch=new JLabel("Next Match");
    private JLabel lastMatch=new JLabel("Previous Match");

    private Tournament t;

    public TournamentHome ()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 100));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        score.setHorizontalAlignment(0);
        score.setFont(new Font("Arial Black", Font.BOLD, 91));
        score.setBackground(new Color(0.1f, 0.2f, 1.0f, 0.7f));
        score.setForeground(Color.YELLOW);
        score.setOpaque(true);
        score.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));

        list.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.6f));
        list.setOpaque(true);

        JPanel pane1 = new JPanel(new GridLayout(2, 1));
        pane1.add(Box.createHorizontalGlue());
        pane1.add(score);
        pane1.setOpaque(false);


        nextMatch.setHorizontalAlignment(0);
        nextMatch.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        nextMatch.setFont(new Font("Verdana", Font.BOLD, 60));
        //nextMatch.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.3f));
        //nextMatch.setOpaque(false);


        lastMatch.setHorizontalAlignment(0);
        lastMatch.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        lastMatch.setFont(new Font("Verdana", Font.BOLD, 60));
        //lastMatch.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.4f));
        //lastMatch.setOpaque(false);

        JPanel pane2 = new JPanel(new GridLayout(2, 1));
        pane2.add(nextMatch);
        pane2.add(lastMatch);
        pane2.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.4f));
        pane2.setOpaque(true);


        pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(pane1);
        pane.add(Box.createHorizontalGlue());
        pane.add(pane2);
        pane.add(list);
        pane.add(Box.createHorizontalGlue());
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
        t.sort();
        ArrayList<String> users = this.t.getPlayers();
        ArrayList<Integer> scores = this.t.getPoints();

        list.setMaximumSize(new Dimension(600, users.size()*40));
        list.setPreferredSize(new Dimension(600, users.size()*40));

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
            tmp = new JLabel(String.valueOf(i+1)+".");
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
        t.setGuestsCounter(1);
        t.setPlayersCounter(0);
    }

    public void setMatches()
    {
        if(t.nextMatch.p1==-1)
            nextMatch.setText("error");
        else
            nextMatch.setText("Next match: "+t.getPlayers().get(t.nextMatch.p1) + " vs " +t.getPlayers().get(t.nextMatch.p2));
    }


}
