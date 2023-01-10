package MainGui.Panels.Directions;

import javax.swing.*;

public class DirectionDisplayPanel extends JPanel {

    private JTextArea textArea;
    DirectionDisplayPanel()
    {
        Load();
    }
     private void Load()
     {
         textArea=new JTextArea();
         textArea.setVisible(false);
     }

    public void DisplayDirections()
    {
        GetDirections();
        DisplayComponents();
    }
    private void DisplayComponents()
    {
        textArea.setVisible(true);
    }
    private void GetDirections()
    {

    }
}
