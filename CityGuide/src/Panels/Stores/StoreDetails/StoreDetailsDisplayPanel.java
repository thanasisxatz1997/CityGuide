package Panels.Stores.StoreDetails;

import Panels.Stores.StoreDetails.StoreOpeningHoursPanels.StoreOpeningHoursPanel;
import Panels.Stores.StoreDetails.StorePhotoPanels.StorePhotosPanel;
import Panels.Stores.StoreDetails.StoreReviewsPanels.StoreReviewsPanel;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StoreDetailsDisplayPanel extends JPanel {
    private Document storeDoc;
    public StoreDetailsImagePanel connectedStoreDetailsImagePanel;
    private Font customLargeFont;
    private Font customSmallFont;
    private JPanel photosPanel;
    private StoreReviewsPanel reviewsPanel;
    private StoreOpeningHoursPanel openHoursPanel;

    public StoreDetailsDisplayPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(580,525));
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        customLargeFont=LoadLargeFontWithSize(16);
        customSmallFont=LoadSmallFontWithSize(14);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void LoadReviewsPanel()
    {
        ArrayList<Document> reviewsDocList= (ArrayList<Document>) storeDoc.getList("reviews", Document.class);
        System.out.println("DOC IS::::"+storeDoc);
        System.out.println("REVIEWS ARE:::: "+reviewsDocList);
        reviewsPanel=new StoreReviewsPanel(reviewsDocList);
        this.add(reviewsPanel);
    }

    public void LoadPhotosPanel()
    {
        ArrayList<Document> photosDocList=(ArrayList<Document>) storeDoc.getList("photos", Document.class);
        photosPanel=new StorePhotosPanel(photosDocList);
        this.add(photosPanel);
    }

    public void LoadOpeningHoursPanel()
    {
        ArrayList<String> openingHoursStrList;
        if(storeDoc.containsKey("opening_hours"))
        {
            System.out.println("IT CONTAINS!!!!!!!!");
            Document openingHoursDoc= (Document) storeDoc.get("opening_hours");
            openingHoursStrList = (ArrayList<String>) openingHoursDoc.getList("weekday_text",String.class);
        }
        else
        {
            openingHoursStrList=null;
        }
        openHoursPanel=new StoreOpeningHoursPanel(openingHoursStrList);
        this.add(openHoursPanel);
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

    public void SetDoc(Document doc)
    {
        this.storeDoc=doc;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));

        this.revalidate();
        this.repaint();
    }
}