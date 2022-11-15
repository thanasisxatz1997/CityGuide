package Panels.Stores;

import Repository.ImageResizer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class StoresPanel extends JPanel {
    private StoreDisplayPanel storeDisplayPanel;
    private StoreFilterPanel storeFilterPanel;
    private JScrollPane storesDisplayScrollPane;
    private Image backgroundImage;

    public StoresPanel()
    {
        this.setPreferredSize(new Dimension(750,600));
        this.setMaximumSize(new Dimension(750,600));
        this.setLayout(new BorderLayout());
        storeDisplayPanel=new StoreDisplayPanel();
        storeFilterPanel=new StoreFilterPanel();
        storeDisplayPanel.SetConnectedStoreFilterPanel(storeFilterPanel);
        storeFilterPanel.SetConnectedStoreDisplayPanel(storeDisplayPanel);

        storesDisplayScrollPane=new JScrollPane(storeDisplayPanel);
        storesDisplayScrollPane.setPreferredSize(new Dimension(485,100));
        storesDisplayScrollPane.setHorizontalScrollBar(null);
        storesDisplayScrollPane.setVisible(true);

        this.add(storesDisplayScrollPane,BorderLayout.WEST);
        this.add(storeFilterPanel,BorderLayout.EAST);
        LoadBackgroundImage();
        this.setVisible(true);
        System.out.println("Done");
    }

    private void LoadBackgroundImage()
    {
        Dimension dim= new Dimension();
        backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
        dim.width=backgroundImage.getWidth(null);
        dim.height=backgroundImage.getHeight(null);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        this.revalidate();
        this.repaint();
    }

}
