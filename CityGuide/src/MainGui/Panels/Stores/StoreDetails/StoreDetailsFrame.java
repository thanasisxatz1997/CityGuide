package MainGui.Panels.Stores.StoreDetails;

import javax.swing.*;
import java.awt.*;

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
