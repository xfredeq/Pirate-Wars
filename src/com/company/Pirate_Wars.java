package com.company;

import GUI.*;

import javax.swing.*;
import java.awt.*;

public class Pirate_Wars extends JFrame
{
    Home panel;
    public Pirate_Wars()
    {
        super("Pirate Wars");
        panel=new Home();
        setWindow();
        this.setVisible(true);
    }

    private void setWindow ( )
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel.getHomePanel());
        this.setSize(1280, 720);
        Dimension windowSize = getSize();
        GraphicsEnvironment screen = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = screen.getCenterPoint();
        int x = center.x - windowSize.width / 2;
        int y = center.y - windowSize.height / 2;
        this.setLocation(x, y);
    }

    public static void main (String[] args)
    {
        EventQueue.invokeLater(()->{
            Pirate_Wars game = new Pirate_Wars();
        });
    }

}
