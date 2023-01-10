package MainGui.Panels.Stores.StoreDetails.StoreOpeningHoursPanels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreOpeningHoursPanel extends JPanel {
    private ArrayList<String> openHoursStrList;
    private StoreOpeningHoursDisplayPanel storeOpeningHoursDisplayPanel;
    public StoreOpeningHoursPanel(ArrayList<String> opHStrList)
    {
        openHoursStrList=opHStrList;
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(450,400));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        storeOpeningHoursDisplayPanel=new StoreOpeningHoursDisplayPanel(openHoursStrList);
        this.add(storeOpeningHoursDisplayPanel);
        this.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(backgroundImage,0,0,null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
