package MainGui.Panels.Stores.StoreDetails.StoreOpeningHoursPanels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StoreOpeningHoursDisplayPanel extends JPanel {
    private ArrayList<String> openingHoursList;
    private Font customSmallFont;
    public StoreOpeningHoursDisplayPanel(ArrayList<String> opHStrList)
    {
        openingHoursList=opHStrList;
        customSmallFont=LoadSmallFontWithSize(16);
        Load();
    }
    private void Load()
    {
        System.out.println("OPENING HOURS DISPLAYED!!!");
        this.setPreferredSize(new Dimension(450,200));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        Border emptyBorder=BorderFactory.createEmptyBorder(15,5,5,5);
        Border raisedBorder=BorderFactory.createRaisedBevelBorder();
        this.setBorder(BorderFactory.createCompoundBorder(raisedBorder,emptyBorder));
        DisplayOpeningHours();
        this.setVisible(true);
    }
    private void DisplayOpeningHours()
    {
        if(openingHoursList!=null)
        {
            for(int i=0;i<openingHoursList.size();i++)
            {
                JLabel openingHoursLabel=new JLabel(openingHoursList.get(i));
                openingHoursLabel.setFont(new Font("Serif",Font.BOLD,16));
                this.add(openingHoursLabel);
            }
        }
        else
        {
            JLabel openingHoursLabel=new JLabel("Opening Hours Not available!");
            this.add(openingHoursLabel);
        }
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
