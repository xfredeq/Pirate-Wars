package Other;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CLK extends JPanel
{
    int minutes=0;
    int seconds=0;
    int time=0;
    JLabel screen = new JLabel();
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            time +=1000;
            minutes = time/60000;
            seconds = (time/1000) % 60;
            screen.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
        }
    });

    public CLK ()
    {
        screen.setForeground(Color.BLACK);
        //screen.setBackground(Color.DARK_GRAY);
        setOpaque(false);
        screen.setFont(new Font("Arial Black",Font.PLAIN,42));
        screen.setText("00:00");
        this.setBorder(BorderFactory.createBevelBorder(1,Color.RED, Color.RED));
        add(screen);
    }

    public void start()
    {
        timer.start();
    }
    public void stop()
    {
        timer.stop();
    }
    public String getTime()
    {
        return (String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }


}
