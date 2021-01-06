package Backend;

import java.util.ArrayList;

public class Users {
    private ArrayList<String> users;
    private ArrayList<String> passes;
    private String currentUsername;
    private String secondUsername;

    public Users ( )
    {
        currentUsername = "";
        secondUsername = "";
        users = new ArrayList<>();
        passes = new ArrayList<>();
        users.add("admin");
        passes.add("admin");
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
                secondUsername = name;
            return true;
        }

        return false;
    }

    public void addUser (String name, String password)
    {
        users.add(name);
        passes.add(password);
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

    public String getCurrentUsername ( )
    {
        return currentUsername;
    }
    public String getSecondUsername ( )
    {
        return secondUsername;
    }

    public void setCurrentUsername (String currentUsername)
    {
        this.currentUsername = currentUsername;
    }
}
