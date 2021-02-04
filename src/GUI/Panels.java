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
    private int gameMode = -1;
    private int loginMode = 0;

    private final Start startPane = new Start();
    private final Login loginPane = new Login();
    private final Sign signPane = new Sign();
    private final Home homePane = new Home();
    private final Settings settingsPane = new Settings();
    private final TournamentSettings tSettingsPane = new TournamentSettings(data);
    private final Scoreboard scorePane = new Scoreboard(users);
    private final Credits creditsPane = new Credits();
    private final Play playPane = new Play();
    private final TournamentStart tStartPane = new TournamentStart();
    private final TournamentLoad tLoadPane = new TournamentLoad();
    private final TournamentLogin tLoginPane = new TournamentLogin();
    private final TournamentLoadLogin tLoadLoginPane = new TournamentLoadLogin();
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
        cardPane.add(creditsPane, "Credits Pane");
        cardPane.add(tSettingsPane, "tSettings Pane");
        cardPane.add(tStartPane, "tStart Pane");
        cardPane.add(tLoadPane, "tLoad Pane");
        cardPane.add(tLoginPane, "tLogin Pane");
        cardPane.add(tLoadLoginPane, "tLoadLogin Pane");


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
        homePane.credits.addActionListener(this);
        homePane.logout.addActionListener(this);
        homePane.exit.addActionListener(this);

        settingsPane.back.addActionListener(this);

        scorePane.back.addActionListener(this);

        creditsPane.back.addActionListener(this);

        playPane.back.addActionListener(this);
        playPane.easy.addActionListener(this);
        playPane.medium.addActionListener(this);
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

        tLoadLoginPane.cancel.addActionListener(this);




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
                if(data.tHomePages.get(i).winner.equals(""))
                {
                    cards.show(cardPane, "Play Pane");
                    data.tHomePages.get(i).clearScoreboard();
                    currTournamentPointer=-1;
                }
                else
                {
                    cards.show(cardPane, "Play Pane");
                    currTournamentPointer=-1;

                    for(int j=0;j<data.getTournament(i).getPlayers().size();j++)
                    {
                        if(!data.getTournament(i).getPlayers().get(j).contains("Guest"))
                        {
                            users.addScore(data.getTournament(i).getPlayers().get(j),(2 * data.getTournament(i).getPoints().get(j)) - (3 * (data.getTournament(i).getPlayers().size()-1) ) );
                        }
                    }
                    data.deleteTournament(i);
                }

            }
            else if(source==data.tHomePages.get(i).start)
            {
                gameMode =2;

                shipsPane = new Ships(data.getTournaments().get(i).fieldSize,data.getTournaments().get(i).biggestShip,data.getTournaments().get(i).shipSurface);
                cardPane.add(shipsPane, "Ships Pane");
                shipsPane.back.addActionListener(this);
                shipsPane.start.addActionListener(this);

                cards.show(cardPane, "Ships Pane");


            }
        }

        for(int i=0;i<tLoadPane.getButtons().length;i++)
        {
            if(source == tLoadPane.getButtons()[i])
            {
                currTournamentPointer=i;
                tLoadLoginPane.clearFields();
                tLoadPane.getButtons()[i].setBackground(Color.RED);

                data.getTournament(i).tLoadLogin(tLoadLoginPane, cards, cardPane, data.getTHomePage(i), 1);


            }
        }


        if (source == startPane.login)
            cards.show(cardPane, "Login Pane");
        else if (source == startPane.signin)
            cards.show(cardPane, "Sign Pane");
        else if (source == startPane.guest)
        {
            cards.show(cardPane, "Home Pane");
            homePane.setUsername(users.getCurrentUsername());
        }
        else if (source == startPane.exit)
        {
            data.save();
            System.exit(0);
        }
        else if (source == loginPane.back)
        {
            if(loginMode==0)
                cards.show(cardPane, "Start Pane");
            else
            {
                cards.show(cardPane, "Play Pane");
                loginMode=0;
            }

        }
        else if (source == loginPane.login)
        {
            invokeLater(new Runnable() {
                @Override
                public void run ( )
                {
                    if (users.login(loginPane.loginField.getText(), loginPane.passField.getPassword())) {
                        homePane.setUsername(users.getCurrentUsername());
                        loginPane.clearFields();
                        if(gameMode ==1)
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
        else if(source == homePane.credits)
        {
            cards.show(cardPane, "Credits Pane");
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
        else if(source == creditsPane.back)
        {
            cards.show(cardPane, "Home Pane");
        }
        else if(source == playPane.back)
            cards.show(cardPane, "Home Pane");
        else if(source == playPane.easy)
        {
            settingsPane.setGameMode("easy");
            gameMode =0;
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Ships Pane");
        }
        else if(source == playPane.medium)
        {
            settingsPane.setGameMode("medium");
            gameMode =0;
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
            gameMode =1;
            cardPane.add(shipsPane, "Ships Pane");
            shipsPane.back.addActionListener(this);
            shipsPane.start.addActionListener(this);

            cards.show(cardPane, "Ships Pane");
        }
        else if(source == playPane.player)
        {
            loginMode=1;
            settingsPane.setGameMode("player");
            shipsPane = new Ships(settingsPane.getFieldSize(),settingsPane.getBiggestShip(),settingsPane.getShipSurface());
            gameMode =1;
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
        {
            tLoadPane.clearTournaments();
            tLoadPane.setTournaments(data.getTournaments());
            tLoadPane.showTournaments();
            this.addTLoadAL();
            cards.show(cardPane, "tLoad Pane");
        }
        else if(source== tLoadPane.back)
        {
            cards.show(cardPane, "tStart Pane");
        }
        else if(source== tSettingsPane.back)
            cards.show(cardPane, "tStart Pane");
        else if(source== tSettingsPane.create)
        {
            tLoginPane.clearFields();
            data.tHomePages.add(new TournamentHome("tHome Pane "+ data.tHomePages.size()));
            data.getLastTHomePage().back.addActionListener(this);
            currTournamentPointer=data.tHomePages.size()-1;
            cardPane.add(data.getLastTHomePage(), "tHome Pane "+ (data.tHomePages.size() - 1));
            data.getLastTHomePage().back.addActionListener(this);
            data.getLastTHomePage().start.addActionListener(this);

            data.addTournament(new Tournament(users,tSettingsPane.getName(), tSettingsPane.getPlayers(), tSettingsPane.getFieldSize(), tSettingsPane.getShipSurface(), tSettingsPane.getBiggestShip()));
            data.getLastTournament().addCurrUser();
            data.getLastTournament().tLogin(tLoginPane, cards, cardPane, data.getLastTHomePage(), 0);
            cards.show(cardPane, "tLogin Pane");

        }
        else if(source== tLoginPane.cancel)
        {
            data.deleteLastTournament();
            cards.show(cardPane, "tStart Pane");
        }
        else if(source== tLoadLoginPane.cancel)
        {
            cards.show(cardPane, "tStart Pane");
        }
        else if(source == shipsPane.start)
        {
            if(true)//shipsPane.getConflicts()==0 && shipsPane.getShipSurface()==0)
            {
                if (gameMode ==1)
                {
                    shipsPane2 = new Ships(settingsPane.getFieldSize(), settingsPane.getBiggestShip(), settingsPane.getShipSurface());
                    cardPane.add(shipsPane2, "Ships Pane 2");
                    cards.show(cardPane, "Ships Pane 2");
                    shipsPane2.back.addActionListener(this);
                    shipsPane2.start.addActionListener(this);
                }
                else if(gameMode ==0)
                {
                    getBoard1();
                    getBoard2();
                    gamePane = new Game(users, settingsPane.getGameMode(), settingsPane.getFieldSize(), board1, board2, settingsPane.getShipSurface());

                    gamePane.back.addActionListener(this);

                    cardPane.add(gamePane, "Game Pane");
                    cards.show(cardPane, "Game Pane");
                    gamePane.clk.start();
                }
                else if(gameMode ==2)
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
            if(gameMode ==2)
            {
                cards.show(cardPane, "tHome Pane "+ currTournamentPointer);
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
                if(gameMode ==2)
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

            data.getTournaments().get(currTournamentPointer).setResults(tGamePane.results());
            cards.show(cardPane, "tHome Pane "+ currTournamentPointer);
        }



    }

    private void getBoard1()
    {
        board1 = shipsPane.getBoard();
    }
    private void getBoard2()
    {
        if(gameMode ==0)
            board2= shipsPane.makeRandomBoard();
        else
            board2= shipsPane2.getBoard();
    }


    private void addTLoadAL()
    {
        for(int i=0;i<data.getTournaments().size();i++)
        {
            tLoadPane.getButtons()[i].addActionListener(this);
        }
    }
}
