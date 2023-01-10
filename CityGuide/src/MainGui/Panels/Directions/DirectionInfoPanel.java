package MainGui.Panels.Directions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionInfoPanel extends JPanel {
    DirectionsMainPanel connectedMainPanel;
    JButton submitButton=new JButton();
    public DirectionInfoPanel()
    {
        Load();
    }
    private void Load()
    {
        submitButton=new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               connectedMainPanel.directionDisplayPanel.DisplayDirections();
            }
        });
    }

}
