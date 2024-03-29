package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Start extends JPanel
{
    public BufferedImage image;
    public JButton login=new JButton("Log In");
    public JButton signin=new JButton("Sign In");
    public JButton guest=new JButton("Play as Guest");
    public JButton exit = new JButton("Exit");
    private final JLabel title=new JLabel("Pirate Wars");

    public Start ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        signin.setAlignmentX(Component.CENTER_ALIGNMENT);
        guest.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        login.setPreferredSize(new Dimension(300, 80));
        login.setBackground(new Color(76, 165, 9, 255));
        login.setFont(new Font("Arial", Font.BOLD, 25));

        signin.setPreferredSize(new Dimension(200, 80));
        signin.setBackground(new Color(6, 141, 203, 255));
        signin.setFont(new Font("Arial", Font.BOLD, 25));

        guest.setPreferredSize(new Dimension(200, 80));
        guest.setBackground(new Color(203, 183, 6, 255));
        guest.setFont(new Font("Arial", Font.BOLD, 25));

        exit.setPreferredSize(new Dimension(200, 80));
        exit.setBackground(new Color(236, 24, 49, 255));
        exit.setFont(new Font("Arial", Font.BOLD, 25));


        title.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        add(login);
        add(Box.createVerticalGlue());
        add(signin);
        add(Box.createVerticalGlue());
        add(guest);
        add(Box.createVerticalGlue());
        add(exit);
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
