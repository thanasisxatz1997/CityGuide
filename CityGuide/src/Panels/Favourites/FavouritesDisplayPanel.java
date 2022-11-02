package Panels.Favourites;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FavouritesDisplayPanel extends JPanel {
    private FavouritesFilterPanel connectedFavouritesDisplayPanel;
    private Font customFont;
    private JLabel favouritesLabel;

    public FavouritesDisplayPanel()
    {
        Load();
    }

    private void Load()
    {
        this.setPreferredSize(new Dimension(280,600));
        this.setMaximumSize(new Dimension(280,600));

        Border margin=new EmptyBorder(10,0,10,10);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        Border raisedBorder=this.getBorder();
        this.setBorder(new CompoundBorder(raisedBorder,margin));
        BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        LoadFont();

        favouritesLabel=new JLabel("Favourites");
        favouritesLabel.setFont(customFont);
        favouritesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(favouritesLabel);

        this.setVisible(true);
    }

    public void SetConnectedFavouritesFilterPanel( FavouritesFilterPanel favouritesFilterPanel)
    {
        connectedFavouritesDisplayPanel=favouritesFilterPanel;
    }
    private void LoadFont()
    {
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
