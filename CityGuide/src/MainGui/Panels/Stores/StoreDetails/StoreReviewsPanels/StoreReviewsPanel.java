package MainGui.Panels.Stores.StoreDetails.StoreReviewsPanels;
import org.bson.Document;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class StoreReviewsPanel extends JPanel {
    private ArrayList<Document> reviewsDocList;
    private Document storeDoc;
    private StoreReviewsDisplayPanel storeReviewsDisplayPanel;
    private StoreReviewsWritePanel storeReviewsWritePanel;
    public StoreReviewsPanel(ArrayList<Document> revDocList,Document doc)
    {
        reviewsDocList=revDocList;
        storeDoc=doc;
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(430,460));
        this.setMaximumSize(new Dimension(430,460));
        this.setLayout(new BorderLayout());
        storeReviewsDisplayPanel=new StoreReviewsDisplayPanel(reviewsDocList);
        storeReviewsWritePanel=new StoreReviewsWritePanel(storeDoc);

        StoreReviewsScrollPane storeReviewsDisplayPane=new StoreReviewsScrollPane();
        storeReviewsDisplayPane.setPreferredSize(new Dimension(430,350));
        storeReviewsDisplayPane.setMaximumSize(new Dimension(430,350));
        storeReviewsDisplayPane.setForeground(new Color(0f,0f,0f,0f));
        storeReviewsDisplayPane.setBackground(new Color(0f,0f,0f,0f));
        storeReviewsDisplayPane.setViewportView(storeReviewsDisplayPanel);
        this.add(storeReviewsDisplayPane,BorderLayout.NORTH);
        this.add(storeReviewsWritePanel,BorderLayout.SOUTH);
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
