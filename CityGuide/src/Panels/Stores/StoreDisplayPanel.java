package Panels.Stores;

import Repository.Filtering;
import org.bson.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoreDisplayPanel extends JPanel {
    public  ArrayList<Document> storesDocList;
    private ArrayList<StoresSinglePanel> singleStorePanelList;
    public StoreFilterPanel connectedFilterPanel;
    private static String searchedText;
    private JLabel storesLabel;
    private Font customFont;

    StoreDisplayPanel()
    {
        //this.setPreferredSize(new Dimension(250,-1));
        this.setPreferredSize(new Dimension(280,600));
        this.setMaximumSize(new Dimension(280,5000));
        /*button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);*/

        //this.setBackground(new Color(255,178,102));
        Border margin=new EmptyBorder(10,0,10,10);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        Border raisedBorder=this.getBorder();
        this.setBorder(new CompoundBorder(raisedBorder,margin));
        BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        LoadFont();
        storesLabel=new JLabel();
        storesLabel.setText("Stores");
        storesLabel.setFont(customFont);
        storesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(storesLabel);
        this.revalidate();
        this.setVisible(true);
    }

    public void SetConnectedStoreFilterPanel(StoreFilterPanel filterPanel)
    {
        connectedFilterPanel=filterPanel;
    }

    public void AddSinglePanels() {
        this.removeAll();
        this.repaint();
        this.revalidate();
        storesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(storesLabel);
        int listSize = 0;
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            listSize++;
            System.out.println("size added");
            System.out.println("Size is : " + listSize);
        }
        this.setPreferredSize(new Dimension(280, listSize * 180 + 20));
        System.out.println("List is:" + singleStorePanelList);
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            System.out.println("added stores!");
            this.add(singlePanel);
        }
    }

    void DisplayPanels() //can be called only from classes inside of pachage
    {
        if(Filtering.CheckSearchedFilters(this.connectedFilterPanel.searchTextField.getText()))
        {
            System.out.println("RestaurantsCheck!");
            storesDocList=new ArrayList<Document>();
            //System.out.println("1New list= "+Filtering.FindStoreType(this.connectedFilterPanel.searchTextField.getText()));
            String searchedType=(String) this.connectedFilterPanel.typeComboBox.getItemAt(this.connectedFilterPanel.typeComboBox.getSelectedIndex());
            String searchedRating=(String) this.connectedFilterPanel.ratingsComboBox.getItemAt(this.connectedFilterPanel.ratingsComboBox.getSelectedIndex());
            String searchedText=(String) this.connectedFilterPanel.searchTextField.getText();
            System.out.println("1New list= "+Filtering.FilterStores(searchedType,searchedRating,searchedText));
            storesDocList.addAll(Filtering.FilterStores(searchedType,searchedRating,searchedText));
            //storesDocList.addAll(Filtering.FindStoreType(this.connectedFilterPanel.searchTextField.getText()));

            singleStorePanelList=new ArrayList<StoresSinglePanel>();
            for (Document doc:storesDocList) {
                StoresSinglePanel singleStore =new StoresSinglePanel();

                System.out.println((String)doc.get("name"));
                singleStore.labelName.setText((String) doc.get("name"));
                double storeRating= (double) doc.get("rating");
                singleStore.labelRating.setText(String.valueOf(storeRating));
                if(doc.containsKey("photos"))
                {
                    singleStore.backgroundImage=GetStoreImage(doc);
                }
                singleStorePanelList.add(singleStore);
            }
            AddSinglePanels();
        }
        else
        {
            System.out.println("Wrong Search Parameters! Try again");
        }
    }

    private Image GetStoreImage(Document doc)
    {
        final String[] photoStr = new String[1];
        photoStr[0] = doc.getList("photos", Map.class).stream().map(map -> photoStr[0] =map.toString()).collect(Collectors.toList()).toString();
        System.out.println("Height: "+ photoStr[0]);

        System.out.println("String exists in position: "+ photoStr[0].lastIndexOf("height"));
        char[] photoCharArray=photoStr[0].toCharArray();
        int photorefeRenceStrStart=photoStr[0].lastIndexOf("photo_reference=")+16;
        if(photorefeRenceStrStart!=-1)
        {
            System.out.println("String exists in position: "+ photorefeRenceStrStart);
            ArrayList<Character> photoReference = new ArrayList<>();
            for(int i=photorefeRenceStrStart;photoCharArray[i]!=',';i++)
            {
                photoReference.add(photoCharArray[i]);
            }
            String photoReferenceStr;
            StringBuilder builder = new StringBuilder(photoReference.size());
            for(Character ch: photoReference)
            {
                builder.append(ch);
            }
            photoReferenceStr=builder.toString();
            System.out.println("THE PHOTO REFERENCE IS: "+ photoReference);
            String photoFullStr="https://maps.googleapis.com/maps/api/place/photo?photoreference="+photoReferenceStr+"&sensor=false&maxheight="+"250"+"&maxwidth="+"250"+"&key="+"AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes";
            System.out.println("THE PHOTO STRING IS: "+photoFullStr);
            Image singleStoreImage=null;
            try {
                URL photoUrl=new URL(photoFullStr);
                singleStoreImage= ImageIO.read(photoUrl);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return singleStoreImage;
        }
        return null;
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
