package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Play extends JPanel
{
    public BufferedImage image;
    public JButton easy=new JButton("Easy");
    public JButton medium=new JButton("Medium");
    public JButton hard=new JButton("Hard - coming soon");
    public JButton guest=new JButton("Play vs Guest");
    public JButton player=new JButton("Play vs 2. player");
    public JButton tournament=new JButton("Tournament");
    public JButton back=new JButton("back");
    private final JLabel title=new JLabel("Pirate Wars");
    private final JLabel single=new JLabel("SinglePlayer:");
    private final JLabel multi=new JLabel("MultiPlayer:");

    public Play ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        easy.setAlignmentX(Component.CENTER_ALIGNMENT);
        medium.setAlignmentX(Component.CENTER_ALIGNMENT);
        hard.setAlignmentX(Component.CENTER_ALIGNMENT);
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        guest.setAlignmentX(Component.CENTER_ALIGNMENT);
        tournament.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        multi.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        single.setPreferredSize(new Dimension(300, 80));
        single.setFont(new Font("Arial", Font.BOLD, 40));
        single.setForeground(Color.BLACK);

        multi.setPreferredSize(new Dimension(300, 80));
        multi.setFont(new Font("Arial", Font.BOLD, 40));
        multi.setForeground(Color.BLACK);

        easy.setPreferredSize(new Dimension(300, 60));
        easy.setBackground(new Color(76, 165, 9, 255));
        easy.setFont(new Font("Arial", Font.BOLD, 25));
        easy.setForeground(Color.BLACK);

        medium.setPreferredSize(new Dimension(200, 60));
        medium.setBackground(new Color(217, 85, 24, 255));
        medium.setFont(new Font("Arial", Font.BOLD, 25));
        medium.setForeground(Color.BLACK);

        hard.setPreferredSize(new Dimension(200, 60));
        hard.setBackground(new Color(236, 24, 49, 255));
        hard.setFont(new Font("Arial", Font.BOLD, 25));
        hard.setForeground(Color.BLACK);

        guest.setPreferredSize(new Dimension(200, 60));
        guest.setBackground(new Color(6, 141, 203, 255));
        guest.setFont(new Font("Arial", Font.BOLD, 25));
        guest.setForeground(Color.BLACK);

        player.setPreferredSize(new Dimension(200, 60));
        player.setBackground(new Color(119, 8, 245, 255));
        player.setFont(new Font("Arial", Font.BOLD, 25));
        player.setForeground(Color.BLACK);

        tournament.setPreferredSize(new Dimension(200, 60));
        tournament.setBackground(new Color(238, 101, 191, 255));
        tournament.setFont(new Font("Arial", Font.BOLD, 25));
        tournament.setForeground(Color.BLACK);

        back.setPreferredSize(new Dimension(200, 60));
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Arial", Font.BOLD, 25));
        back.setForeground(Color.BLACK);


        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(single);
        add(Box.createVerticalGlue());
        add(easy);
        add(Box.createVerticalGlue());
        add(medium);
        add(Box.createVerticalGlue());
        add(hard);
        add(Box.createVerticalGlue());
        add(multi);
        add(Box.createVerticalGlue());
        add(guest);
        add(Box.createVerticalGlue());
        add(player);
        add(Box.createVerticalGlue());
        add(tournament);
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



}
