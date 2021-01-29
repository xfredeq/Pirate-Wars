package GUI;

import Other.Tournament;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TournamentLoad extends JPanel
{
    public BufferedImage image;
    public JButton back = new JButton("back");
    private final JLabel title=new JLabel("Pirate Wars");
    private final JLabel tournaments_label =new JLabel("Tournaments");

    private ArrayList<Tournament> tournaments;

    private final JPanel list = new JPanel(new GridLayout(1, 1));

    private JButton[] buttons = new JButton[1];



    public TournamentLoad()
    {

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {

        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        tournaments_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        back.setPreferredSize(new Dimension(200, 40));
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Arial", Font.BOLD, 25));

        tournaments_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        tournaments_label.setFont(new Font("Arial Black", Font.BOLD, 91));
        tournaments_label.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        tournaments_label.setForeground(Color.MAGENTA);
        tournaments_label.setOpaque(true);

        list.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.6f));
        list.setOpaque(true);

    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(tournaments_label);
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

    public void showTournaments()
    {
        this.buttons=new JButton[this.tournaments.size()];


        list.setMaximumSize(new Dimension(600, Integer.min(8000,  100*this.tournaments.size())));
        list.setPreferredSize(new Dimension(600,  100*this.tournaments.size()));

        list.setLayout(new GridLayout(this.tournaments.size()+1, 2));

        JLabel tmp = new JLabel("name:");
        tmp.setFont(new Font("Verdana", Font.BOLD, 20));
        tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        tmp.setHorizontalAlignment(0);
        tmp.setOpaque(false);
        list.add(tmp);
        tmp = new JLabel("load:");
        tmp.setFont(new Font("Verdana", Font.BOLD, 20));
        tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
        tmp.setHorizontalAlignment(0);
        tmp.setOpaque(false);
        list.add(tmp);


        for(int i=0;i<this.tournaments.size();i++)
        {
            tmp = new JLabel(this.tournaments.get(i).name);
            tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
            tmp.setHorizontalAlignment(0);
            tmp.setOpaque(false);
            list.add(tmp);

            JButton tmpB=new JButton("LOAD " + this.tournaments.get(i).name);
            tmp.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK, Color.BLACK));
            tmp.setHorizontalAlignment(0);
            tmp.setOpaque(false);
            list.add(tmpB);
            this.buttons[i]=tmpB;
        }
    }
    public void clearTournaments()
    {
        list.removeAll();
    }

    public void setTournaments(ArrayList<Tournament> t)
    {
        this.tournaments=t;
    }



    public JButton[] getButtons ( )
    {
        return buttons;
    }
}
