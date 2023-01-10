package Panels.Directions;
import Panels.Favourites.FavouritesDisplayPanel;
import Panels.Favourites.FavouritesFilterPanel;
import javax.swing.*;
public class DirectionFrame extends JFrame {
    private DirectionsMainPanel directionsMainPanel;
    public DirectionFrame()
    {
        load();
    }

    private void load()
    {
        directionsMainPanel=new DirectionsMainPanel();
        this.add(directionsMainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(700,700);
        this.setLocation(100, 150);
    }

}
