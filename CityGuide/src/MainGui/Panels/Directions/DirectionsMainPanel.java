package MainGui.Panels.Directions;

import javax.swing.*;
import java.awt.*;

public class DirectionsMainPanel extends JPanel {
    private Image backgroundImage;
    public DirectionInfoPanel directionInfoPanel;
    public DirectionDisplayPanel directionDisplayPanel;
    DirectionsMainPanel()
    {
        load();
    }
    private void load()
    {
        backgroundImage=new ImageIcon("src/resources/BackgroundImages/randomMapPic.jpg").getImage();
        directionDisplayPanel=new DirectionDisplayPanel();
        directionInfoPanel=new DirectionInfoPanel();
        directionInfoPanel.connectedMainPanel=this;
        directionDisplayPanel.connectedMainPanel=this;
        this.setLayout(new BorderLayout());
        this.add(directionInfoPanel,BorderLayout.WEST);
        this.add(directionDisplayPanel,BorderLayout.EAST);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);

        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        this.revalidate();
        this.repaint();
    }

}