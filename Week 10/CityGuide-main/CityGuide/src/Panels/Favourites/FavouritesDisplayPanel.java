package Panels.Favourites;

import Panels.Recommended.TestRectButton;
import Panels.Stores.StoreDetails.StoreDetailsFrame;
import Repository.CurrentUser;
import Repository.DataManager;
import org.bson.Document;

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
import java.util.ArrayList;

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
        this.setPreferredSize(new Dimension(550,600));
        this.setMaximumSize(new Dimension(550,600));

        Border margin=new EmptyBorder(10,0,10,10);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        Border raisedBorder=this.getBorder();
        this.setBorder(new CompoundBorder(raisedBorder,margin));
        //BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS);
        //this.setLayout(boxLayout);
        this.setLayout(new FlowLayout());

        LoadFont();

        //favouritesLabel=new JLabel("Favourites");
        //favouritesLabel.setFont(customFont);
        //favouritesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //this.add(favouritesLabel);
        if(CurrentUser.IsLoggedIn())
        {
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    DisplayPanels();
                }
            });
            t2.start();

        }

        this.setVisible(true);
    }

    public void DisplayPanels()
    {
        ArrayList<Document> favouriteDocs=DataManager.GetFavouriteStores();
        for(int i=0;i<favouriteDocs.size();i++)
        {
            Document storeDoc=favouriteDocs.get(i);
            TestRectButton testRectButton=new TestRectButton(250,180);
            testRectButton.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
            testRectButton.text= (String) storeDoc.get("name");
            this.add(testRectButton);
            CreateButtonListener(testRectButton,storeDoc,testRectButton.backgroundImage);
        }
    }
    private void CreateButtonListener(JButton button,Document finalStoreDoc, Image backgroundImage)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreDetailsFrame storeDetailsFrame=new StoreDetailsFrame();
                storeDetailsFrame.storeDetailsImagePanel.SetBackgroundImage(backgroundImage);
            }
        });
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
