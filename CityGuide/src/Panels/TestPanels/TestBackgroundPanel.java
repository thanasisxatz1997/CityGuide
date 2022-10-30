package Panels.TestPanels;

import Panels.Stores.StoresPanel;
import Repository.ImageResizer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class TestBackgroundPanel extends JPanel {

    private Image backgroundImage;
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
        LoadBackgroundImage();
        this.setLayout(new BorderLayout());
        this.setVisible(true);
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);
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

    /*@Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(backgroundImage,0,0,null);
    }*/
}
