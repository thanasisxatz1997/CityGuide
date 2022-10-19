package Forms;
import Panels.Stores.StoresSinglePanel;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.RELATIVE;

public class MainForm extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JScrollPane storePane;
    private JScrollPane filterPane;
    private JPanel storePanel;
    private JPanel filterPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea tandemSkydivingIsTheTextArea;
    private JButton detailsButton;
    private JButton button3;
    private JButton button4;
    private JButton button2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JButton saveButton;
    private JButton registerButton;
    private JButton logInButton;
    private JPanel storesDisplayPanel;
    private JPanel storesPanel;
    private JScrollPane storesDisplayPane;
    private JScrollPane storesPane;
    private JPanel storeSinglePanel1;
    private JPanel storeSinglePanel2;
    private JPanel storeSinglePanel3;
    private JPanel storeSinglePanel4;
    private JPanel storeSinglePanel5;
    private JPanel storeSinglePanel6;

    public MainForm()
    {
        this.setTitle("CityGuide");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.add(panel1);
        this.setSize(new Dimension(800,800));

        AddSinglePanels();
    }
    public void AddSinglePanels()
    {
        storeSinglePanel1=new StoresSinglePanel();
        storeSinglePanel2=new StoresSinglePanel();
        storeSinglePanel3=new StoresSinglePanel();
        storeSinglePanel4=new StoresSinglePanel();
        storeSinglePanel5=new StoresSinglePanel();
        storeSinglePanel6=new StoresSinglePanel();


        storesDisplayPane.setPreferredSize(new Dimension(400,0));
        storesDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagConstraints c=new GridBagConstraints();
        storesDisplayPanel.setLayout(new GridBagLayout());

        storesDisplayPanel.setPreferredSize(new Dimension(400,185*17));
        c.anchor=GridBagConstraints.PAGE_START;
        c.fill =GridBagConstraints.BOTH;
        c.gridheight=1;
        c.gridwidth=1;
        c.weightx=0.5;
        c.gridx=0;
        c.gridy=RELATIVE;
        storesDisplayPanel.add(storeSinglePanel1,c);
        storesDisplayPanel.add(storeSinglePanel2,c);
        storesDisplayPanel.add(storeSinglePanel3,c);
        storesDisplayPanel.add(storeSinglePanel4,c);
        storesDisplayPanel.add(storeSinglePanel5,c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);
        storesDisplayPanel.add(new StoresSinglePanel(),c);


        /*c.gridheight=1;
        c.gridwidth=1;
        c.gridx=0;
        c.gridy=0;
        storesDisplayPanel.add(storeSinglePanel1,c);
        c.gridheight=2;
        c.gridwidth=1;
        c.gridx=0;
        c.gridy=1;
        storesDisplayPanel.add(storeSinglePanel2,c);
        c.gridheight=3;
        c.gridy=2;
        storesDisplayPanel.add(storeSinglePanel3,c);
        c.gridheight=4;
        c.gridy=3;
        storesDisplayPanel.add(storeSinglePanel4,c);
        c.gridheight=5;
        c.gridy=4;
        storesDisplayPanel.add(storeSinglePanel5,c);
        c.gridheight=6;
        c.gridy=5;*/
    }


}
