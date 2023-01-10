package MainGui.Panels.Stores.StoreDetails.StoreReviewsPanels;

import javax.swing.*;
import java.awt.*;

public class StoreReviewDisplayScrollPanel extends JPanel {
    public StoreReviewDisplayScrollPanel()
    {
        this.setPreferredSize(new Dimension(430,350));
        this.setMaximumSize(new Dimension(430,350));
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
