package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TournamentLoadLogin extends JPanel
{
    private BufferedImage image;
    private final JLabel loginLabel=new JLabel("login:");
    private final JLabel passLabel=new JLabel("password:");
    public JLabel loginField = new JLabel();
    public JPasswordField passField = new JPasswordField();
    public JButton login=new JButton("Log In");
    public JButton cancel=new JButton("Cancel Tournament loading");
    private final JLabel title=new JLabel("Pirate Wars");

    public TournamentLoadLogin ( )
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
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginField.setFont(new Font("Verdana", Font.BOLD, 20));
        loginField.setBackground(new Color(1,1,1, 40));
        loginField.setOpaque(true);

        loginField.setMaximumSize(new Dimension(200,1));
        passField.setMaximumSize(new Dimension(200,1));

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
        login.setBackground(null);
    }


}
