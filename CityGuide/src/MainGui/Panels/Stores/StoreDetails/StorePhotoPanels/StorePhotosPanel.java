package MainGui.Panels.Stores.StoreDetails.StorePhotoPanels;

import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StorePhotosPanel extends JPanel {
    private StorePhotoDisplayPanel storePhotoDisplayPanel;
    private ArrayList<Document> photosDocList;
    public StorePhotosPanel(ArrayList<Document> phDocList)
    {
        photosDocList=phDocList;
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(580,520));
        storePhotoDisplayPanel=new StorePhotoDisplayPanel(photosDocList);
        this.add(storePhotoDisplayPanel);
        this.setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
