package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TournamentStart extends JPanel
{
    public BufferedImage image;
    public JButton newT=new JButton("New Tournament");
    public JButton loadT=new JButton("Load Tournament");
    public JButton back = new JButton("back");

    private final JPanel panel = new JPanel(new GridLayout(1,3));
    private final JLabel title=new JLabel("Pirate Wars");

    public TournamentStart ( )
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
    }

    private void setComponents ( )
    {

        panel.add(newT);
        panel.add(Box.createHorizontalGlue());
        panel.add(loadT);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(1000, 300));
        panel.setPreferredSize(new Dimension(1000, 300));
        panel.setOpaque(false);

        newT.setPreferredSize(new Dimension(600, 200));
        newT.setMaximumSize(new Dimension(600, 200));
        newT.setBackground(new Color(76, 165, 9, 255));
        newT.setFont(new Font("Arial", Font.BOLD, 25));

        loadT.setPreferredSize(new Dimension(600, 200));
        loadT.setMaximumSize(new Dimension(600, 200));
        loadT.setBackground(new Color(217, 85, 24, 255));
        loadT.setFont(new Font("Arial", Font.BOLD, 25));

        back.setPreferredSize(new Dimension(300, 80));
        back.setBackground(new Color(203, 183, 6, 255));
        back.setFont(new Font("Arial", Font.BOLD, 25));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        add(panel);
        add(Box.createVerticalGlue());
        add(back);
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
