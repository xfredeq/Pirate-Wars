package Other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Data
{
    File file;
    Scanner read;
    PrintWriter write;
    Users users;

    public Data(Users users)
    {
        this.users=users;
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
                    scores.add((Integer)score);
                }
            }
        }

    }



}
