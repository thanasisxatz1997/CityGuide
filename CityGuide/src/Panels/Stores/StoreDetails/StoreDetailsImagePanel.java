package Panels.Stores.StoreDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class StoreDetailsImagePanel extends JPanel {
    private Image backgroundImage;
    public StoreDetailsDisplayPanel storeDetailsDisplayPanel;
    public StoreDetailsFrame connectedFrame;
    public StoreDetailsImagePanel()
    {
        Load();
    }
    private void Load()
    {
        storeDetailsDisplayPanel=new StoreDetailsDisplayPanel();
        this.add(storeDetailsDisplayPanel);
    }
    public void SetBackgroundImage(Image image)
    {
        backgroundImage=image;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(backgroundImage,0,0,null);
        g.setClip(new Rectangle2D.Float(0,0,getSize().width-1, getSize().height-1));
        g.drawImage(backgroundImage, 0, 0, getSize().width-1, getSize().height-1, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        this.revalidate();
        this.repaint();
    }
}
