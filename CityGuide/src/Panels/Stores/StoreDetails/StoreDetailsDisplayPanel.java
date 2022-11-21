package Panels.Stores.StoreDetails;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StoreDetailsDisplayPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel ratingLabel;
    private JButton saveButton;
    private JButton websiteButton;
    private JScrollPane commentsScrollPane;
    private Font customLargeFont;
    private Font customSmallFont;
    public StoreDetailsDisplayPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(430,460));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new GridBagLayout());
        customLargeFont=LoadLargeFontWithSize(16);
        customSmallFont=LoadSmallFontWithSize(14);
        GridBagConstraints c=new GridBagConstraints();
        AddComponents(c);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
    private void AddComponents(GridBagConstraints c)
    {
        nameLabel=new JLabel("Name Label");
        nameLabel.setFont(customLargeFont);
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=0;
        this.add(nameLabel,c);

        ratingLabel=new JLabel("4.5"+" (671)");
        ratingLabel.setFont(customSmallFont);
        ratingLabel.setIcon(new ImageIcon("src/resources/ButtonIcons/star-icon.png"));
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=2;
        c.gridy=0;
        this.add(ratingLabel,c);

        saveButton=new JButton("Save");
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=4;
        this.add(saveButton,c);

        websiteButton=new JButton("Web");
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=2;
        c.gridy=4;
        this.add(websiteButton,c);

        commentsScrollPane=new JScrollPane();
        commentsScrollPane.setPreferredSize(new Dimension(400,400));
        commentsScrollPane.setBorder(BorderFactory.createRaisedBevelBorder());
        commentsScrollPane.setForeground(new Color(1f,1f,1f,0f));
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=3;
        c.gridheight=3;
        this.add(commentsScrollPane,c);
    }
    public void SetComponentsDetails(String storeName,double ratingsScore,int ratingsCount,String website)
    {
        this.nameLabel.setText(storeName);
        this.ratingLabel.setText(ratingsScore+" "+"("+ratingsCount+")");
    }

    private Font LoadLargeFontWithSize(int fontSize) {
        Font customFont;
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
    }

    private Font LoadSmallFontWithSize(int fontSize)
    {
        Font customFont;
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/CaviarDreams.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD,fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
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
