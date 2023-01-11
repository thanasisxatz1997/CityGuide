package MainGui.Panels.Directions;

import Repository.PublicTransit;
import org.eclipse.swt.internal.C;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DirectionDisplayPanel extends JPanel {
    private Font customFont;
    DirectionsMainPanel connectedMainPanel;
    private  static String path;
    private JTextArea textArea;
    private JScrollPane directionDisplayScrollPanel;
    DirectionDisplayPanel()
    {
        load();
    }
    private void load()
    {
        customFont=LoadFontWithFontSize(18);
        this.setBackground(new Color(0,0,0,0));
        this.setPreferredSize(new Dimension(350,570));
        textArea=new JTextArea();
        textArea.setPreferredSize(new Dimension(600,530));
        //this.add(textArea);
        textArea.setVisible(false);
        directionDisplayScrollPanel=new JScrollPane();
        directionDisplayScrollPanel.setPreferredSize(new Dimension(300,520));
        //directionDisplayScrollPanel.setHorizontalScrollBar(null);
        directionDisplayScrollPanel.setVisible(true);
        this.add(directionDisplayScrollPanel);
        directionDisplayScrollPanel.setViewportView(textArea);
    }

    public void DisplayDirections(String origin,String destination,String type)
    {
        GetDirections(origin,destination,type);
        DisplayComponents();
    }
    private void DisplayComponents()
    {
        textArea.setVisible(true);
    }
    private void GetDirections(String origin,String destination,String type)
    {
        String dirStr=PublicTransit.CalculatePath(origin,destination,"");
        textArea.setText(dirStr);
        int count=0;
        for(int i=0;i<dirStr.length();i++)
        {
            if(dirStr.charAt(i) == '\n')
            {
                count++;
            }
        }
        System.out.println("it has "+count+" lines now!");
        textArea.setVisible(true);
        textArea.setPreferredSize(new Dimension(textArea.getWidth(),25*count));
        this.revalidate();
        this.repaint();
    }
    private Font LoadFontWithFontSize(int fontSize) {
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
}