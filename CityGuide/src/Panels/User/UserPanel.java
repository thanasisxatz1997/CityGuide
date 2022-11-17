package Panels.User;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    private UserOptionsPanel userOptionsPanel;
    private Image backgroundImage;
    public UserPanel()
    {
        this.setPreferredSize(new Dimension(750,600));
        this.setMaximumSize(new Dimension(750,600));
        this.setLayout(new BorderLayout());

        LoadBackgroundImage();

        userOptionsPanel=new UserOptionsPanel();
        this.add(userOptionsPanel,BorderLayout.WEST);

        this.setVisible(true);
    }
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
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        this.revalidate();
        this.repaint();
    }
}
