package MainGui.Panels.Tips;

import javax.swing.*;
import java.awt.*;

public class TipsTextDisplayPanel extends JPanel {

    public TipsTextDisplayPanel()
    {
        Load();
    }
    private void Load()
    {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
