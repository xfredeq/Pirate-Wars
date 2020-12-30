package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panels extends JFrame implements ActionListener
{
    public Pic panelPic = new Pic();
    public Start panelStart = new Start();
    public Pic panelPic2 = new Pic();
    JPanel cardPane=new JPanel();
    CardLayout cards;
    public Panels ( )
    {
        super("Pirate Wars");
        cards=new CardLayout();
        cardPane.setLayout(cards);
        cardPane.add(panelPic, "First Pane");
        cardPane.add(panelStart, "Second Pane");
        cardPane.add(panelPic2, "Third Pane");
        panelPic.b1.addActionListener(this);
        panelPic2.b1.addActionListener(this);
        panelPic2.b1.setBackground(Color.GREEN);
        panelStart.logInButton.addActionListener(this);
        setWindow();
        this.add(cardPane);
        this.setVisible(true);
    }

    private void setWindow ( )
    {
        //panel.setLayout(new CardLayout());
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

        if(source == panelPic.b1)
            cards.next(cardPane);
        else if(source == panelStart.logInButton)
            cards.previous(cardPane);
        else if(source == panelPic2.b1)
            cards.next(cardPane);
    }
}
