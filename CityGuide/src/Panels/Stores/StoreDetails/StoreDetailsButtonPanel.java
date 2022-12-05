package Panels.Stores.StoreDetails;

import Repository.CurrentUser;
import Repository.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StoreDetailsButtonPanel extends JPanel {
    private Document storeDoc;
    public StoreDetailsImagePanel connectedStoreDetailsImagePanel;
    private JButton saveButton;
    private JButton websiteButton;
    private JButton photosButton;
    private JButton reviewsButton;
    private JButton openHoursButton;
    private JLabel ratingLabel;
    private Font customSmallFont;
    public StoreDetailsButtonPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(440,35));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        LoadDetailsButtonPanel();
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
    private void LoadDetailsButtonPanel()
    {
        customSmallFont=LoadSmallFontWithSize(16);
        LoadSaveButton();
        LoadWebsiteButton();
        LoadPhotosButton();
        LoadReviewsButton();
        LoadOpenHoursButton();
        LoadRatingsLabel();
    }
    private void LoadSaveButton()
    {
        saveButton=new JButton("");
        saveButton.setIcon(new ImageIcon("src/resources/ButtonIcons/Save-icon.png"));
        saveButton.setPreferredSize(new Dimension(35,35));
        this.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentUser.IsLoggedIn())
                {
                    DataManager.AddStoreToFavourites(storeDoc);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please log in for more features!","Not Logged In!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void LoadWebsiteButton()
    {
        websiteButton=new JButton("");
        websiteButton.setIcon(new ImageIcon("src/resources/ButtonIcons/world-wide-web.png"));
        websiteButton.setPreferredSize(new Dimension(35,35));
        this.add(websiteButton);
    }

    private void LoadPhotosButton()
    {
        photosButton=new JButton("");
        photosButton.setIcon(new ImageIcon("src/resources/ButtonIcons/photo-gallery.png"));
        photosButton.setPreferredSize(new Dimension(35,35));
        this.add(photosButton);
        photosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedStoreDetailsImagePanel.storeDetailsDisplayPanel.removeAll();
                connectedStoreDetailsImagePanel.storeDetailsDisplayPanel.LoadPhotosPanel();
            }
        });
    }

    private void LoadReviewsButton()
    {
        reviewsButton=new JButton("");
        reviewsButton.setIcon(new ImageIcon("src/resources/ButtonIcons/comment.png"));
        reviewsButton.setPreferredSize(new Dimension(35,35));
        this.add(reviewsButton);
        reviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedStoreDetailsImagePanel.storeDetailsDisplayPanel.removeAll();
                connectedStoreDetailsImagePanel.storeDetailsDisplayPanel.LoadReviewsPanel();
            }
        });
    }

    private void LoadOpenHoursButton()
    {
        openHoursButton=new JButton("");
        openHoursButton.setIcon(new ImageIcon("src/resources/ButtonIcons/opening-hours.png"));
        openHoursButton.setPreferredSize(new Dimension(35,35));
        this.add(openHoursButton);
        openHoursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedStoreDetailsImagePanel.storeDetailsDisplayPanel.removeAll();
                connectedStoreDetailsImagePanel.storeDetailsDisplayPanel.LoadOpeningHoursPanel();
            }
        });
    }

    private void LoadRatingsLabel()
    {
        ratingLabel=new JLabel("4.5"+" (671)");
        ratingLabel.setFont(customSmallFont);
        ratingLabel.setIcon(new ImageIcon("src/resources/ButtonIcons/star-icon.png"));
        this.add(ratingLabel);
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

    public void SetComponentsDetails(double ratingsScore,int ratingsCount,String website)
    {
        this.ratingLabel.setText(ratingsScore+" "+"("+ratingsCount+")");
    }

    public void SetDoc(Document doc)
    {
        this.storeDoc=doc;
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
