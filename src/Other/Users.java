package Other;

import java.util.ArrayList;

public class Users {
    private ArrayList<String> users;
    private ArrayList<String> passes;
    private ArrayList<Integer> scores;
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
        for (int j = 0; j < password.length; j++)
            pass += password[j];

        if (i >= 0 && passes.get(i).equals(pass)) {
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
        for (int j = 0; j < password1.length; j++)
            pass1 += password1[j];

        String pass2 = "";
        for (int j = 0; j < password2.length; j++)
            pass2 += password2[j];

        if (!pass1.equals(pass2))
            return false;
        else if (isUser(name) >= 0)
            return false;
        else if (name.length() == 0)
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
