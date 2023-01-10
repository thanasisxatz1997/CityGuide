package Panels.Directions;

import javax.swing.*;
import java.awt.*;

public class DirectionsMainPanel extends JPanel {
    public DirectionInfoPanel directionInfoPanel;
    public DirectionDisplayPanel directionDisplayPanel;
    DirectionsMainPanel()
    {
        load();
    }
    private void load()
    {
        directionDisplayPanel=new DirectionDisplayPanel();
        directionInfoPanel=new DirectionInfoPanel();
        directionInfoPanel.connectedMainPanel=this;
        directionDisplayPanel.connectedMainPanel=this;
        this.setLayout(new BorderLayout());
        this.add(directionInfoPanel,BorderLayout.WEST);
        this.add(directionDisplayPanel,BorderLayout.EAST);


    }

}
