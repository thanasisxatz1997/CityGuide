package MainGui.Panels.Home;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class HomeHintsPanel extends JPanel {

    private Font customSmallFont;
    public static MongoCollection collection;
    private FactsTextPane factsLabel;
    private ArrayList<String> factsArrList=new ArrayList<>();
    public HomeHintsPanel()
    {
        this.setPreferredSize(new Dimension(750,100));
        this.setMaximumSize(new Dimension(750,100));
        this.setMinimumSize(new Dimension(750,100));
        this.setSize(new Dimension(750,100));
        this.setBackground(new Color(0,0,0));
        this.setLayout(new FlowLayout());
        FillFactsArray();
        LoadSmallFont("src/resources/Fonts/CaviarDreams.ttf");
        factsLabel=new FactsTextPane();

        StyledDocument styledDocument=factsLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        styledDocument.setParagraphAttributes(0, styledDocument.getLength(), center, false);


        factsLabel.setBackground(new Color(0,0,0));
        factsLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        factsLabel.setAlignmentX(factsLabel.CENTER_ALIGNMENT);
        factsLabel.setAlignmentY(factsLabel.CENTER_ALIGNMENT);
        factsLabel.setPreferredSize(new Dimension(550,90));
        factsLabel.setFont(customSmallFont);
        factsLabel.setForeground(new Color(255,255,255));
        int factNum= (int) Math.floor(Math.random()*(factsArrList.size()-1+1)+1);
        factsLabel.setText(factsArrList.get(factNum-1));
        this.add(factsLabel);
        this.setVisible(true);
    }

    private void DisplayFact()
    {

    }
    private void FillFactsArray()
    {
        System.out.println("Collection is: "+collection.getNamespace());

        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        ArrayList<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterable = collection.find();
        iterable.into(results);
        System.out.println(results);
        System.out.println("Filling facts now");
        int i=0;
        for (Document doc:iterable) {
            factsArrList.add(doc.get("fact").toString());
            System.out.println("fact: "+factsArrList.get(i));
            i++;
        }
        System.out.println(results);
    }

    private void LoadSmallFont(String path)
    {
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customSmallFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
    }
}
