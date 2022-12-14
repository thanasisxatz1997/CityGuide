package MainGui.Panels.Stores;

import MainGui.Panels.Recommended.CustomButtons.TestRectButton;
import MainGui.Panels.Stores.StoreDetails.StoreDetailsFrame;
import Repository.DataManager;
import Repository.Filtering;
import org.bson.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreDisplayPanel extends JPanel {
    public static Thread t2;
    //public  ArrayList<Document> storesDocList;
    private ArrayList<StoresSinglePanel> singleStorePanelList;
    public StoreFilterPanel connectedFilterPanel;
    private static String searchedText;
    private JLabel storesLabel;
    private Font customFont;

    StoreDisplayPanel()
    {
        //this.setPreferredSize(new Dimension(250,-1));
        this.setPreferredSize(new Dimension(550,600));

        this.setMaximumSize(new Dimension(550,5000));
        /*button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);*/
        //this.setBackground(new Color(255,178,102));
        Border margin=new EmptyBorder(10,0,10,10);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        Border raisedBorder=this.getBorder();
        this.setBorder(new CompoundBorder(raisedBorder,margin));
        //BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS); //for StorePanels
        //this.setLayout(boxLayout); //for StorePanels
        this.setLayout(new FlowLayout());// for StoreButtons
        this.setPreferredSize(new Dimension(550,this.getPreferredSize().height+3*180));
        LoadFont();
        storesLabel=new JLabel();
        storesLabel.setText("Stores");
        storesLabel.setFont(customFont);
        storesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //this.add(storesLabel);
        this.revalidate();
        this.setVisible(true);
    }
    public void StartAddingStores()
    {
        t2=new Thread(new Runnable() {
            @Override
            public void run() {
                StoreFilterPanel.StartLoadingAnimation();
                AddRandomStores();
                StoreFilterPanel.StopLoadingAnimation();
            }
        });
        t2.start();
    }
    public void SetConnectedStoreFilterPanel(StoreFilterPanel filterPanel)
    {
        connectedFilterPanel=filterPanel;
    }

    public void AddRandomStores()
    {
        for(int i=0;i<10;i++)
        {
            Document storeDoc=DataManager.GetRandomRecommendedStoreTest();
            TestRectButton testRectButton=new TestRectButton(250,180);
            Image backgroundImage=DataManager.GetStoreImage(storeDoc);
            testRectButton.backgroundImage= backgroundImage;
            testRectButton.text= (String) storeDoc.get("name");
            this.add(testRectButton);
            CreateButtonListener(testRectButton,storeDoc,backgroundImage);
        }
    }
    public void AddSinglePanels(ArrayList<Document> storesDocList) {
        this.removeAll();
        this.repaint();
        this.revalidate();
        storesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //this.add(storesLabel);
        int listSize = 0;

        this.setPreferredSize(new Dimension(520, storesDocList.size() * 180 + 20));
        for(int i=0;i<storesDocList.size();i++)
        {
            System.out.println("Added Panel: "+i);
            Document doc=storesDocList.get(i);
            TestRectButton testRectButton=new TestRectButton(240,180);
            Image backgroundImage=DataManager.GetStoreImage(doc);
            testRectButton.backgroundImage=backgroundImage;
            testRectButton.text= doc.get("name").toString();
            testRectButton.setMaximumSize(new Dimension(250,180));
            this.add(testRectButton);
            CreateButtonListener(testRectButton,doc,testRectButton.backgroundImage);
        }

        /*for (StoresSinglePanel singlePanel : singleStorePanelList) {
            listSize++;
            System.out.println("size added");
            System.out.println("Size is : " + listSize);
        }
        this.setPreferredSize(new Dimension(520, listSize * 180 + 20));
        System.out.println("List is:" + singleStorePanelList);
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            System.out.println("added stores!");
            TestRectButton testRectButton=new TestRectButton(240,180);
            testRectButton.backgroundImage=singlePanel.backgroundImage;
            testRectButton.text= String.valueOf(singlePanel.labelName.getText());
            testRectButton.setMaximumSize(new Dimension(250,180));
            this.add(testRectButton);
            //this.add(singlePanel);
        }*/
    }
    private void CreateButtonListener(JButton button,Document finalStoreDoc, Image backgroundImage)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreDetailsFrame storeDetailsFrame=new StoreDetailsFrame();
                storeDetailsFrame.storeDetailsImagePanel.SetBackgroundImage(backgroundImage);
                storeDetailsFrame.storeDetailsImagePanel.storeDetailsDisplayPanel.SetDoc(finalStoreDoc);
                storeDetailsFrame.storeName=finalStoreDoc.get("name").toString();
                storeDetailsFrame.storeDetailsImagePanel.storeDetailsButtonPanel.storeDoc=finalStoreDoc;
                if(finalStoreDoc.get("website")!=null)
                {
                    storeDetailsFrame.storeDetailsImagePanel.storeDetailsButtonPanel.webpageStr=finalStoreDoc.get("website").toString();
                }
                storeDetailsFrame.SetName();
                System.out.println("STORE DETAILS CREATED WITH NAME: "+storeDetailsFrame.storeName);
            }
        });
    }

    void DisplayPanels() //can be called only from classes inside of pachage
    {
        if(Filtering.CheckSearchedFilters(this.connectedFilterPanel.searchTextField.getText()))
        {
            this.removeAll();
            this.repaint();
            this.revalidate();
            if(t2!=null && t2.isAlive())
            {
                t2.interrupt();
            }

            t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    StoreFilterPanel.StartLoadingAnimation();
                    System.out.println("RestaurantsCheck!");
                    ArrayList<Document> storesDocList=new ArrayList<>();
                    //System.out.println("1New list= "+Filtering.FindStoreType(this.connectedFilterPanel.searchTextField.getText()));

                    String searchedType=(String) connectedFilterPanel.typeComboBox.getItemAt(connectedFilterPanel.typeComboBox.getSelectedIndex());
                    String searchedRating=(String) connectedFilterPanel.ratingsComboBox.getItemAt(connectedFilterPanel.ratingsComboBox.getSelectedIndex());
                    String searchedText=Filtering.GeneralizeSearchedType(connectedFilterPanel.searchTextField.getText());
                    //System.out.println("1New list= "+Filtering.FilterStores(searchedType,searchedRating,searchedText));
                    storesDocList.addAll(Filtering.FilterStores(searchedType,searchedRating,searchedText));
                    //storesDocList.addAll(Filtering.FindStoreType(this.connectedFilterPanel.searchTextField.getText()));

                    /*singleStorePanelList=new ArrayList<StoresSinglePanel>();
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
                    }*/
                    AddSinglePanels(storesDocList);
                    StoreFilterPanel.StopLoadingAnimation();
                }
            });
            t2.start();
        }
        else
        {
            System.out.println("SEARCHING BY NAME!");
            String searchedType=(String) connectedFilterPanel.typeComboBox.getItemAt(connectedFilterPanel.typeComboBox.getSelectedIndex());
            String searchedRating=(String) connectedFilterPanel.ratingsComboBox.getItemAt(connectedFilterPanel.ratingsComboBox.getSelectedIndex());
            String searchedText=connectedFilterPanel.searchTextField.getText();
            ArrayList<Document> storesDocList=new ArrayList<>();
            storesDocList.add(Filtering.SearchStoreByName(searchedType,searchedText,searchedRating));
            System.out.println("The store Doe list IN search by name is: "+storesDocList);
            AddSinglePanels(storesDocList);
        }
    }

    private Image GetStoreImage(Document doc)
    {
        if(doc.get("photos")!=null) {
            final String[] photoStr = new String[1];
            System.out.println("Doc is: " + doc);
            photoStr[0] = doc.getList("photos", Map.class).stream().map(map -> photoStr[0] = map.toString()).collect(Collectors.toList()).toString();
            System.out.println("Height: " + photoStr[0]);
            System.out.println("String exists in position: " + photoStr[0].lastIndexOf("height"));
            char[] photoCharArray = photoStr[0].toCharArray();
            int photorefeRenceStrStart = photoStr[0].lastIndexOf("photo_reference=") + 16;
            if (photorefeRenceStrStart != -1) {
                System.out.println("String exists in position: " + photorefeRenceStrStart);
                ArrayList<Character> photoReference = new ArrayList<>();
                for (int i = photorefeRenceStrStart; photoCharArray[i] != ','; i++) {
                    photoReference.add(photoCharArray[i]);
                }
                String photoReferenceStr;
                StringBuilder builder = new StringBuilder(photoReference.size());
                for (Character ch : photoReference) {
                    builder.append(ch);
                }
                photoReferenceStr = builder.toString();

                char[] photoWidthArray = photoStr[0].toCharArray();
                int photoWidthStrStart = photoStr[0].lastIndexOf("width=") + 6;
                ArrayList<Character> photoWidth = new ArrayList<>();
                for (int i = photoWidthStrStart; photoWidthArray[i] != '}' && photoWidthArray[i] != ','; i++) {
                    photoWidth.add(photoWidthArray[i]);
                }
                String photoWidthStr;
                StringBuilder widthBuilder = new StringBuilder(photoWidth.size());
                for (Character ch : photoWidth) {
                    widthBuilder.append(ch);
                }
                photoWidthStr = builder.toString();


                char[] photoHeightArray = photoStr[0].toCharArray();
                int photoHeightStrStart = photoStr[0].lastIndexOf("height=") + 7;
                ArrayList<Character> photoHeight = new ArrayList<>();
                for (int i = photoHeightStrStart; photoHeightArray[i] != ',' && photoHeightArray[i] != '}'; i++) {
                    photoHeight.add(photoHeightArray[i]);
                }
                String photoHeightStr;
                StringBuilder heightBuilder = new StringBuilder(photoHeight.size());
                for (Character ch : photoHeight) {
                    heightBuilder.append(ch);
                }
                photoHeightStr = builder.toString();


                System.out.println("THE PHOTO REFERENCE IS: " + photoReference);
                System.out.println("THE PHOTO WIDTH IS: " + photoWidth);
                System.out.println("THE PHOTO HEIGHT IS: " + photoHeight);
                String lastPhotoWidthStr = "";
                String lastPhotoHeightStr = "";
                for (char c : photoWidth) {
                    lastPhotoWidthStr = lastPhotoWidthStr + c;
                }
                for (char c : photoHeight) {
                    lastPhotoHeightStr = lastPhotoHeightStr + c;
                }

                String photoFullStr = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + photoReferenceStr + "&sensor=false&maxheight=" + lastPhotoHeightStr + "&maxwidth=" + lastPhotoWidthStr + "&key=" + "AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes";
                System.out.println("THE PHOTO STRING IS: " + photoFullStr);
                Image singleStoreImage = new ImageIcon("src/resources/BackgroundImages/colosseum.png").getImage();
                try {
                    URL photoUrl = new URL(photoFullStr);
                    singleStoreImage = ImageIO.read(photoUrl.openStream());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return singleStoreImage;
            }
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

    /*public void AddSinglePanelsOLD() {
        this.removeAll();
        this.repaint();
        this.revalidate();
        storesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //this.add(storesLabel);
        int listSize = 0;
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            listSize++;
            System.out.println("size added");
            System.out.println("Size is : " + listSize);
        }
        this.setPreferredSize(new Dimension(520, listSize * 180 + 20));
        System.out.println("List is:" + singleStorePanelList);
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            System.out.println("added stores!");
            TestRectButton testRectButton=new TestRectButton(240,180);
            testRectButton.backgroundImage=singlePanel.backgroundImage;
            testRectButton.text= String.valueOf(singlePanel.labelName.getText());
            testRectButton.setMaximumSize(new Dimension(250,180));
            this.add(testRectButton);
            //this.add(singlePanel);
        }
    }*/
}
