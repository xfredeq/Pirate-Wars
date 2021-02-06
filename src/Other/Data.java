package Other;

import GUI.TournamentHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Data implements ActionListener {
    private final File file;
    private Scanner read;
    private PrintWriter write;
    private final Users users;
    private final ArrayList<Tournament> tournaments;
    public final ArrayList<TournamentHome> tHomePages;

    public Data(Users users)
    {
        this.users=users;
        this.tournaments = new ArrayList<>();
        this.tHomePages=new ArrayList<>();
        file = new File("dane.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void save ( )
    {
        try {
            this.write = new PrintWriter(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> users = this.users.getUsers();
        ArrayList<String> passes = this.users.getPasses();
        ArrayList<Integer> scores = this.users.getScores();

        for(int i=0;i< users.size();i++)
        {
            write.print("PLAYER\n");
            write.print(users.get(i) + " "+passes.get(i)+" "+scores.get(i)+"\n");
        }

        for (Tournament tournament : tournaments)
        {
            write.print("TOURNAMENT\n");

            write.print(tournament.name + " " + tournament.getPlayersN() + " " + tournament.getFieldSize() + " " + tournament.getShipSurface() + " " + tournament.getBiggestShip() + " " + tournament.getMatchesCounter() + "\n");

            for(int j=0;j<tournament.getPlayersOrder().size();j++)
            {
                write.print(tournament.getPlayersOrder().get(j) + " ");
            }
            write.print("\n");

            for(int j=0;j<tournament.getPlayers().size();j++)
            {
                write.print(tournament.getPlayers().get(j) + " ");
            }
            write.print("\n");

            for(int j=0;j<tournament.getPoints().size();j++)
            {
                write.print(tournament.getPoints().get(j) + " ");
            }
            write.print("\n");

            for(int j=0;j<tournament.getPlayersN();j++)
            {
                for(int k=0;k<tournament.getPlayersN();k++)
                {
                    write.print(tournament.getGames()[j][k] + " ");
                }
                write.print("\n");
            }


        }

        write.print("X");
        write.close();
    }

    public void read (JPanel cardPane )
    {
        try {
            this.read = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> users = this.users.getUsers();
        ArrayList<String> passes = this.users.getPasses();
        ArrayList<Integer> scores = this.users.getScores();

        String user, pass;
        int score;

        String tName;
        int playersN, fieldSize, shipsSurface, biggestShip, matchesCounter;

        while(read.hasNextLine())
        {
            while(read.nextLine().equals("PLAYER"))
            {
                user=read.next();
                pass=read.next();
                score=read.nextInt();

                if(this.users.isUser(user)==-1)
                {
                    users.add(user);
                    passes.add(pass);
                    scores.add(score);
                }
            }

            while(read.nextLine().equals("TOURNAMENT"))
            {

                this.tHomePages.add(new TournamentHome("tHome Pane "+ this.tHomePages.size()));
                cardPane.add(this.getLastTHomePage(), "tHome Pane "+ (this.tHomePages.size() - 1));
                this.getLastTHomePage().back.addActionListener( this);
                this.getLastTHomePage().start.addActionListener( this);


                tName = read.next();
                playersN = read.nextInt();
                fieldSize = read.nextInt();
                shipsSurface = read.nextInt();
                biggestShip = read.nextInt();
                matchesCounter = read.nextInt();

                this.addTournament(new Tournament(this.users, tName, playersN, fieldSize, shipsSurface, biggestShip));
                this.getLastTournament().setMatchesCounter(matchesCounter);
            }

        }

    }

    public ArrayList<Tournament> getTournaments ( ) { return this.tournaments; }

    public void addTournament(Tournament t)
    {
        this.tournaments.add(t);
    }

    public Tournament getLastTournament()
    {
        return this.tournaments.get(this.tournaments.size()-1);
    }

    public Tournament getTournament(int i)
    {
        return this.tournaments.get(i);
    }

    public void deleteLastTournament()
    {
        this.tournaments.remove(this.tournaments.size()-1);
        this.tHomePages.remove(this.tHomePages.size()-1);
    }

    public void deleteTournament(int i)
    {
        this.tournaments.remove(i);
        this.tHomePages.remove(i);
    }

    public TournamentHome getLastTHomePage()
    {
        return this.tHomePages.get(this.tHomePages.size()-1);
    }

    public TournamentHome getTHomePage(int i)
    {
        return this.tHomePages.get(i);
    }


    @Override
    public void actionPerformed (ActionEvent e)
    {

    }
}
