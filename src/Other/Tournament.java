package Other;

import GUI.TournamentLogin;
import GUI.TournamentHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tournament implements ActionListener
{
    private int playersCounter;
    private int guestCounter;
    public String name;
    private int playersNumber;
    private ArrayList<String> players;
    private ArrayList<Integer> points;
    private boolean[][] games;

    private TournamentLogin tLogin;
    private Users users;
    private CardLayout cards;
    private JPanel cardPane;
    private TournamentHome tHome;

    private final int fieldSize;
    private final int shipSurface;
    private final int biggestShip;

    public Tournament (Users users, String name, int playersN, int fieldSize, int shipSurface, int biggestShip)
    {
        this.players=new ArrayList<>();
        this.users=users;
        this.name=name;
        this.playersNumber=playersN;
        this.points=new ArrayList<>();
        this.games= new boolean[playersN][playersN];
        this.fieldSize=fieldSize;
        this.shipSurface=shipSurface;
        this.biggestShip=biggestShip;
        clrArrays();
        //addCurrUser();


    }


    public void tLogin (TournamentLogin tournamentLoginPane, CardLayout cards, JPanel cardPane, TournamentHome tHome)
    {
        this.playersCounter=0;
        this.guestCounter=1;
        this.tHome=tHome;
        this.tLogin = tournamentLoginPane;
        this.cards=cards;
        this.cardPane=cardPane;
        this.tLogin.guest.addActionListener(this);
        this.tLogin.login.addActionListener(this);

        //TODO pętla logowań + zapis nazw do wektora + okno THome + TLOAD
    }


    private void clrArrays ()
    {
        for(int i=0;i<this.playersNumber;i++)
        {
            for(int j=0;j<this.playersNumber;j++)
            {
                this.games[i][j]= i == j;
            }
        }
    }

    public void addCurrUser()
    {
        if(users.getCurrentUsername().equals(""))
        {
            playersCounter++;
            guestCounter++;
            players.add("Guest1");
            points.add(0);
        }
        else
        {
            playersCounter++;
            players.add(users.getCurrentUsername());
            points.add(0);
        }
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource();

        if(source== tLogin.login)
        {
            if(playersCounter<playersNumber)
            {
                String name = tLogin.loginField.getText();
                char[] password = tLogin.passField.getPassword();
                String pass = "";
                for (char c : password) pass += c;
                int i = users.isUser(name);
                if (i < 0 || players.contains(name)) {
                    tLogin.login.setBackground(Color.RED);
                } else {
                    if (!pass.equals(users.getPasses().get(i)))
                        tLogin.login.setBackground(Color.RED);
                    else {
                        this.players.add(name);
                        this.points.add(0);
                        playersCounter++;
                        this.tLogin.clearFields();
                    }
                }
            }
            else
            {
                tHome.setTournament(this);
                tHome.showTScoreboard();
                this.cards.show(cardPane, "tHome Pane");
            }
        }
        else if(source== tLogin.guest)
        {
            if(playersCounter<playersNumber)
            {
                this.players.add("Guest"+String.valueOf(guestCounter++));
                this.points.add(0);
                playersCounter++;
                this.tLogin.clearFields();
            }
            else
            {
                tHome.setTournament(this);
                tHome.showTScoreboard();
                this.cards.show(cardPane, "tHome Pane");
            }

        }

    }

    public void sort()
    {

    }

    public ArrayList<String> getPlayers ( )
    {
        return players;
    }

    public ArrayList<Integer> getPoints ( )
    {
        return points;
    }

    public void setPlayersCounter(int x) {this.playersCounter=x;}

    public void setGuestsCounter(int x) {this.guestCounter=x;}
}
