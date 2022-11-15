package Panels.Recommended;

import Repository.ComponentStyling.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestRectButton extends JButton {
    public Image backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
    private int normalWidth;
    private int normalHeight;
    public TestRectButton( int givenWidth,int givenHeight)
    {
        normalWidth=givenWidth;
        normalHeight=givenHeight;
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
        this.setVisible(true);
    }

    Shape shape;
    @Override
    protected void paintComponent(Graphics g) {
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,60, 60);
        //g.drawRect(0,0,getSize().width - 1, getSize().height - 1);
        //g.setClip(new Ellipse2D.Float(0, 0, getSize().width-1, getSize().height-1));
        g.setClip(new RoundRectangle2D.Float(0,0,getSize().width-1, getSize().height-1,60,60));
        g.drawImage(backgroundImage, 0, 0, getSize().width-1, getSize().height-1, null);
        if (getModel().isArmed()) {
            g.setColor(Color.black);
        }
        else if(getModel().isRollover())
        {
            g.setColor(new Color(1f,1f,1f,.5f));
            System.out.println("SELECTED!");
            g.setFont(LoadFontWithFontSize(16));
            //y: (normalHeight/2)-15+10
            g.fillRoundRect((normalWidth/2)-50+10, (normalHeight/2)-15+10, 80, g.getFont().getSize()+10,10, 10);
            g.setColor(Color.black);
            //y:(normalHeight/2)-15+30
            g.drawString("TEST!",(normalWidth/2)-50+30,(normalHeight/2)-15+30);
            this.setPreferredSize(new Dimension((int) (normalWidth+7), (int) (normalHeight+7)));
            g.setColor(Color.white);
            g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,60, 60);
        }
        else {
            this.setPreferredSize(new Dimension(normalWidth,normalHeight));
            g.setColor(getBackground());
        }
        super.paintComponent(g);
        revalidate();
        repaint();
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,60, 60);
    }

    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

    private Font LoadFontWithFontSize(int fontSize)
    {
        Font customFont;
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD,fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
    }
}
