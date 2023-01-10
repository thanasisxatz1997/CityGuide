package MainGui.Panels.Activities.ActivitiesDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundPanel extends JPanel {
    public Image backgroundImage=new ImageIcon("src/resources/BackgroundImages/backgroundimagetest.jpg").getImage();
    public String descriptionStr;
    private JTextArea textArea;
    private Font customFont;
    public BackgroundPanel()
    {
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(660,660));
        LoadSmallFont("src/resources/Fonts/CaviarDreams.ttf");
        textArea=new JTextArea();
        textArea.setFont(customFont);
        textArea.setPreferredSize(new Dimension(640,640));
        JScrollPane textScrollPane=new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(630,630));
        this.add(textScrollPane);
        this.setVisible(true);
    }

    private void LoadSmallFont(String path)
    {
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ChangeDescription()
    {
        textArea.setText(descriptionStr);
        textArea.setFont(customFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setClip(new Rectangle2D.Float(0,0,getSize().width-1, getSize().height-1));
        g.drawImage(backgroundImage, 0, 0, getSize().width-1, getSize().height-1, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

        this.revalidate();
        this.repaint();
    }
}
