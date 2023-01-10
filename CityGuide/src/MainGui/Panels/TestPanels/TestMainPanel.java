package MainGui.Panels.TestPanels;

import javax.swing.*;
import java.awt.*;

public class TestMainPanel extends JPanel {

    private TestButtonPanel buttonPanel;
    public static TestBackgroundPanel backgroundPanel;

    public TestMainPanel()
    {
        LoadPanel();
    }
    private void LoadPanel()
    {
        this.setPreferredSize(new Dimension(900,600));
        this.setLayout(new BorderLayout());
        AddPanels();
        this.setVisible(true);
    }
    private void AddPanels()
    {
        buttonPanel=new TestButtonPanel();
        this.add(buttonPanel,BorderLayout.WEST);
        backgroundPanel=new TestBackgroundPanel();
        this.add(backgroundPanel,BorderLayout.EAST);
    }
}
