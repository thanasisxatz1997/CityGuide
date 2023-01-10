package MainGui.Panels.Stores.StoreDetails.StoreReviewsPanels;

import javax.swing.*;
import java.awt.*;

public class StoreReviewsScrollPane extends JScrollPane {
    public StoreReviewsScrollPane()
    {

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        this.revalidate();
        this.repaint();
    }
}
