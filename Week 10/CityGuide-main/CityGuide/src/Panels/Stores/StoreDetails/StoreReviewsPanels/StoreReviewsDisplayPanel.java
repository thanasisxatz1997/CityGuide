package Panels.Stores.StoreDetails.StoreReviewsPanels;

import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreReviewsDisplayPanel extends JPanel {
    private ArrayList<Document> reviewsDocList;
    private int width=405;
    private int height=350;
    public StoreReviewsDisplayPanel(ArrayList<Document> revDocList)
    {
        reviewsDocList=revDocList;
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(width,height));
        //this.setMaximumSize(new Dimension(430,350));
        this.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        LoadReviews();
    }
    private void LoadReviews()
    {
        for(int i=0;i<reviewsDocList.size();i++)
        {
            height=height+70;
            this.setPreferredSize(new Dimension(width,height));
            this.setMinimumSize(new Dimension(width,height));
            SingleReviewPanel singleReviewPanel=new SingleReviewPanel(reviewsDocList.get(i).get("author_name").toString(),reviewsDocList.get(i).get("text").toString(),reviewsDocList.get(i).get("rating").toString());
            this.add(singleReviewPanel);
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
