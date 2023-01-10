package MainGui.Panels.Stores;

import javax.swing.*;
import java.awt.*;

public class StoreLoadingPanel extends JPanel {
    public Image backgroundImage;
    public StoreLoadingPanel()
    {
        this.setBackground(new Color(0.1f,0.1f,0.1f,0f));
        backgroundImage=new ImageIcon("src/resources/Animations/"+StoreFilterPanel.loadingState+"TR.png").getImage();
        //backgroundImage=new ImageIcon("src/resources/Animations/"+StoreFilterPanel.loadingState+"TR.png").getImage();
        this.repaint();
        this.setVisible(true);
    }
    public void SetNullImage()
    {
        this.backgroundImage=null;
    }
    public void SetStartingImage()
    {
        backgroundImage=new ImageIcon("src/resources/Animations/"+StoreFilterPanel.loadingState+"TR.png").getImage();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundImage=new ImageIcon("src/resources/Animations/LoadingIcon1/load_"+StoreFilterPanel.loadingState+".png").getImage();
        g.drawImage(backgroundImage,0,0,null);
        //g.setColor(new Color(0.1f,0.1f,0.1f,0.4f));
        //g.fillRect(0,0,100,100);
        //g.setColor(Color.black);
        //g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        this.revalidate();
        this.repaint();
    }
}
