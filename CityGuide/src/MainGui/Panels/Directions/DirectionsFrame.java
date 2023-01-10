package MainGui.Panels.Directions;

import javax.swing.*;

public class DirectionsFrame extends JFrame {
    private DirectionsMainPanel directionsMainPanel;
    public DirectionsFrame()
    {
        Load();
    }
    private void Load()
    {
        directionsMainPanel=new DirectionsMainPanel();
        this.pack();
        this.setVisible(true);
    }
}
