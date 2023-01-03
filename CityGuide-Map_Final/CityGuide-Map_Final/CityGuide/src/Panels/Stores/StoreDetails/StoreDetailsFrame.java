package Panels.Stores.StoreDetails;

import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreDetailsFrame extends JFrame {
    public String storeName;
    public StoreDetailsImagePanel storeDetailsImagePanel;
    public StoreDetailsFrame()
    {
        Load();
    }
    public void Load()
    {
        storeDetailsImagePanel=new StoreDetailsImagePanel();

        this.setPreferredSize(new Dimension(600,600));
        this.add(storeDetailsImagePanel);
        this.setVisible(true);
        this.pack();
        this.revalidate();
        this.repaint();
    }

    public void SetName()
    {
        this.setTitle(storeName);
    }
}
