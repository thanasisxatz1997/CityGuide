package Panels.Tips;

import javax.swing.*;
import java.awt.*;

public class TipsPanel extends JPanel {
    private Image backgroundImage;
    private TipsDisplayPanel tipsDisplayPanel;
    public TipsPanel()
    {
        Load();
    }
    private void Load()
    {
        backgroundImage= new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
        tipsDisplayPanel=new TipsDisplayPanel();
        this.add(tipsDisplayPanel);
    }

    @Override
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
