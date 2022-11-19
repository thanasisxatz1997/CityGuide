package Panels.TestPanels;

import Panels.Favourites.FavouritesPanel;
import Panels.Stores.StoresPanel;
import Repository.ImageResizer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TestBackgroundPanel extends JPanel {

    private HomeHintsPanel hintsPanel;
    private Image backgroundImage;
    private Image logoImage;
    public TestBackgroundPanel()
    {
        LoadPanel();
    }

    private void LoadPanel()
    {
        this.setPreferredSize(new Dimension(750,600));
        this.setMaximumSize(new Dimension(750,600));
        this.setMinimumSize(new Dimension(750,600));
        this.setSize(new Dimension(750,600));
        this.setLayout(new BorderLayout());
        LoadBackgroundImage();
        LoadLogoImage();
        LoadHintsPanel();
        this.setVisible(true);
        System.out.println("Panel Added");

    }

    public void LoadHintsPanel()
    {
        hintsPanel=new HomeHintsPanel();
        this.add(hintsPanel,BorderLayout.SOUTH);
    }

    private void LoadBackgroundImage()
    {
        Dimension dim= new Dimension();
        try {
            ImageResizer.resize("src/resources/BackgroundImages/RomeImage.jpg", "src/resources/BackgroundImages/RomeImageResized.jpg", this.getWidth(), this.getHeight());
        } catch (IOException e) {
            System.out.println("CANT RESIZE");
            e.printStackTrace();
        }
        backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
        dim.width=backgroundImage.getWidth(null);
        dim.height=backgroundImage.getHeight(null);
    }
    private void LoadLogoImage()
    {
        logoImage=new ImageIcon("src/resources/Logos/LOGOcrTrNewpng.png").getImage();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);
        g.drawImage(logoImage,220,15,null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .0f));

        this.revalidate();
        this.repaint();
    }

    public void AddStorePanel()
    {
        this.removeAll();
        this.add(new StoresPanel());
        this.repaint();
        this.revalidate();
        System.out.println("Stores Added");
    }
    public void AddMapPanel()
    {
        this.removeAll();
        this.add(new TestMapPanel());
        this.repaint();
        this.revalidate();
    }

    public void AddFavouritesPanel()
    {
        this.removeAll();
        this.add(new FavouritesPanel());
        this.repaint();
        this.revalidate();
    }
    public void AddRecommendedPanel()
    {
        this.removeAll();
        this.add(new RecommendedPanel());
        this.repaint();
        this.revalidate();
    }

    public void AddUserPanel()
    {
        this.removeAll();
        this.add(new UserPanel());
        this.repaint();
        this.revalidate();
    }

    /*@Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(backgroundImage,0,0,null);
    }*/
}
