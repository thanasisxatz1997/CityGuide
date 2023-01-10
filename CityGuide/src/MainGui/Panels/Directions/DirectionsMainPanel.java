package MainGui.Panels.Directions;

import javax.swing.*;

public class DirectionsMainPanel extends JPanel {
    public DirectionInfoPanel directionInfoPanel;
    public DirectionDisplayPanel directionDisplayPanel;
    DirectionsMainPanel()
    {
        Load();
    }
    public void Load()
    {

        directionDisplayPanel=new DirectionDisplayPanel();
        directionInfoPanel=new DirectionInfoPanel();
        directionInfoPanel.connectedMainPanel=this;
    }
}
