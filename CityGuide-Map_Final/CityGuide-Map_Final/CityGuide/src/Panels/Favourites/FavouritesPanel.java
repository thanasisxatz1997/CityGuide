package Panels.Favourites;

import Panels.Stores.StoreDisplayPanel;
import Panels.Stores.StoreFilterPanel;

import javax.swing.*;
import java.awt.*;

public class FavouritesPanel extends JPanel {
    private Image backgroundImage;
    private static FavouritesDisplayPanel favouritesDisplayPanel;
    private static FavouritesFilterPanel favouritesFilterPanel;
    private JScrollPane favouritesDisplayScrollPane;
    public FavouritesPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(750,500));
        this.setMaximumSize(new Dimension(750,500));
        this.setLayout(new BorderLayout());
        favouritesDisplayPanel=new FavouritesDisplayPanel();
        favouritesFilterPanel=new FavouritesFilterPanel();
        favouritesDisplayPanel.SetConnectedFavouritesFilterPanel(favouritesFilterPanel);
        favouritesFilterPanel.SetConnectedFavouritesDisplayPanel(favouritesDisplayPanel);

        favouritesDisplayScrollPane=new JScrollPane(favouritesDisplayPanel);
        favouritesDisplayScrollPane.setPreferredSize(new Dimension(550,100));
        favouritesDisplayScrollPane.setHorizontalScrollBar(null);
        favouritesDisplayScrollPane.setVisible(true);

        this.add(favouritesDisplayScrollPane,BorderLayout.WEST);
        this.add(favouritesFilterPanel,BorderLayout.EAST);

        LoadBackgroundImage();
        this.setVisible(true);
    }
    private void LoadBackgroundImage()
    {
        Dimension dim= new Dimension();
        backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
        dim.width=backgroundImage.getWidth(null);
        dim.height=backgroundImage.getHeight(null);
    }
    @Override
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
