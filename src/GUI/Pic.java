package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Pic extends JPanel
{
    public BufferedImage image;
    public JButton b1=new JButton("button1");
    public JButton b2=new JButton("button2");

    public Pic ( )
    {

        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(b1);

        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(b2);


        File imageFile = new File("graphics\\bg.jpg");
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
