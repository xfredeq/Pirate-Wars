package com.company;

import GUI.*;

import java.awt.*;

public class Pirate_Wars {

    public static void main (String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run ( )
            {
                Panels game = new Panels();
            }
        });
    }
}
