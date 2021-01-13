package Other;

import GUI.TLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tournament implements ActionListener
{
    private int playersCounter=0;
    private int guestCounter=1;
    public String name;
    private int playersNumber;
    private ArrayList<String> players;
    private boolean[][] games;

    private TLogin tLogin;
    private Users users;

    private final int fieldSize;
    private final int shipSurface;
    private final int biggestShip;

    public Tournament (Users users, String name, int playersN, int fieldSize, int shipSurface, int biggestShip)
    {
        this.players=new ArrayList<>();
        this.users=users;
        this.name=name;
        this.playersNumber=playersN;
        this.games= new boolean[playersN][playersN];
        this.fieldSize=fieldSize;
        this.shipSurface=shipSurface;
        this.biggestShip=biggestShip;
        clrGames();
        addCurrUser();


    }


    public void tLogin (TLogin tLoginPane )
    {
        this.tLogin=tLoginPane;
        this.tLogin.guest.addActionListener(this);

            this.tLogin.login.addActionListener(this);

        //TODO pętla logowań + zapis nazw do wektora + okno THome + TLOAD
    }


    private void clrGames()
    {
        for(int i=0;i<this.playersNumber;i++)
        {
            for(int j=0;j<this.playersNumber;j++)
            {
                this.games[i][j]= i == j;
            }
        }
    }

    private void addCurrUser()
    {
        if(this.users.getCurrentUsername().equals(""))
        {
            playersCounter++;
            guestCounter++;
            players.add("Guest1");
        }
        else
        {
            playersCounter++;
            players.add(users.getCurrentUsername());
        }
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource();

        if(source==tLogin.login)
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
                        playersCounter++;
                        this.tLogin.clearFields();
                    }
                }
            }
            else
            {
                tLogin.login.setBackground(Color.GREEN);
            }
        }
        else if(source==tLogin.guest)
        {
            if(playersCounter<playersNumber)
            {
                this.players.add("Guest"+String.valueOf(guestCounter++));
                playersCounter++;
                this.tLogin.clearFields();
            }
            else
                tLogin.login.setBackground(Color.GREEN);
        }

    }
}
