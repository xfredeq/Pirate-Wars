package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pic extends JPanel
{
    private BufferedImage image;
    public JButton b1=new JButton("button1");
    public JButton b2=new JButton("button2");
    public JButton b3=new JButton("button3");
    private JLabel title=new JLabel("Pirate Wars");

    public Pic ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana",Font.PLAIN,36));
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(b1);
        add(Box.createVerticalGlue());
        add(b2);
        add(Box.createVerticalGlue());
        add(b3);
        add(Box.createVerticalGlue());


        File imageFile = new File("graphics\\bg2.jpg");
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
