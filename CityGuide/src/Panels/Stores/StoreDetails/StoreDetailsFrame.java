package Panels.Stores.StoreDetails;

import javax.swing.*;
import java.awt.*;

public class StoreDetailsFrame extends JFrame {
    public StoreDetailsImagePanel storeDetailsImagePanel;
    public StoreDetailsFrame()
    {
        Load();
    }
    public void Load()
    {
        storeDetailsImagePanel=new StoreDetailsImagePanel();

        this.setPreferredSize(new Dimension(450,510));
        this.add(storeDetailsImagePanel);
        this.setVisible(true);
        this.pack();
        this.revalidate();
        this.repaint();
    }
}
