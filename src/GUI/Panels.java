package GUI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panels extends JFrame implements ActionListener {

    private Users users = new Users();
    private Start startPane = new Start();
    private Login loginPane = new Login();
    private Sign signPane = new Sign();
    private Home homePane = new Home();
    private Settings settingsPane = new Settings();
    private Play playPane = new Play();
    private Ships shipsPane;
    private Game gamePane;
    JPanel cardPane = new JPanel();
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
        cards = new CardLayout();
        cardPane.setLayout(cards);
        cardPane.add(startPane, "Start Pane");
        cardPane.add(loginPane, "Login Pane");
        cardPane.add(signPane, "Sign Pane");
        cardPane.add(homePane, "Home Pane");
        cardPane.add(settingsPane,"Settings Pane");
        cardPane.add(playPane, "Play Pane");



        startPane.login.addActionListener(this);
        startPane.signin.addActionListener(this);
        startPane.guest.addActionListener(this);
        startPane.exit.addActionListener(this);

        loginPane.login.addActionListener(this);
        loginPane.back.addActionListener(this);

        signPane.sign.addActionListener(this);
        signPane.back.addActionListener(this);

        homePane.play.addActionListener(this);
        homePane.settings.addActionListener(this);
        homePane.logout.addActionListener(this);
        homePane.exit.addActionListener(this);

        settingsPane.back.addActionListener(this);

        playPane.back.addActionListener(this);
        playPane.easy.addActionListener(this);



        this.add(cardPane);
    }

    private void setWindow ( )
    {
        setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1050);
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

        if (source == startPane.login)
            cards.show(cardPane, "Login Pane");
        else if (source == startPane.signin)
            cards.show(cardPane, "Sign Pane");
        else if (source == startPane.guest) {
            cards.show(cardPane, "Home Pane");
            homePane.setUsername(users.getCurrentUsername());
        }
        else if (source == startPane.exit)
            System.exit(0);
        else if (source == loginPane.back)
            cards.show(cardPane, "Start Pane");
        else if (source == loginPane.login) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run ( )
                {
                    if (users.login(loginPane.loginField.getText(), loginPane.passField.getPassword())) {
                        homePane.setUsername(users.getCurrentUsername());
                        loginPane.clearFields();
                        cards.show(cardPane, "Home Pane");
                    } else
                        loginPane.login.setBackground(Color.RED);
                }
            });
        }
        else if (source == signPane.back)
            cards.show(cardPane, "Start Pane");
        else if (source == signPane.sign)
        {
            EventQueue.invokeLater(new Runnable()
            {
                @Override
                public void run ( )
                {
                    if (users.register(signPane.loginField.getText(), signPane.passField.getPassword(), signPane.confirmPassField.getPassword()))
                    {
                        homePane.setUsername(users.getCurrentUsername());
                        signPane.clearFields();
                        cards.show(cardPane, "Home Pane");
                    }
                    else
                        signPane.sign.setBackground(Color.RED);
                }
            });
        }
        else if(source == homePane.settings)
            cards.show(cardPane, "Settings Pane");
        else if (source == homePane.logout)
        {
            users.setCurrentUsername("");
            cards.show(cardPane, "Start Pane");
        }
        else if (source == homePane.exit)
            System.exit(0);
        else if (source == settingsPane.back)
        {
            settingsPane.setSettings();
            cards.show(cardPane, "Home Pane");
        }
        else if(source == homePane.play)
            cards.show(cardPane, "Play Pane");
        else if(source == playPane.back)
            cards.show(cardPane, "Home Pane");
        else if(source == playPane.easy)
        {
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Ships Pane");
        }
        else if(source == shipsPane.start)
        {
            if(true)//shipsPane.getConflicts()==0 && shipsPane.getShipSurface()==0)
            {
                gamePane = new Game(settingsPane.getFieldSize(), shipsPane.getBoard(), shipsPane.makeRandomBoard());

                cardPane.add(gamePane, "Game Pane");

                cards.show(cardPane, "Game Pane");
                //make array

            }
            else
                shipsPane.start.setBackground(Color.RED);
        }
        else if(source == shipsPane.back)
        {
            shipsPane.reset();
            cards.show(cardPane, "Play Pane");
        }
    }
}
