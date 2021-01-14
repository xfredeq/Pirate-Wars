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
    private final int playersNumber;
    private ArrayList<String> playersOrder;
    private final ArrayList<String> players;
    private final ArrayList<Integer> points;
    private final boolean[][] games;

    private TournamentLogin tLogin;
    private final Users users;
    private CardLayout cards;
    private JPanel cardPane;
    private TournamentHome tHome;

    private final int shipSurface;
    private final int biggestShip;

    public Tournament (Users users, String name, int playersN, int fieldSize, int shipSurface, int biggestShip)
    {
        this.playersCounter=0;
        this.guestCounter=1;
        this.players=new ArrayList<>();
        this.playersOrder=new ArrayList<>();
        this.users=users;
        this.name=name;
        this.playersNumber=playersN;
        this.points=new ArrayList<>();
        this.games= new boolean[playersN][playersN];
        this.shipSurface=shipSurface;
        this.biggestShip=biggestShip;
        clrArrays();
        //addCurrUser();


    }


    public void tLogin (TournamentLogin tournamentLoginPane, CardLayout cards, JPanel cardPane, TournamentHome tHome)
    {
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
        //System.out.println("addcurruser: "+playersCounter + " "+ guestCounter);
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
            if(playersCounter+1<playersNumber)
            {
                //System.out.println("action tlogin if: "+playersCounter+" "+playersNumber);
                String login = tLogin.loginField.getText();
                //System.out.println(login);
                char[] password = tLogin.passField.getPassword();
                String pass = "";
                for (char c : password) pass += c;

                int i = users.isUser(login);
                //System.out.println("I "+i);
                if (i < 0  )
                {
                    tLogin.login.setBackground(Color.RED);
                    //System.out.println(111);
                }
                else if(players.contains(login))
                {
                    tLogin.login.setBackground(Color.RED);
                    //.out.println(222);
                }
                else {
                    if (!pass.equals(users.getPasses().get(i)))
                    {
                        tLogin.login.setBackground(Color.RED);
                        //System.out.println(333);
                    }
                    else
                        {
                        this.players.add(login);
                        this.playersOrder.add(login);
                        this.points.add(0);
                        playersCounter++;
                        this.tLogin.clearFields();
                    }
                }
            }
            else if (playersCounter<playersNumber)
            {
                String login = tLogin.loginField.getText();
                char[] password = tLogin.passField.getPassword();
                String pass = "";
                for (char c : password) pass += c;
                int i = users.isUser(login);
                if (i < 0 || players.contains(login))
                {
                    tLogin.login.setBackground(Color.RED);
                }
                else
                {
                    if (!pass.equals(users.getPasses().get(i)))
                        tLogin.login.setBackground(Color.RED);
                    else
                    {
                        this.players.add(login);
                        this.playersOrder.add(login);
                        this.points.add(0);
                        playersCounter++;
                    }
                }

                tHome.setTournament(this);
                tHome.showTScoreboard();
                this.cards.show(cardPane, "tHome Pane");

                this.tLogin.guest.removeActionListener(this);
                this.tLogin.login.removeActionListener(this);
            }
        }
        else if(source== tLogin.guest)
        {
            if(playersCounter+1<playersNumber)
            {
                this.players.add("Guest"+String.valueOf(guestCounter++));
                this.playersOrder.add("Guest"+String.valueOf(guestCounter++));
                this.points.add(0);
                playersCounter++;
                this.tLogin.clearFields();
            }
            else if (playersCounter<playersNumber)
            {
                this.players.add("Guest"+String.valueOf(guestCounter++));
                this.playersOrder.add("Guest"+String.valueOf(guestCounter++));
                this.points.add(0);
                playersCounter++;

                tHome.setTournament(this);
                tHome.showTScoreboard();
                this.cards.show(cardPane, "tHome Pane");

                this.tLogin.guest.removeActionListener(this);
                this.tLogin.login.removeActionListener(this);
            }

        }

    }

    public void sort()
    {
        Integer tmp1;
        String tmp2;

        for(int i=0;i<players.size();i++)
        {
            for(int j=i+1;j<players.size();j++)
            {
                if(points.get(j)>points.get(i))
                {
                    tmp1=points.get(i);
                    points.set(i,points.get(j));
                    points.set(j,tmp1);

                    tmp2=players.get(i);
                    players.set(i,players.get(j));
                    players.set(j,tmp2);
                }
            }
        }
    }

    public ArrayList<String> getPlayers ( ) { return players; }

    public ArrayList<Integer> getPoints ( ) {return points; }

    public void setPlayersCounter(int x) {this.playersCounter=x;}

    public void setGuestsCounter(int x) {this.guestCounter=x;}
}
