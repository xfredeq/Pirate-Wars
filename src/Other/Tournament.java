package Other;

import GUI.TournamentLoadLogin;
import GUI.TournamentLogin;
import GUI.TournamentHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tournament implements ActionListener {
    private int playersCounter;
    private int guestCounter;
    private int loadCounter;
    private int matchesNumber = 0;
    private int matchesCounter = 0;
    public String name;
    private final int playersNumber;
    private final ArrayList<String> playersOrder;
    private final ArrayList<Integer> matches;
    private final ArrayList<String> players;
    private final ArrayList<Integer> points;
    public Match lastMatch;
    public Match nextMatch;
    private final boolean[][] games;

    private TournamentLogin tLogin;
    private TournamentLoadLogin tLoadLogin;
    private final Users users;
    private CardLayout cards;
    private JPanel cardPane;
    private TournamentHome tHome;

    public int mode = -1;

    public final int shipSurface;
    public final int biggestShip;
    public final int fieldSize;

    public Tournament (Users users, String name, int playersN, int fieldSize, int shipSurface, int biggestShip)
    {
        this.lastMatch = new Match(-1, -1);
        this.playersCounter = 0;
        this.guestCounter = 1;
        this.loadCounter = 0;
        this.players = new ArrayList<>();
        this.playersOrder = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.users = users;
        this.name = name;
        this.playersNumber = playersN;
        this.points = new ArrayList<>();
        this.games = new boolean[playersN][playersN];
        this.shipSurface = shipSurface;
        this.biggestShip = biggestShip;
        this.fieldSize = fieldSize;

        for (int i = 1; i < playersN; i++) {
            this.matchesNumber += i;
        }

        clrArrays();


    }


    public void tLogin (TournamentLogin tournamentLoginPane, CardLayout cards, JPanel cardPane, TournamentHome tHome, int loginMode)
    {
        this.mode = loginMode;

        this.tHome = tHome;
        this.tLogin = tournamentLoginPane;
        this.cards = cards;
        this.cardPane = cardPane;
        if (this.mode == 0) {
            this.tLogin.guest.addActionListener(this);
            this.tLogin.login.addActionListener(this);
        }


    }

    public void tLoadLogin (TournamentLoadLogin tournamentLoginPane, CardLayout cards, JPanel cardPane, TournamentHome tHome, int loginMode)
    {
        this.mode = loginMode;
        this.loadCounter = 0;
        this.tHome = tHome;
        this.tLoadLogin = tournamentLoginPane;
        this.cards = cards;
        this.cardPane = cardPane;

        if (this.mode == 1) {
            this.tLoadLogin.login.addActionListener(this);
            while (this.loadCounter < this.playersNumber && this.playersOrder.get(this.loadCounter).contains("Guest")) {
                this.loadCounter++;
            }
            //System.out.print("load counter: " + this.loadCounter + "p number: " + this.playersNumber);
            if (this.loadCounter < this.playersNumber) {
                this.tLoadLogin.loginField.setText(this.playersOrder.get(this.loadCounter));
                this.cards.show(cardPane, "tLoadLogin Pane");
            } else {
                tHome.setTournament(this);
                tHome.showTScoreboard();
                setNextMatch();
                tHome.setMatches();
                this.cards.show(cardPane, tHome.name);
            }
        }
    }


    private void clrArrays ( )
    {
        for (int i = 0; i < this.playersNumber; i++) {
            for (int j = 0; j < this.playersNumber; j++) {
                this.games[i][j] = i == j;
            }
        }
    }

    public void addCurrUser ( )
    {
        //System.out.println("addcurruser: "+playersCounter + " "+ guestCounter);
        if (users.getCurrentUsername().equals("")) {
            playersCounter++;
            guestCounter++;
            players.add("Guest1");
            playersOrder.add("Guest1");
            points.add(0);
            matches.add(0);
        } else {
            playersCounter++;
            players.add(users.getCurrentUsername());
            playersOrder.add(users.getCurrentUsername());
            points.add(0);
            matches.add(0);
        }
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource();

        if (this.mode == 0) {
            if (source == tLogin.login) {
                if (playersCounter + 1 < playersNumber) {
                    //System.out.println("action tlogin if: "+playersCounter+" "+playersNumber);
                    String login = tLogin.loginField.getText();
                    //System.out.println(login);
                    char[] password = tLogin.passField.getPassword();
                    String pass = "";
                    for (char c : password) pass += c;

                    int i = users.isUser(login);
                    //System.out.println("I "+i);
                    if (i < 0) {
                        tLogin.login.setBackground(Color.RED);
                        //System.out.println(111);
                    } else if (players.contains(login)) {
                        tLogin.login.setBackground(Color.RED);
                        //.out.println(222);
                    } else {
                        if (!pass.equals(users.getPasses().get(i))) {
                            tLogin.login.setBackground(Color.RED);
                            //System.out.println(333);
                        } else {
                            this.players.add(login);
                            this.playersOrder.add(login);
                            this.points.add(0);
                            this.matches.add(0);
                            playersCounter++;
                            this.tLogin.clearFields();
                        }
                    }
                } else if (playersCounter < playersNumber) {
                    String login = tLogin.loginField.getText();
                    char[] password = tLogin.passField.getPassword();

                    String pass = "";
                    for (char c : password) pass += c;

                    int i = users.isUser(login);
                    if (i < 0) {
                        tLogin.login.setBackground(Color.RED);
                        //System.out.println(111);
                    } else if (players.contains(login)) {
                        tLogin.login.setBackground(Color.RED);
                        //.out.println(222);
                    } else if (!pass.equals(users.getPasses().get(i)))
                        tLogin.login.setBackground(Color.RED);
                    else {
                        this.players.add(login);
                        this.playersOrder.add(login);
                        this.points.add(0);
                        this.matches.add(0);
                        playersCounter++;

                        tHome.setTournament(this);
                        tHome.showTScoreboard();
                        this.cards.show(cardPane, tHome.name);
                        setNextMatch();
                        tHome.setMatches();

                        this.tLogin.guest.removeActionListener(this);
                        this.tLogin.login.removeActionListener(this);
                    }
                }
            } else if (source == tLogin.guest) {
                if (playersCounter + 1 < playersNumber) {
                    this.players.add("Guest" + guestCounter);
                    this.playersOrder.add("Guest" + guestCounter++);
                    this.points.add(0);
                    this.matches.add(0);
                    playersCounter++;
                    this.tLogin.clearFields();
                } else if (playersCounter < playersNumber) {
                    this.players.add("Guest" + guestCounter);
                    this.playersOrder.add("Guest" + guestCounter++);
                    this.points.add(0);
                    this.matches.add(0);
                    playersCounter++;

                    tHome.setTournament(this);
                    tHome.showTScoreboard();
                    this.cards.show(cardPane, tHome.name);
                    setNextMatch();
                    tHome.setMatches();

                    this.tLogin.guest.removeActionListener(this);
                    this.tLogin.login.removeActionListener(this);
                }

            }
        } else if (this.mode == 1) {
            if (source == tLoadLogin.login) {
                while (this.loadCounter < this.playersNumber && this.playersOrder.get(this.loadCounter).contains("Guest")) {
                    this.loadCounter++;
                    //System.out.println("11 lc: " + loadCounter);
                }

                if (loadCounter + 1 < playersNumber) {
                    //System.out.println("action tlogin if: "+playersCounter+" "+playersNumber);
                    String login = tLoadLogin.loginField.getText();
                    //System.out.println(login);
                    char[] password = tLoadLogin.passField.getPassword();
                    String pass = "";
                    for (char c : password) pass += c;

                    int i = users.isUser(login);
                    //System.out.println("I "+i);
                    if (i < 0) {
                        tLoadLogin.login.setBackground(Color.RED);
                        //System.out.println(111);
                    } else {
                        if (!pass.equals(users.getPasses().get(i))) {
                            tLoadLogin.login.setBackground(Color.RED);
                            //System.out.println(333);
                        } else {
                            loadCounter++;
                            //System.out.println("22 lc: " + loadCounter);
                            while (this.loadCounter < this.playersNumber && this.playersOrder.get(this.loadCounter).contains("Guest")) {
                                this.loadCounter++;
                                //System.out.println("33 lc: " + loadCounter);
                            }

                            if (loadCounter < playersNumber) {
                                this.tLoadLogin.clearFields();
                                this.tLoadLogin.loginField.setText(this.playersOrder.get(this.loadCounter));
                                //.out.println("if");
                            } else {
                                //System.out.println("else");
                                tHome.setTournament(this);
                                tHome.showTScoreboard();
                                setNextMatch();
                                tHome.setMatches();
                                this.cards.show(cardPane, tHome.name);
                                this.tLoadLogin.login.removeActionListener(this);
                            }
                        }
                    }
                } else if (loadCounter < playersNumber) {
                    String login = tLoadLogin.loginField.getText();
                    char[] password = tLoadLogin.passField.getPassword();
                    String pass = "";
                    for (char c : password) pass += c;
                    int i = users.isUser(login);
                    if (i < 0) {
                        tLoadLogin.login.setBackground(Color.RED);
                    } else if (!pass.equals(users.getPasses().get(i)))
                        tLoadLogin.login.setBackground(Color.RED);
                    else {
                        tHome.setTournament(this);
                        tHome.showTScoreboard();
                        setNextMatch();
                        tHome.setMatches();
                        this.cards.show(cardPane, tHome.name);
                        this.tLoadLogin.login.removeActionListener(this);
                    }


                }
            }
        }

    }


    public ArrayList<String> getPlayers ( )
    {
        return players;
    }

    public int getPlayersN ( )
    {
        return playersNumber;
    }

    public int getFieldSize ( )
    {
        return fieldSize;
    }

    public int getShipSurface ( )
    {
        return shipSurface;
    }

    public int getBiggestShip ( )
    {
        return biggestShip;
    }

    public int getMatchesCounter ( )
    {
        return matchesCounter;
    }

    public void setMatchesCounter (int i ) { this.matchesCounter=i;}

    public boolean[][] getGames ( )
    {
        return games;
    }

    public ArrayList<String> getPlayersOrder ( )
    {
        return playersOrder;
    }

    public ArrayList<Integer> getPoints ( )
    {
        return points;
    }

    public String getPlayer1 ( )
    {
        return playersOrder.get(nextMatch.p1);
    }

    public String getPlayer2 ( )
    {
        return playersOrder.get(nextMatch.p2);
    }

    public ArrayList<Integer> getMatches ( )
    {
        return matches;
    }

    public void setPlayersCounter (int x)
    {
        this.playersCounter = x;
    }

    public void setGuestsCounter (int x)
    {
        this.guestCounter = x;
    }

    private void setNextMatch ( )
    {
        if (matchesCounter < matchesNumber) {
            int p2;
            int p1 = lastMatch.p1;
            if (p1 == -1) {
                p1 = 0;
            } else
                p1 = (lastMatch.p1 + 2) % playersNumber;


            for (int i = 0; i < playersNumber; i++) {
                if (matches.get(i) < matches.get(p1)) {
                    p1 = i;
                    //System.out.println("shift p1");
                }
            }

            p2 = (p1 + 1) % playersNumber;
            //System.out.println(p1 + " " + p2);

            while (p2 == p1 || games[p1][p2]) {
                p2 = (p2 + 1) % playersNumber;
                //System.out.println("shift");
            }


            for (int i = 0; i < playersNumber; i++) {
                if (matches.get(i) < matches.get(p2) && i != p1 && !games[p2][i])
                    p2 = i;
            }

            nextMatch = new Match(p1, p2);
        } else
            nextMatch = new Match(-1, -1);
    }

    public void start ( )
    {
        int i = 0;
        while (i < 40) {
            setNextMatch();
            if (nextMatch.p1 != -1) {
                //System.out.println("next match: " +i + " "+ nextMatch.p1 + " " + nextMatch.p2);
                matches.set(nextMatch.p1, matches.get(nextMatch.p1) + 1);
                matches.set(nextMatch.p2, matches.get(nextMatch.p2) + 1);
                games[nextMatch.p1][nextMatch.p2] = true;
                games[nextMatch.p2][nextMatch.p1] = true;
                lastMatch.p1 = nextMatch.p1;
                lastMatch.p2 = nextMatch.p2;
                matchesCounter++;
                i++;
            } else {
                //System.out.println("koniec");
                break;
            }
        }
    }

    public void setResults (int winner)
    {
        matches.set(nextMatch.p1, matches.get(nextMatch.p1) + 1);
        matches.set(nextMatch.p2, matches.get(nextMatch.p2) + 1);
        games[nextMatch.p1][nextMatch.p2] = true;
        games[nextMatch.p2][nextMatch.p1] = true;
        lastMatch.p1 = nextMatch.p1;
        lastMatch.p2 = nextMatch.p2;
        matchesCounter++;

        switch (winner) {
            case 0 -> points.set(nextMatch.p1, points.get(nextMatch.p1) + 3);
            case 1 -> points.set(nextMatch.p2, points.get(nextMatch.p2) + 3);
        }
        setNextMatch();

        tHome.clearScoreboard();
        tHome.showTScoreboard();
        tHome.setMatches();

    }


}
