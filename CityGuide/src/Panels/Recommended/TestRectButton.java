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
    public String text;
    public Image backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
    private boolean resized;

    private Font customFont;
    private int normalWidth;
    private int normalHeight;
    public TestRectButton( int givenWidth,int givenHeight)
    {
        resized=false;
        normalWidth=givenWidth;
        normalHeight=givenHeight;
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
        System.out.println("Text: "+text);

        this.setVisible(true);
    }

    private void CutText()
    {
        int maxLength=text.length();
        if(text.length()>this.getWidth()/12-5)
        {
            System.out.println("OLD TEXT IS: "+text);
            maxLength=(this.getWidth()/12)-6;
            String newText=text.substring(0,maxLength);
            newText=newText+"...";
            text=newText;
            System.out.println("New length is: "+newText.length());
            resized=true;
        }


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
            if(text!=null&& resized==false)
            {
                CutText();
            }

            g.setColor(new Color(1f,1f,1f,.8f));
            System.out.println("SELECTED!");
            g.setFont(LoadFontWithFontSize(16));
            FontMetrics fontMetrics=g.getFontMetrics(customFont);
            int textWidth=fontMetrics.stringWidth(text);
            //y: (normalHeight/2)-15+10
            g.fillRoundRect((normalWidth/2)-text.length()*12/2, (normalHeight/2)-15+10, textWidth, g.getFont().getSize()+10,10, 10);
            g.setColor(Color.black);
            //y:(normalHeight/2)-15+30
            g.drawString(text,(normalWidth/2)-text.length()*12/2,(normalHeight/2)-15+30);
            System.out.println("str length is: "+text.length()+"text is : "+text);
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
