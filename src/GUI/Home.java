package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home extends JPanel {
    public BufferedImage image;
    public JButton play = new JButton("Play");
    public JButton settings = new JButton("Settings");
    public JButton score = new JButton("Scoreboard");
    public JButton credits = new JButton("Credits");
    public JButton logout = new JButton("Logout");
    public JButton exit = new JButton("Exit");
    private JLabel title = new JLabel("Pirate Wars");
    private JLabel user = new JLabel("You are logged as: ");

    public Home ( )
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        user.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 100));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);
    }

    private void addComponents ( )
    {
        add(user);
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(play);
        add(Box.createVerticalGlue());
        add(settings);
        add(Box.createVerticalGlue());
        add(score);
        add(Box.createVerticalGlue());
        add(credits);
        add(Box.createVerticalGlue());
        add(logout);
        add(Box.createVerticalGlue());
        add(exit);
        add(Box.createVerticalGlue());

        File imageFile = new File("graphics\\bg3.jpg");
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

    public void setUsername (String name)
    {
        if (!name.equals(""))
            user.setText("You are logged as: " + name);
        else
            user.setText("You are logged as: Guest");
    }


}
