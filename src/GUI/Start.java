package GUI;

import javax.swing.*;
import java.awt.*;

public class Start extends JPanel
{
    public Start()
    {
        add(logInButton);
        add(signInButton);
        add(playAsGuestButton);

    }
    public JButton logInButton = new JButton("dc");

    private JButton signInButton=new JButton("dc");
    private JButton playAsGuestButton=new JButton("dc");



}
