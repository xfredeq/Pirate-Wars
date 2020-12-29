package com.company;

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame {
    public Window ( )
    {
        super("Pirate Wars");

        setFrame();
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
        setVisible(true);
    }
}
