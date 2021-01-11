package Other;

import GUI.Login;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tournament
{
    public String name;
    private int playersNumber;
    private ArrayList<String> players;
    private boolean[][] games;

    private int fieldSize;
    private int shipSurface;
    private int biggestShip;

    public Tournament (String name, int players, int fieldSize, int shipSurface, int biggestShip)
    {
        this.name=name;
        this.playersNumber=players;
        this.games= new boolean[players][players];
        this.fieldSize=fieldSize;
        this.shipSurface=shipSurface;
        this.biggestShip=biggestShip;
    }


    public void tLogin (JPanel cardPane,CardLayout cards )
    {
        Login login = new Login();
        cardPane.add(login, "TLogin");
        cards.show(cardPane, "TLogin");

        //TODO pętla logowań + zapis nazw do wektora + okno THome + TLOAD
    }


}
