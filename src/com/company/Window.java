package com.company;

import javax.swing.JFrame;

public class Window extends JFrame
{
    public Window()
    {
        super("Pirate Wars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(640,480);
    }
}
