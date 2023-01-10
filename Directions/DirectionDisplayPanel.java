package Panels.Directions;

import Repository.PublicTransit;

import javax.swing.*;
import java.awt.*;

public class DirectionDisplayPanel extends JPanel {
    DirectionsMainPanel connectedMainPanel;
    private  static String path;
    private JTextArea textArea;
    private JScrollPane directionDisplayScrollPanel;
    DirectionDisplayPanel()
    {
        load();
    }
    private void load()
    {
        this.setPreferredSize(new Dimension(450,700));
        textArea=new JTextArea();
        textArea.setPreferredSize(new Dimension(450,700));
        //this.add(textArea);
        textArea.setVisible(false);
        directionDisplayScrollPanel=new JScrollPane();
        directionDisplayScrollPanel.setPreferredSize(new Dimension(450,550));
        directionDisplayScrollPanel.setHorizontalScrollBar(null);
        directionDisplayScrollPanel.setVisible(true);
        this.add(directionDisplayScrollPanel);
        directionDisplayScrollPanel.setViewportView(textArea);
    }

    public void DisplayDirections(String origin,String destination,String type)
    {
        GetDirections(origin,destination,type);
        DisplayComponents();
    }
    private void DisplayComponents()
    {
        textArea.setVisible(true);
    }
    private void GetDirections(String origin,String destination,String type)
    {
        textArea.setText(PublicTransit.CalculatePath(origin,destination,""));
        textArea.setVisible(true);
    }
}
