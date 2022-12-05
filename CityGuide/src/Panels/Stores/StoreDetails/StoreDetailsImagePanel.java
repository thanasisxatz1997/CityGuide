package Panels.Stores.StoreDetails;

import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class StoreDetailsImagePanel extends JPanel {
    private Image backgroundImage;
    public StoreDetailsButtonPanel storeDetailsButtonPanel;
    public StoreDetailsDisplayPanel storeDetailsDisplayPanel;
    public StoreDetailsFrame connectedFrame;
    public StoreDetailsImagePanel()
    {
        Load();
    }
    private void Load()
    {
        this.setLayout(new BorderLayout());
        storeDetailsDisplayPanel=new StoreDetailsDisplayPanel();
        storeDetailsDisplayPanel.connectedStoreDetailsImagePanel=this;
        storeDetailsButtonPanel=new StoreDetailsButtonPanel();
        storeDetailsButtonPanel.connectedStoreDetailsImagePanel=this;
        this.add(storeDetailsButtonPanel,BorderLayout.NORTH);
        this.add(storeDetailsDisplayPanel,BorderLayout.SOUTH);
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
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

        this.revalidate();
        this.repaint();
    }
}
