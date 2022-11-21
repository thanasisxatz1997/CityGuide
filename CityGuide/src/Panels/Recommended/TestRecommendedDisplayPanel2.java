package Panels.Recommended;

import Panels.Stores.StoreDetails.StoreDetailsFrame;
import Repository.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestRecommendedDisplayPanel2 extends JPanel {
    private TestRoundPanel testRoundPanel;
    private TestRoundButton testRoundButton;
    public TestRecommendedDisplayPanel2()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(750,599));
        this.setLayout(new FlowLayout());
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                AddStores();
            }
        });
        t3.start();
    }

    public void AddStores()
    {
        int maxSize=750*599;
        Document storeDoc;
        storeDoc=DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button1=new TestRectButton(190,220);
        button1.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button1.text= (String) storeDoc.get("name");
        this.add(button1);
        Document finalStoreDoc = storeDoc;
        CreateButtonListener(button1,finalStoreDoc,button1.backgroundImage);


        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRoundButton button2=new TestRoundButton(160,160);
        button2.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button2.text= (String) storeDoc.get("name");
        this.add(button2);
        CreateButtonListener(button2,storeDoc,button2.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button3=new TestRectButton(250,160);
        button3.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button3.text= (String) storeDoc.get("name");
        this.add(button3);
        CreateButtonListener(button3,storeDoc,button3.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRoundButton button4=new TestRoundButton(180,180);
        button4.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button4.text= (String) storeDoc.get("name");
        this.add(button4);
        CreateButtonListener(button4,storeDoc,button4.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestOvalButton button5=new TestOvalButton(250,140);
        button5.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button5.text= (String) storeDoc.get("name");
        this.add(button5);
        CreateButtonListener(button5,storeDoc,button5.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button6=new TestRectButton(200,160);
        button6.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button6.text= (String) storeDoc.get("name");
        this.add(button6);
        CreateButtonListener(button6,storeDoc,button6.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button7=new TestRectButton(140,140);
        button7.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button7.text= (String) storeDoc.get("name");
        this.add(button7);
        CreateButtonListener(button7,storeDoc,button7.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button8=new TestRectButton(250,170);
        button8.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button8.text= (String) storeDoc.get("name");
        this.add(button8);
        CreateButtonListener(button8,storeDoc,button8.backgroundImage);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestOvalButton button9=new TestOvalButton(230,160);
        button9.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        button9.text= (String) storeDoc.get("name");
        this.add(button9);
        CreateButtonListener(button9,storeDoc,button9.backgroundImage);
    }

    private void CreateButtonListener(JButton button,Document finalStoreDoc, Image backgroundImage)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreDetailsFrame storeDetailsFrame=new StoreDetailsFrame();
                storeDetailsFrame.storeDetailsImagePanel.SetBackgroundImage(backgroundImage);
                storeDetailsFrame.storeDetailsImagePanel.storeDetailsDisplayPanel.SetComponentsDetails(finalStoreDoc.get("name").toString(),(double)finalStoreDoc.get("rating"),(int)finalStoreDoc.get("user_ratings_total"),"");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        this.revalidate();
        this.repaint();
    }
}
