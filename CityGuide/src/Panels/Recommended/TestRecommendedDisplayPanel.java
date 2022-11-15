package Panels.Recommended;

import Repository.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

public class TestRecommendedDisplayPanel extends JPanel {
    private TestRoundPanel testRoundPanel;
    private TestRoundButton testRoundButton;
    public TestRecommendedDisplayPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(750,599));
        this.setLayout(new FlowLayout());
        int maxSize=750*599;
        int buttonSize=0;
        int nextSize=120+(int)(Math.random()*((200-120)+1));
        System.out.println("RandomSize:"+nextSize);
        while(buttonSize+nextSize<maxSize-300*300)
        {
            Document storeDoc =DataManager.GetRandomRecommendedStoreTest();
            buttonSize=buttonSize+(nextSize+30)*(nextSize+30);
            TestRectButton testRoundButton=new TestRectButton(180,nextSize);
            testRoundButton.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
            this.add(testRoundButton);
            //TestOvalButton testRoundButton=new TestOvalButton(180,nextSize);
            //testRoundButton.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
            //this.add(testRoundButton);
            //TestRoundButton testRoundButton=new TestRoundButton(nextSize,nextSize);
            //testRoundButton.backgroundImage=DataManager.GetRandomStoreImage(storeDoc);
            //this.add(testRoundButton);
            nextSize=100+(int)(Math.random()*((200-100)+1));
            System.out.println("RandomSize:"+nextSize);
        }
        this.setVisible(true);
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
