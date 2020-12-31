package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panels extends JFrame implements ActionListener
{

    private Start startPanel = new Start();
    private Login loginPanel = new Login();
    private Sign signPanel = new Sign();
    private Home homePanel = new Home();
    private Pic panelPic2 = new Pic();
    private Pic picPanel = new Pic();
    JPanel cardPane=new JPanel();
    CardLayout cards;
    public Panels ( )
    {
        super("Pirate Wars");

        setLayout();
        setWindow();
        this.setVisible(true);
    }

    private void setLayout ( )
    {
        cards=new CardLayout();
        cardPane.setLayout(cards);
        cardPane.add(startPanel, "Start Pane");
        cardPane.add(loginPanel, "Login Pane");
        cardPane.add(signPanel, "Sign Pane");
        cardPane.add(homePanel, "Home Pane");


        startPanel.login.addActionListener(this);
        startPanel.signin.addActionListener(this);
        startPanel.guest.addActionListener(this);
        loginPanel.back.addActionListener(this);

        this.add(cardPane);
    }

    private void setWindow ( )
    {
        setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        Dimension windowSize = getSize();
        GraphicsEnvironment screen = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = screen.getCenterPoint();
        int x = center.x - windowSize.width / 2;
        int y = center.y - windowSize.height / 2;
        this.setLocation(x, y);
    }


    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource();

        if(source == startPanel.login)
            cards.show(cardPane,"Login Pane");
        else if(source == startPanel.signin)
            cards.show(cardPane,"Sign Pane");
        else if(source == startPanel.guest)
            cards.show(cardPane,"Home Pane");
        else if(source == loginPanel.back)
            cards.show(cardPane,"Start Pane");
    }
}
