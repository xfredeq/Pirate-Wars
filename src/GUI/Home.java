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
    private final JLabel title = new JLabel("Pirate Wars");
    private final JLabel user = new JLabel("You are logged as: ");

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

        user.setPreferredSize(new Dimension(300, 40));
        user.setFont(new Font("Arial", Font.BOLD, 15));

        play.setPreferredSize(new Dimension(300, 80));
        play.setBackground(new Color(76, 165, 9, 255));
        play.setFont(new Font("Arial", Font.BOLD, 25));
        play.setForeground(Color.BLACK);

        settings.setPreferredSize(new Dimension(200, 80));
        settings.setBackground(new Color(6, 141, 203, 255));
        settings.setFont(new Font("Arial", Font.BOLD, 25));
        settings.setForeground(Color.BLACK);

        score.setPreferredSize(new Dimension(200, 80));
        score.setBackground(new Color(119, 8, 245, 255));
        score.setFont(new Font("Arial", Font.BOLD, 25));
        score.setForeground(Color.BLACK);

        credits.setPreferredSize(new Dimension(200, 80));
        credits.setBackground(new Color(217, 85, 24, 255));
        credits.setFont(new Font("Arial", Font.BOLD, 25));
        credits.setForeground(Color.BLACK);

        logout.setPreferredSize(new Dimension(200, 80));
        logout.setBackground(new Color(203, 183, 6, 255));
        logout.setFont(new Font("Arial", Font.BOLD, 25));
        logout.setForeground(Color.BLACK);

        exit.setPreferredSize(new Dimension(200, 80));
        exit.setBackground(new Color(236, 24, 49, 255));
        exit.setFont(new Font("Arial", Font.BOLD, 25));
        exit.setForeground(Color.BLACK);


        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
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
