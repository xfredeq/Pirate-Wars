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
    public JButton hard=new JButton("Hard - TBD");
    public JButton guest=new JButton("Play vs Guest");
    public JButton player=new JButton("Play vs 2. player");
    public JButton back=new JButton("back");
    private JLabel title=new JLabel("Pirate Wars");
    private JLabel single=new JLabel("SinglePlayer");
    private JLabel multi=new JLabel("MultiPlayer");

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
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        multi.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 100));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        hard.setBackground(Color.RED);
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
