package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login extends JPanel
{
    private BufferedImage image;
    private JLabel loginLabel=new JLabel("login:");
    private JLabel passLabel=new JLabel("password:");
    public JTextField loginField = new JTextField();
    public JPasswordField passField = new JPasswordField();
    public JButton login=new JButton("Log In");
    public JButton back=new JButton("back");
    private JLabel title=new JLabel("Pirate Wars");

    public Login ( )
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
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginField.setMaximumSize(new Dimension(200,1));
        passField.setMaximumSize(new Dimension(200,1));
        title.setFont(new Font("Verdana",Font.PLAIN,36));

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
        add(back);
        add(Box.createVerticalGlue());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }



}
