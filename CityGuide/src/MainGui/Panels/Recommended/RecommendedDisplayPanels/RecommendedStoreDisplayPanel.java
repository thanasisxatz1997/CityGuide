package MainGui.Panels.Recommended.RecommendedDisplayPanels;

import MainGui.Panels.Recommended.RecommendedSinglePanel;
import Repository.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

import static Repository.DataManager.GetRandomRecommendedStore;

public class RecommendedStoreDisplayPanel extends JPanel {

    private RecommendedSinglePanel recommendedSinglePanel;

    public RecommendedStoreDisplayPanel()
    {
        Load();
    }
    private void Load()
    {
        //this.setPreferredSize(new Dimension(400,400));
        //this.setMaximumSize(new Dimension(400,400));
        //this.setMinimumSize(new Dimension(400,400));
        //this.setSize(new Dimension(400,400));
        recommendedSinglePanel=new RecommendedSinglePanel();
        this.add(recommendedSinglePanel);
        Document randomStoreDoc=GetRandomRecommendedStore();
        recommendedSinglePanel.labelName.setText(randomStoreDoc.get("name").toString());
        recommendedSinglePanel.labelRating.setText(randomStoreDoc.get("rating").toString());
        recommendedSinglePanel.backgroundImage=DataManager.GetRandomStoreImage(randomStoreDoc);

        this.setVisible(true);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
