package Panels.Recommended;

import Repository.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

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
        int maxSize=750*599;
        Document storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button1=new TestRectButton(190,220);
        button1.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button1);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRoundButton button2=new TestRoundButton(160,160);
        button2.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button2);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button3=new TestRectButton(250,160);
        button3.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button3);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRoundButton button4=new TestRoundButton(180,180);
        button4.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button4);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestOvalButton button5=new TestOvalButton(250,140);
        button5.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button5);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button6=new TestRectButton(200,160);
        button6.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button6);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button7=new TestRectButton(140,140);
        button7.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button7);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestRectButton button8=new TestRectButton(250,170);
        button8.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button8);

        storeDoc =DataManager.GetRandomRecommendedStoreTest();
        TestOvalButton button9=new TestOvalButton(230,160);
        button9.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
        this.add(button9);
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
