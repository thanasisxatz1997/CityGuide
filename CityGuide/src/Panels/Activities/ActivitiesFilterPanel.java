package Panels.Activities;

import javax.swing.*;
import java.awt.*;

public class ActivitiesFilterPanel extends JPanel {
    public ActivitiesFilterPanel()
    {
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(734,35));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new FlowLayout());
        JComboBox comboBox=new JComboBox();
        comboBox.addItem("Daily trips.");
        comboBox.addItem("Museums");
        comboBox.addItem("Sightseeing");
        this.add(comboBox);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        this.revalidate();
        this.repaint();
    }

}
