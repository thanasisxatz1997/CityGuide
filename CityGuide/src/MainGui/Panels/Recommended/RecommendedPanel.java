package MainGui.Panels.Recommended;

import MainGui.Panels.Recommended.RecommendedDisplayPanels.RecommendedStoreDisplayPanel;
import MainGui.Panels.Recommended.RecommendedDisplayPanels.TestRecommendedDisplayPanel2;

import javax.swing.*;
import java.awt.*;

public class RecommendedPanel extends JPanel {
    private RecommendedStoreDisplayPanel recommendedStoreDisplayPanel;
    private Image backgroundImage;
    public RecommendedPanel()
    {
        Load();
    }

    private void Load()
    {
        this.setPreferredSize(new Dimension(750,600));
        this.setMaximumSize(new Dimension(750,600));
        this.setMinimumSize(new Dimension(750,600));
        this.setSize(new Dimension(750,600));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        LoadBackgroundImage();
        //recommendedStoreDisplayPanel=new RecommendedStoreDisplayPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        //this.add(new TestRoundPanel());
        this.add(new TestRecommendedDisplayPanel2());
    }

    /*private void Load()
    {
        this.setPreferredSize(new Dimension(750,550));
        this.setMaximumSize(new Dimension(750,550));
        this.setMinimumSize(new Dimension(750,550));
        this.setSize(new Dimension(750,550));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        LoadBackgroundImage();
        recommendedStoreDisplayPanel=new RecommendedStoreDisplayPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(0,0,0,0);
        c.weighty=0.5;
        c.weightx=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=0;
        this.add(recommendedStoreDisplayPanel,c);

        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(new JTextArea("aAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "aAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),c);

        //c.weightx=0.5;
        //c.weighty=0.5;
        //c.gridheight=1;
        //c.gridwidth=3;
        //c.gridx=2;
        //c.gridy=0;
        //this.add(new TextArea(""));
        //recommendedStoreDisplayPanel=new RecommendedStoreDisplayPanel();

        this.setVisible(true);
    }*/
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
