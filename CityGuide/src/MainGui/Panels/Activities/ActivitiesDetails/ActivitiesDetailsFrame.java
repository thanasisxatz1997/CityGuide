package MainGui.Panels.Activities.ActivitiesDetails;

import javax.swing.*;
import java.awt.*;

public class ActivitiesDetailsFrame extends JFrame {
    public BackgroundPanel backgroundPanel;
    public String nameStr;
    public ActivitiesDetailsFrame()
    {
        Load();
    }
    private void Load()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(660,670));
        this.setMinimumSize(new Dimension(660,670));
        this.setResizable(false);
        backgroundPanel=new BackgroundPanel();
        this.add(backgroundPanel);
        this.setVisible(true);
    }
    public void ChangeTitle()
    {
        this.setTitle(nameStr);
    }

}
