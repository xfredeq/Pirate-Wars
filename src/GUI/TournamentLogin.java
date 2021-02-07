package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TournamentLogin extends JPanel
{
    private BufferedImage image;
    private final JLabel loginLabel=new JLabel("login:");
    private final JLabel passLabel=new JLabel("password:");
    public JTextField loginField = new JTextField();
    public JPasswordField passField = new JPasswordField();
    public JButton login=new JButton("Log In");
    public JButton cancel=new JButton("Cancel Tournament creation");
    public JButton guest=new JButton("add Guest");
    private final JLabel title=new JLabel("Pirate Wars");

    public TournamentLogin ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        guest.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginField.setMaximumSize(new Dimension(200,1));
        passField.setMaximumSize(new Dimension(200,1));

        login.setPreferredSize(new Dimension(300, 80));
        login.setBackground(new Color(76, 165, 9, 255));
        login.setFont(new Font("Arial", Font.BOLD, 25));

        guest.setPreferredSize(new Dimension(200, 80));
        guest.setBackground(new Color(203, 183, 6, 255));
        guest.setFont(new Font("Arial", Font.BOLD, 25));

        cancel.setPreferredSize(new Dimension(200, 80));
        cancel.setBackground(new Color(236, 24, 49, 255));
        cancel.setFont(new Font("Arial", Font.BOLD, 25));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 100));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        File imageFile = new File("graphics\\bg1.jpg");
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

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(loginLabel);
        add(loginField);
        add(Box.createVerticalGlue());
        add(passLabel);
        add(passField);
        add(Box.createVerticalGlue());
        add(login);
        add(Box.createVerticalGlue());
        add(guest);
        add(Box.createVerticalGlue());
        add(cancel);
        add(Box.createVerticalGlue());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    public void clearFields()
    {
        loginField.setText("");
        passField.setText("");
        login.setBackground(new Color(76, 165, 9, 255));
    }


}
