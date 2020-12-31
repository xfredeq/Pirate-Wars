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
    private JTextField loginField = new JTextField();
    private JPasswordField passField = new JPasswordField();
    public JButton login=new JButton("Log In");
    public JButton back=new JButton("back");
    private JLabel title=new JLabel("Pirate Wars");

    public Login ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginField.setMaximumSize(new Dimension(200,1));
        passField.setMaximumSize(new Dimension(200,1));
        title.setFont(new Font("Verdana",Font.PLAIN,36));
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(loginLabel);
        //add(Box.createVerticalGlue());
        add(loginField);
        add(Box.createVerticalGlue());
        add(passLabel);
        //add(Box.createVerticalGlue());
        add(passField);
        add(Box.createVerticalGlue());

        add(login);
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createVerticalGlue());


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
