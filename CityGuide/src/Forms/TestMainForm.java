package Forms;

import Panels.TestPanels.TestMainPanel;

import Panels.TestPanels.TestBackgroundPanel;
import Panels.TestPanels.TestMainPanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class TestMainForm extends JFrame {
    public static TestMainPanel mainPanel;

    public TestMainForm()
    {
        LoadForm();
    }
    private void LoadForm()
    {
        this.setTitle("Rome City Guide");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        ImageIcon formIcon=new ImageIcon("src/resources/Icons/colosseumIcon.png");
        this.setIconImage(formIcon.getImage());
        this.setSize(new Dimension(100,800));
        mainPanel=new TestMainPanel();
        this.add(mainPanel);

        this.pack();
        this.setVisible(true);
    }
}


