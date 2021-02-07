package Other;

import java.util.ArrayList;

public class Users {
    private final ArrayList<String> users;
    private final ArrayList<String> passes;
    private final ArrayList<Integer> scores;
    private String currentUsername;
    private String secondUsername;

    public Users ( )
    {
        currentUsername = "";
        secondUsername = "";
        users = new ArrayList<>();
        passes = new ArrayList<>();
        scores = new ArrayList<>();
        /*users.add("admin");
        passes.add("admin");
        scores.add(0);
        users.add("player");
        passes.add("player");
        scores.add(0);*/
    }

    public int isUser (String name)
    {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(name))
                return i;
        }
        return -1;
    }

    public boolean login (String name, char[] password)
    {
        int i = isUser(name);
        String pass = "";
        for (char c : password) pass += c;

        if (i >= 0 && passes.get(i).equals(pass))
        {
            if (currentUsername.equals(""))
                currentUsername = name;
            else
            {
             if (name.equals(currentUsername))
                 return false;
             else
                 secondUsername = name;
            }
            return true;
        }

        return false;
    }

    public void addUser (String name, String password)
    {
        users.add(name);
        passes.add(password);
        scores.add(0);
    }

    public boolean register (String name, char[] password1, char[] password2)
    {
        String pass1 = "";
        for (char value : password1) pass1 += value;

        String pass2 = "";
        for (char c : password2) pass2 += c;

        if (!pass1.equals(pass2))
            return false;
        else if (isUser(name) >= 0)
            return false;
        else if (name.length() == 0)
            return false;
        else if (name.contains("Guest"))
            return false;
        else {
            addUser(name, pass1);
            currentUsername = name;
            return true;
        }

    }
    public void setCurrentUsername (String currentUsername)
    {
        this.currentUsername = currentUsername;
    }

    public void setSecondUsername (String secondUsername)
    {
        this.secondUsername = secondUsername;
    }

    public String getCurrentUsername ( )
    {
        return currentUsername;
    }
    public String getSecondUsername ( )
    {
        return secondUsername;
    }

    public Integer getScore (String name )
    {
        int i=isUser(name);
        if(i>=0)
            return scores.get(i);
        else
            return -1;
    }

    public void addScore (String name, int points )
    {
        int i=isUser(name);
        if(i>=0)
            scores.set(i, scores.get(i) + points);
    }

    public void setScore (String name, int points )
    {
        int i=isUser(name);
        if(i>=0)
            scores.set(i, points);
    }

    public void sort()
    {
        Integer tmp1;
        String tmp2;
        String tmp3;
        for(int i=0;i<users.size();i++)
        {
            for(int j=i+1;j<users.size();j++)
            {
                if(scores.get(j)>scores.get(i))
                {
                    tmp1=scores.get(i);
                    scores.set(i,scores.get(j));
                    scores.set(j,tmp1);

                    tmp2=users.get(i);
                    users.set(i,users.get(j));
                    users.set(j,tmp2);

                    tmp3=passes.get(i);
                    passes.set(i,passes.get(j));
                    passes.set(j,tmp3);
                }
            }
        }
    }

    public ArrayList<String> getUsers ( )
    {
        return users;
    }

    public ArrayList<String> getPasses ( )
    {
        return passes;
    }

    public ArrayList<Integer> getScores ( )
    {
        return scores;
    }
}
