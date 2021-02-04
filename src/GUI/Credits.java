package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Credits extends JPanel
{
    private BufferedImage image;
    private final JLabel creditsLabel =new JLabel("Credits");
    private final JLabel link1Label =new JLabel("Background_1:",SwingConstants.CENTER);
    private final JLabel link2Label =new JLabel("Background_2:",SwingConstants.CENTER);
    private final JLabel link3Label =new JLabel("Background_3:",SwingConstants.CENTER);
    public JTextArea link1 = new JTextArea();
    public JTextArea link2 = new JTextArea();
    public JTextArea link3 = new JTextArea();
    public JButton back=new JButton("back");
    private final JLabel title=new JLabel("Pirate Wars");

    private final JPanel panel = new JPanel(new GridLayout(3,2));

    public Credits ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 120));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        back.setPreferredSize(new Dimension(200, 80));
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Arial", Font.BOLD, 25));

        creditsLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 100));
        creditsLabel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        creditsLabel.setForeground(Color.BLUE);
        creditsLabel.setOpaque(true);

        panel.setMaximumSize(new Dimension(1000, 600));
        panel.setPreferredSize(new Dimension(8000, 400));
        panel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        panel.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY, Color.DARK_GRAY) );

     //  link1.setPreferredSize(new Dimension(250,25));
     //  link2.setPreferredSize(new Dimension(250,25));
     //  link3.setPreferredSize(new Dimension(250,25));
     //  link1.setMaximumSize(new Dimension(250,25));
     //  link2.setMaximumSize(new Dimension(250,25));
     //  link3.setMaximumSize(new Dimension(250,25));

        link1Label.setFont(new Font("Verdana", Font.BOLD, 40));
        link2Label.setFont(new Font("Verdana", Font.BOLD, 40));
        link3Label.setFont(new Font("Verdana", Font.BOLD, 40));

        link1.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY, Color.GRAY) );
        link2.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY, Color.GRAY) );
        link3.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY, Color.GRAY) );

        link1Label.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY, Color.GRAY) );
        link2Label.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY, Color.GRAY) );
        link3Label.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY, Color.GRAY) );




        link1.setText("https://www.vecteezy.com/vector-art/522071-two-wooden-ships-in-the-ocean");
        link2.setText("https://www.vecteezy.com/vector-art/223684-vintage-compass-background");
        link3.setText("https://www.vecteezy.com/vector-art/520446-a-ship-sailing");


        link1.setFont(new Font("Arial", Font.PLAIN, 25));
        link2.setFont(new Font("Arial", Font.PLAIN, 25));
        link3.setFont(new Font("Arial", Font.PLAIN, 25));

        link1.setLineWrap(true);
        link2.setLineWrap(true);
        link3.setLineWrap(true);

        link1.setEditable(false);
        link2.setEditable(false);
        link3.setEditable(false);

        link1.setOpaque(false);
        link2.setOpaque(false);
        link3.setOpaque(false);


        panel.add(link1Label);
        panel.add(link1);
        panel.add(link2Label);
        panel.add(link2);
        panel.add(link3Label);
        panel.add(link3);

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

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(creditsLabel);
        add(Box.createVerticalGlue());
        add(panel);
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
        link1.setText("");
        link2.setText("");
    }


}
