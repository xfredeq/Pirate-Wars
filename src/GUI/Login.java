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
    private final JLabel loginLabel=new JLabel("login:");
    private final JLabel passLabel=new JLabel("password:");
    public JTextField loginField = new JTextField();
    public JPasswordField passField = new JPasswordField();
    public JButton login=new JButton("Log In");
    public JButton back=new JButton("back");
    private final JLabel title=new JLabel("Pirate Wars");

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

        login.setPreferredSize(new Dimension(300, 80));
        login.setBackground(new Color(76, 165, 9, 255));
        login.setFont(new Font("Arial", Font.BOLD, 25));

        back.setPreferredSize(new Dimension(200, 80));
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Arial", Font.BOLD, 25));

        loginLabel.setPreferredSize(new Dimension(300, 80));
        loginLabel.setFont(new Font("Arial", Font.BOLD, 25));

        passLabel.setPreferredSize(new Dimension(200, 80));
        passLabel.setFont(new Font("Verdana", Font.BOLD, 25));

        loginField.setPreferredSize(new Dimension(250,25));
        passField.setPreferredSize(new Dimension(250,25));
        loginField.setMaximumSize(new Dimension(250,25));
        passField.setMaximumSize(new Dimension(250,25));


        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
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
        add(back);
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
