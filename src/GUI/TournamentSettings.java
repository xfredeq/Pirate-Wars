package GUI;

import Other.Data;
import Other.Tournament;
import Other.Users;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TournamentSettings extends JPanel implements ChangeListener, ActionListener {
    private int fieldSize;
    private int shipSurface;
    private int biggestShip;
    private int maxShipSurface;
    private int playersNumber;

    private Data data;

    private BufferedImage image;

    public JButton back = new JButton("back");
    public JButton create = new JButton("Create Tournament");

    private final JButton dft = new JButton("Restore Default");
    private final JTextField name = new JTextField();
    private final JSlider players = new JSlider(JSlider.HORIZONTAL, 3, 8, 5);
    private final JSlider field = new JSlider(JSlider.HORIZONTAL, 5, 15, 10);
    private final JSlider ships = new JSlider(JSlider.HORIZONTAL);
    private final JLabel playersLabel = new JLabel("Players number: ");
    private final JLabel fieldLabel = new JLabel("Field Size(NxN): ");
    private final JLabel shipsLabel = new JLabel("Ship surface: ");
    private final JLabel title = new JLabel("Pirate Wars");

    public TournamentSettings (Data data )
    {
        this.data=data;
        setDefault();
        setSettings();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
        players.addChangeListener(this);
        field.addChangeListener(this);
        dft.addActionListener(this);
    }

    private void setComponents ( )
    {
        dft.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        create.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        name.setMaximumSize(new Dimension(200,1));

        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        shipsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Bradley Hand ITC", Font.BOLD, 100));
        title.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        title.setForeground(Color.RED);
        title.setOpaque(true);

        players.setAlignmentX(Component.CENTER_ALIGNMENT);
        players.setMajorTickSpacing(1);
        players.setPaintTicks(true);
        players.setPaintLabels(true);
        players.setMaximumSize(new Dimension(1000, 80));

        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setMajorTickSpacing(1);
        field.setPaintTicks(true);
        field.setPaintLabels(true);
        field.setMaximumSize(new Dimension(1000, 80));

        ships.setAlignmentX(Component.CENTER_ALIGNMENT);
        ships.setMinimum(8);
        ships.setMaximum(25);
        ships.setValue(20);
        ships.setMajorTickSpacing(1);
        ships.setPaintTicks(true);
        ships.setPaintLabels(true);
        ships.setMaximumSize(new Dimension(1000, 80));

        File imageFile = new File("graphics\\bg1.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }

    private void addComponents ( )
    {
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(dft);
        add(Box.createVerticalGlue());
        add(name);
        add(Box.createVerticalGlue());
        add(playersLabel);
        add(players);
        add(Box.createVerticalGlue());
        add(fieldLabel);
        add(field);
        add(Box.createVerticalGlue());
        add(shipsLabel);
        add(ships);
        add(Box.createVerticalGlue());
        add(create);
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createVerticalGlue());
    }

    @Override
    public void paintComponent (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    private void adjustSettings ( )
    {
        biggestShip = (int) (fieldSize * 0.4);
        maxShipSurface = (int) (fieldSize * fieldSize * 0.25);
    }

    public void setDefault ( )
    {
        setDefaultName();
        players.setValue(5);
        field.setValue(10);
        ships.setValue(20);
    }

    private void setSettings ( )
    {
        playersNumber=players.getValue();
        fieldSize = field.getValue();
        shipSurface = ships.getValue();
        adjustSettings();
    }

    private void setDefaultName()
    {
        ArrayList<Tournament> t = data.getTournaments();
        //t.add(new Tournament(new Users(),"Tournament_1",1,1,1,2));
        String tmp, name = "Tournament_";
        boolean czy=true;
        for(int i=1;i<10000;i++)
        {
            czy=true;
            tmp=name+String.valueOf(i);
            for(int j=0;j<t.size();j++)
            {
                if(tmp.equals(t.get(j).name))
                {
                    czy=false;
                    break;
                }
            }
            if(czy)
            {
                name+=String.valueOf(i);
                this.name.setText(name);
                return;
            }
        }

    }

    @Override
    public void stateChanged (ChangeEvent e)
    {
        Object source = e.getSource();
        if (source == field) {
            fieldSize = field.getValue();
            adjustSettings();
            ships.setMinimum((int) (field.getValue() * 0.8));
            ships.setMaximum(maxShipSurface);
            ships.setValue(maxShipSurface * 4 / 5);
        }
        else if (source==players)
        {
            this.playersNumber=players.getValue();
        }
    }

    @Override
    public void actionPerformed (ActionEvent ev)
    {
        Object source = ev.getSource();
        if (source == dft)
            setDefault();

    }

    public int getBiggestShip ( )
    {
        return biggestShip;
    }

    public int getShipSurface ( )
    {
        return shipSurface;
    }

    public int getFieldSize ( )
    {
        return fieldSize;
    }

    public int getPlayers ( )
    {
        return playersNumber;
    }

    public String getName ( )
    {
        return name.getText();
    }


}
