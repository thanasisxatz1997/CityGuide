package MainGui.Panels.Activities;

import javax.swing.*;
import java.awt.*;


public class ActivitiesPanel extends JPanel {
    private Image backgroundImage;
    private ActivitiesMainPanel activitiesMainPanel;


    public ActivitiesPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(750,600));
        LoadBackgroundImage();
        activitiesMainPanel =new ActivitiesMainPanel();
        this.add(activitiesMainPanel);
        this.revalidate();
        this.repaint();
        this.setVisible(true);

    }
    private void LoadBackgroundImage()
    {
        this.backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));

        this.revalidate();
        this.repaint();
    }
}