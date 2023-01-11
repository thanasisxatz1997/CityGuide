package MainGui.Panels.Directions;
import MainGui.Panels.Favourites.FavouritesDisplayPanel;
import MainGui.Panels.Favourites.FavouritesFilterPanel;
import javax.swing.*;
public class DirectionFrame extends JFrame {
    public DirectionsMainPanel directionsMainPanel;
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
        this.setSize(700,570);
        this.setLocation(100, 150);
    }

}