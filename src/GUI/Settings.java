package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel implements ChangeListener
{
    private int fieldSize;
    private int biggestShip;
    private int maxShipSurface;
    private BufferedImage image;
    public JButton dft=new JButton("Restore Default");
    public JButton back=new JButton("back");
    public JSlider field=new JSlider(JSlider.HORIZONTAL,5,15,10);
    public JSlider ships=new JSlider(JSlider.HORIZONTAL);
    private JLabel fieldLabel=new JLabel("Field Size(NxN): ");
    private JLabel shipsLabel=new JLabel("Ship surface: ");
    private JLabel title=new JLabel("Pirate Wars");

    public Settings ( )
    {
        setDefault();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setComponents();
        addComponents();
        field.addChangeListener(this);
    }

    private void setComponents ( )
    {
        dft.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana",Font.PLAIN,36));

        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setMajorTickSpacing(1);
        field.setPaintTicks(true);
        field.setPaintLabels(true);
        field.setMaximumSize(new Dimension(800,80));

        ships.setAlignmentX(Component.CENTER_ALIGNMENT);
        ships.setMinimum((int) (field.getValue()*0.4));
        ships.setMaximum(maxShipSurface);
        ships.setValue((ships.getMinimum()+ships.getMaximum())/2);
        ships.setMajorTickSpacing(1);
        ships.setPaintTicks(true);
        ships.setPaintLabels(true);
        ships.setMaximumSize(new Dimension(800,80));

        File imageFile = new File("graphics\\bg1.jpg");
        try
        {
            image = ImageIO.read(imageFile);
        }
        catch (IOException e)
        {
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
        add(fieldLabel);
        add(field);
        add(Box.createVerticalGlue());
        add(ships);
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createVerticalGlue());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    private void adjustSettings()
    {
        biggestShip = (int) (fieldSize*0.4);
        maxShipSurface = (int) (fieldSize*fieldSize*0.25);
    }

    public void setDefault()
    {
        fieldSize=10;
        adjustSettings();
    }

    public void setSettings()
    {
        fieldSize=field.getValue();
        adjustSettings();
    }


    public int getFieldSize ( )
    {
        return fieldSize;
    }

    @Override
    public void stateChanged (ChangeEvent e)
    {
        Object source = e.getSource();
        if (source == field)
        {
            fieldSize=field.getValue();
            ships.setMinimum((int) (field.getValue()*0.4));
            ships.setMaximum(maxShipSurface);
            ships.setValue((ships.getMinimum()+ships.getMaximum())/2);
        }
    }
}
