package Other;

import GUI.TournamentHome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Data
{
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

        write.print("X");
        write.close();
    }

    public void read ( )
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




}
