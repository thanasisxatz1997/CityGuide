package MainGui.Forms;
import MainGui.Panels.Stores.StoresSinglePanel;
import Repository.Filtering;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainForm extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JScrollPane storePane;
    private JScrollPane filterPane;
    private JPanel storePanel;
    private JPanel activitiesFilterPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea tandemSkydivingIsTheTextArea;
    private JButton detailsButton;
    private JButton button3;
    private JButton button4;
    private JButton button2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField storesSearchTextField;
    private JTextField textField2;
    private JTable table1;
    private JButton saveButton;
    private JButton userButton;
    private JPanel storesDisplayPanel;
    private JPanel storesPanel;
    private JScrollPane storesDisplayPane;
    private JPanel storesFiltersPanel;
    private JButton applyFiltersButton;
    private JScrollPane storesPane;
    private JPanel storeSinglePanel1;
    private JPanel storeSinglePanel2;
    private JPanel storeSinglePanel3;
    private JPanel storeSinglePanel4;
    private JPanel storeSinglePanel5;
    private JPanel storeSinglePanel6;

    public  ArrayList<Document> storesDocList;
    private ArrayList<StoresSinglePanel> singleStorePanelList;
    public static int storePanelHeight=800;

    int listSize;

    public MainForm()
    {
        LoadForm();

    }

    private void LoadForm()
    {
        this.setTitle("CityGuide");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.add(panel1);
        this.setSize(new Dimension(800,800));
        LoadListeners();
        InitializeStoreDisplayPanel();
        InitializeStoreFiltersPanel();
        LoadStoreFilterComboBoxes(); // Placeholder in this class
    }

    private void LoadStoreFilterComboBoxes() // Placeholder in this class
    {
        comboBox3.addItem("");
        comboBox3.addItem("Restaurants");
        comboBox3.addItem("Cafes");
    }

    private void InitializeStoreDisplayPanel()
    {
        storesDisplayPane.setPreferredSize(new Dimension(400,-1));
        storesDisplayPanel.setPreferredSize(new Dimension(400,800));
        storesDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout boxLayout=new BoxLayout(storesDisplayPanel,BoxLayout.Y_AXIS);
        storesDisplayPanel.setLayout(boxLayout);
        storesDisplayPanel.setVisible(true);
    }

    private void InitializeStoreFiltersPanel()
    {
        applyFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanels();
            }
        });
    }

    private void DisplayPanels()
    {
        if(Filtering.CheckSearchedFilters(storesSearchTextField.getText()))
        {
            System.out.println("RestaurantsCheck!");
            storesDocList=new ArrayList<Document>();
            System.out.println("1New list= "+Filtering.FindStoreType(storesSearchTextField.getText()));
            storesDocList.addAll(Filtering.FindStoreType(storesSearchTextField.getText()));

            singleStorePanelList=new ArrayList<StoresSinglePanel>();
            for (Document doc:storesDocList) {
                StoresSinglePanel singleStore =new StoresSinglePanel();
                System.out.println((String)doc.get("name"));
                singleStore.labelName.setText((String) doc.get("name"));
                singleStorePanelList.add(singleStore);
            }
            AddSinglePanels();
        }
        else
        {
            System.out.println("Wrong Search Parameters! Try again");
        }
    }

    private void LoadListeners(){
        LoadActionListeners();
    }

    private void LoadActionListeners(){
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInManager.Managers.Initialize();
            }
        });
    }

    public void AddSinglePanels()
    {
        storesDisplayPanel.removeAll();
        storesDisplayPanel.repaint();
        storesDisplayPanel.revalidate();
        listSize = 0;
        for (StoresSinglePanel singlePanel:singleStorePanelList)
        {
            listSize++;
            System.out.println("size added");
            System.out.println("Size is : "+listSize);
        }
        storesDisplayPanel.setPreferredSize(new Dimension(400,listSize*180+20));
        System.out.println("List is:"+singleStorePanelList);
        for (StoresSinglePanel singlePanel:singleStorePanelList)
        {
            System.out.println("added stores!");
            storesDisplayPanel.add(singlePanel);
        }


        //storesDisplayPane.setPreferredSize(new Dimension(400,800));
        //storesDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //BoxLayout boxLayout=new BoxLayout(storesDisplayPanel,BoxLayout.Y_AXIS);
        //storesDisplayPanel.setLayout(boxLayout);
        /*

        storesDisplayPane.setPreferredSize(new Dimension(400,0));
        storesDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        storesDisplayPanel.setLayout(new GridBagLayout());

        GridBagConstraints c=new GridBagConstraints();
        storesDisplayPanel.setPreferredSize(new Dimension(400,185*17));
        c.anchor=GridBagConstraints.PAGE_START;
        c.fill =GridBagConstraints.BOTH;
        c.gridheight=1;
        c.gridwidth=3;
        c.weightx=1;
        c.weighty=0;
        c.gridx=0;
        c.gridy=RELATIVE;

        */


        //storesDisplayPanel.setVisible(true);

        /*
        storeSinglePanel1=new StoresSinglePanel();
        storeSinglePanel2=new StoresSinglePanel();
        storeSinglePanel3=new StoresSinglePanel();
        storeSinglePanel4=new StoresSinglePanel();
        storeSinglePanel5=new StoresSinglePanel();
        storeSinglePanel6=new StoresSinglePanel();
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
        storesDisplayPanel.add(new StoresSinglePanel(),c); */


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
