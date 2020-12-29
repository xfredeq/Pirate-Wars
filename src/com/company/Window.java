package com.company;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window ( )
    {
        super("Pirate Wars");
        setFrame();
        setLayout(new GridLayout(2, 4));
        ImageIcon icon1 = new ImageIcon("graphics\\vector-vintage-compass-background.jpg");
        Image icon1img = icon1.getImage();
        Image new_icon1img = icon1img.getScaledInstance(JButton.WIDTH, JButton.HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon new_icon1=new ImageIcon(new_icon1img);
        add(new JButton(new ImageIcon(icon1img.getScaledInstance(JButton.WIDTH, JButton.HEIGHT, Image.SCALE_SMOOTH))));
        add(new JButton("Pirate"));
        add(new JButton("Wars"));
        add(new JButton("Singleplayer"));
        add(new JButton("Multiplayer"));

        setVisible(true);
    }

    private void setFrame ( )
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        Dimension windowSize = getSize();
        GraphicsEnvironment screen = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = screen.getCenterPoint();
        int x = center.x - windowSize.width / 2;
        int y = center.y - windowSize.height / 2;
        setLocation(x, y);

    }
}
