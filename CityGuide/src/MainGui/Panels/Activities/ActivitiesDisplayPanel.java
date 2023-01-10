package MainGui.Panels.Activities;

import MainGui.Panels.Activities.ActivitiesDetails.ActivitiesDetailsFrame;
import MainGui.Panels.Recommended.CustomButtons.TestStraightRectButton;
import Repository.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActivitiesDisplayPanel extends JPanel {

    public ActivitiesDisplayPanel()
    {
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(722,600));
        //this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new FlowLayout());
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                AddActivities();
            }
        });
        t2.start();
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
    private void AddActivities()
    {
        ArrayList<Document> activitiesList=DataManager.GetActivities();
        for(int i=0;i<activitiesList.size();i++)
        {
            this.setPreferredSize(new Dimension(this.getPreferredSize().width,this.getPreferredSize().height+255));
            TestStraightRectButton testStraightRectButton=new TestStraightRectButton(350,255);
            testStraightRectButton.backgroundImage=DataManager.GetActivitiesImage(activitiesList.get(i));
            testStraightRectButton.text= (String) activitiesList.get(i).get("name");
            int j = i;
            testStraightRectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ActivitiesDetailsFrame activitiesDetailsFrame=new ActivitiesDetailsFrame();
                    activitiesDetailsFrame.backgroundPanel.backgroundImage=testStraightRectButton.backgroundImage;
                    activitiesDetailsFrame.backgroundPanel.descriptionStr=(String) activitiesList.get(j).get("description");
                    activitiesDetailsFrame.nameStr=activitiesList.get(j).get("name").toString();
                    activitiesDetailsFrame.ChangeTitle();
                    activitiesDetailsFrame.backgroundPanel.ChangeDescription();
                }
            });
            this.add(testStraightRectButton);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        this.revalidate();
        this.repaint();
    }
}
