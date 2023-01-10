package MainGui.Panels.Activities;

import javax.swing.*;
import java.awt.*;

public class ActivitiesMainPanel extends JPanel {
    private ActivitiesFilterPanel activitiesFilterPanel;
    private ActivitiesDisplayPanel activitiesDisplayPanel;
    private JScrollPane displayScrollPane;

    public ActivitiesMainPanel()
    {
        Load();

    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(747,593));
        //this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new FlowLayout());
        activitiesFilterPanel=new ActivitiesFilterPanel();
        this.add(activitiesFilterPanel);
        activitiesDisplayPanel=new ActivitiesDisplayPanel();
        displayScrollPane=new JScrollPane(activitiesDisplayPanel);
        displayScrollPane.setPreferredSize(new Dimension(745,550));
        this.add(displayScrollPane);
        this.setVisible(true);

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));

        this.revalidate();
        this.repaint();
    }

}