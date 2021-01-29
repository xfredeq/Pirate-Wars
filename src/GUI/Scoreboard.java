package GUI;

import Other.Users;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Scoreboard extends JPanel
{
    public BufferedImage image;
    public JButton back = new JButton("back");
    private final JLabel title=new JLabel("Pirate Wars");
    private final JLabel score=new JLabel("Scoreboard");
    private JPanel list = new JPanel(new GridLayout(1, 1));;

    private Users users;

    public Scoreboard (Users users )
    {
        this.users=users;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setFont(new Font("Arial Black", Font.BOLD, 91));
        score.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        score.setForeground(Color.CYAN);
        score.setOpaque(true);

        back.setPreferredSize(new Dimension(200, 80));
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Arial", Font.BOLD, 25));

        list.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.6f));
        list.setOpaque(true);

    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(score);
        add(list);
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

    public void showScoreboard()
    {
        users.sort();
        ArrayList<String> users = this.users.getUsers();
        ArrayList<Integer> scores = this.users.getScores();

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
    }


}
