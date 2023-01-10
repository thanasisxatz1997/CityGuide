package MainGui.Panels.Recommended.RecommendedDisplayPanels;

import javax.swing.*;
import java.awt.*;

public class TestRoundPanel extends JPanel {
    public TestRoundPanel()
    {
        this.setPreferredSize(new Dimension(50,50));
        //this.setBorder(new RoundedBorder(50));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(0,0,g.getClipBounds().width, g.getClipBounds().height);
    }
}
