package GUI;

import Other.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.EventQueue.invokeLater;

public class Panels extends JFrame implements ActionListener {

    private final Users users = new Users();
    private final Data data = new Data(users);

    private boolean[][] board1=new boolean[1][1], board2=new boolean[1][1];
    private int mode=-1;

    private final Start startPane = new Start();
    private final Login loginPane = new Login();
    private final Sign signPane = new Sign();
    private final Home homePane = new Home();
    private final Settings settingsPane = new Settings();
    private final TournamentSettings tSettingsPane = new TournamentSettings(data);
    private final Scoreboard scorePane = new Scoreboard(users);
    private final Play playPane = new Play();
    private final TournamentStart tStartPane = new TournamentStart();
    private final TournamentLoad tLoadPane = new TournamentLoad();
    private TournamentLogin tLoginPane = new TournamentLogin();
    //private  TournamentHome tHomePane=new TournamentHome();
    private Ships shipsPane= new Ships(1,1,1), shipsPane2 = new Ships(1,1,1);
    private Game gamePane=new Game(users," ",1,board1,board2,1);
    private TournamentGame tGamePane=new TournamentGame("","",1,board1,board2,1);



    private int currTournamentPointer;


    JPanel cardPane = new JPanel();
    CardLayout cards;

    public Panels ( )
    {
        super("Pirate Wars");
        invokeLater(data::read);

        /*ArrayList<String> users = this.users.getUsers();
        ArrayList<String> passes = this.users.getPasses();
        ArrayList<Integer> scores = this.users.getScores();

        for(int i=0;i<users.size();i++)
        {
            System.out.println(users.get(i)+" "+passes.get(i)+" "+scores.get(i));
        }*/

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
        cardPane.add(scorePane, "Scoreboard Pane");
        cardPane.add(tSettingsPane, "tSettings Pane");
        cardPane.add(tStartPane, "tStart Pane");
        cardPane.add(tLoadPane, "tLoad Pane");
        cardPane.add(tLoginPane, "tLogin Pane");


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
        homePane.score.addActionListener(this);
        homePane.logout.addActionListener(this);
        homePane.exit.addActionListener(this);

        settingsPane.back.addActionListener(this);

        scorePane.back.addActionListener(this);

        playPane.back.addActionListener(this);
        playPane.easy.addActionListener(this);
        playPane.medium.addActionListener(this);;
        //playPane.hard.addActionListener(this);
        playPane.guest.addActionListener(this);
        playPane.player.addActionListener(this);
        playPane.tournament.addActionListener(this);

        tStartPane.newT.addActionListener(this);
        tStartPane.loadT.addActionListener(this);
        tStartPane.back.addActionListener(this);

        tLoadPane.back.addActionListener(this);

        tSettingsPane.back.addActionListener(this);
        tSettingsPane.create.addActionListener(this);

        tLoginPane.cancel.addActionListener(this);

        //tHomePane.back.addActionListener(this);


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

        for(int i=0;i<data.tHomePages.size();i++)
        {
            if(source==data.tHomePages.get(i).back)
            {
                cards.show(cardPane, "Play Pane");
                data.tHomePages.get(i).clearScoreboard();
                currTournamentPointer=-1;
            }
            else if(source==data.tHomePages.get(currTournamentPointer).start)
            {
                mode=2;

                shipsPane = new Ships(data.getTournaments().get(i).fieldSize,data.getTournaments().get(i).biggestShip,data.getTournaments().get(i).shipSurface);
                cardPane.add(shipsPane, "Ships Pane");
                shipsPane.back.addActionListener(this);
                shipsPane.start.addActionListener(this);

                cards.show(cardPane, "Ships Pane");


            }
        }

        if (source == startPane.login)
            cards.show(cardPane, "Login Pane");
        else if (source == startPane.signin)
            cards.show(cardPane, "Sign Pane");
        else if (source == startPane.guest) {
            cards.show(cardPane, "Home Pane");
            homePane.setUsername(users.getCurrentUsername());
        }
        else if (source == startPane.exit)
        {
            data.save();
            System.exit(0);
        }
        else if (source == loginPane.back)
            cards.show(cardPane, "Start Pane");
        else if (source == loginPane.login)
        {
            invokeLater(new Runnable() {
                @Override
                public void run ( )
                {
                    if (users.login(loginPane.loginField.getText(), loginPane.passField.getPassword())) {
                        homePane.setUsername(users.getCurrentUsername());
                        loginPane.clearFields();
                        if(mode==1)
                            cards.show(cardPane, "Ships Pane");
                        else
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
            invokeLater(new Runnable()
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
        else if(source == homePane.score)
        {
            scorePane.showScoreboard();
            cards.show(cardPane, "Scoreboard Pane");
        }
        else if (source == homePane.logout)
        {
            users.setCurrentUsername("");
            cards.show(cardPane, "Start Pane");
        }
        else if (source == homePane.exit)
        {
            data.save();
            System.exit(0);
        }
        else if(source == homePane.play)
            cards.show(cardPane, "Play Pane");
        else if (source == settingsPane.back)
        {
            settingsPane.setSettings();
            cards.show(cardPane, "Home Pane");
        }
        else if(source == scorePane.back)
        {
            scorePane.clearScoreboard();
            cards.show(cardPane, "Home Pane");
        }
        else if(source == playPane.back)
            cards.show(cardPane, "Home Pane");
        else if(source == playPane.easy)
        {
            settingsPane.setGameMode("easy");
            mode=0;
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Ships Pane");
        }
        else if(source == playPane.medium)
        {
            settingsPane.setGameMode("medium");
            mode=0;
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Ships Pane");
        }
        else if(source == playPane.guest)
        {
            settingsPane.setGameMode("guest");
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            mode=1;
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Ships Pane");
        }
        else if(source == playPane.player)
        {
            settingsPane.setGameMode("player");
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            mode=1;
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Login Pane");
        }
        else if(source== playPane.tournament)
            cards.show(cardPane, "tStart Pane");
        else if(source== tStartPane.back)
            cards.show(cardPane, "Play Pane");
        else if(source== tStartPane.newT)
        {
            tSettingsPane.setDefault();
            cards.show(cardPane, "tSettings Pane");
        }
        else if(source== tStartPane.loadT)
            cards.show(cardPane, "tLoad Pane");
        else if(source== tLoadPane.back)
            cards.show(cardPane, "tStart Pane");
        else if(source== tSettingsPane.back)
            cards.show(cardPane, "tStart Pane");
        else if(source== tSettingsPane.create)
        {
            tLoginPane.clearFields();
            data.tHomePages.add(new TournamentHome("tHome Pane "+String.valueOf(data.tHomePages.size())));
            data.getLastTHomePage().back.addActionListener(this);
            currTournamentPointer=data.tHomePages.size()-1;
            cardPane.add(data.getLastTHomePage(), "tHome Pane "+String.valueOf(data.tHomePages.size()-1));
            data.getLastTHomePage().back.addActionListener(this);
            data.getLastTHomePage().start.addActionListener(this);

            data.addTournament(new Tournament(users,tSettingsPane.getName(), tSettingsPane.getPlayers(), tSettingsPane.getFieldSize(), tSettingsPane.getShipSurface(), tSettingsPane.getBiggestShip()));
            data.getLastTournament().addCurrUser();
            data.getLastTournament().tLogin(tLoginPane, cards, cardPane, data.getLastTHomePage());
            cards.show(cardPane, "tLogin Pane");

        }
        else if(source== tLoginPane.cancel)
        {
            data.deleteLastTournament();
            cards.show(cardPane, "tStart Pane");
        }
        /*else if(source== tHomePane.back)
        {
            cards.show(cardPane, "Play Pane");
            tHomePane.clearScoreboard();
        }*/
        else if(source == shipsPane.start)
        {
            if(true)//shipsPane.getConflicts()==0 && shipsPane.getShipSurface()==0)
            {
                if (mode==1)
                {
                    shipsPane2 = new Ships(settingsPane.getFieldSize(), settingsPane.getBiggestShip(), settingsPane.getShipSurface());
                    cardPane.add(shipsPane2, "Ships Pane 2");
                    cards.show(cardPane, "Ships Pane 2");
                    shipsPane2.back.addActionListener(this);
                    shipsPane2.start.addActionListener(this);
                }
                else if(mode==0)
                {
                    getBoard1();
                    getBoard2();
                    gamePane = new Game(users, settingsPane.getGameMode(), settingsPane.getFieldSize(), board1, board2, settingsPane.getShipSurface());

                    gamePane.back.addActionListener(this);

                    cardPane.add(gamePane, "Game Pane");
                    cards.show(cardPane, "Game Pane");
                    gamePane.clk.start();
                }
                else if(mode==2)
                {
                    shipsPane2 = new Ships(data.getTournaments().get(currTournamentPointer).fieldSize, data.getTournaments().get(currTournamentPointer).biggestShip, data.getTournaments().get(currTournamentPointer).shipSurface);
                    cardPane.add(shipsPane2, "Ships Pane 2");
                    cards.show(cardPane, "Ships Pane 2");
                    shipsPane2.back.addActionListener(this);
                    shipsPane2.start.addActionListener(this);
                }
            }
            else
                shipsPane.start.setBackground(Color.RED);

        }
        else if(source == shipsPane.back)
        {
            if(mode==2)
            {
                cards.show(cardPane, "tHome Pane "+String.valueOf(currTournamentPointer));
            }
            else
            {
                cards.show(cardPane, "Play Pane");
            }
        }
        else if(source == shipsPane2.back)
        {
            shipsPane.reset();
            cards.show(cardPane, "Ships Pane");
        }
        else if(source == shipsPane2.start)
        {
            if(true)//shipsPane2.getConflicts()==0 && shipsPane2.getShipSurface()==0)
            {
                if(mode==2)
                {
                    getBoard1();
                    getBoard2();
                    tGamePane = new TournamentGame(data.getTournaments().get(currTournamentPointer).getPlayer1(), data.getTournaments().get(currTournamentPointer).getPlayer2(), data.getTournaments().get(currTournamentPointer).fieldSize, board1, board2, data.getTournaments().get(currTournamentPointer).shipSurface);
                    tGamePane.back.addActionListener(this);

                    cardPane.add(tGamePane, "tGame Pane");
                    cards.show(cardPane, "tGame Pane");
                    tGamePane.clk.start();
                }
                else
                {
                    getBoard1();
                    getBoard2();
                    gamePane = new Game(users, settingsPane.getGameMode(), settingsPane.getFieldSize(), board1, board2, settingsPane.getShipSurface());

                    gamePane.back.addActionListener(this);

                    cardPane.add(gamePane, "Game Pane");
                    cards.show(cardPane, "Game Pane");
                    gamePane.clk.start();
                }
            }
        }
        else if(source == gamePane.back)
        {
            cards.show(cardPane, "Home Pane");
        }
        else if(source == tGamePane.back)
        {

            data.getTournaments().get(currTournamentPointer).setResults(0);
            cards.show(cardPane, "tHome Pane "+String.valueOf(currTournamentPointer));
        }



    }

    private void getBoard1()
    {
        board1 = shipsPane.getBoard();
    }
    private void getBoard2()
    {
        if(mode==0)
            board2= shipsPane.makeRandomBoard();
        else
            board2= shipsPane2.getBoard();
    }
}
