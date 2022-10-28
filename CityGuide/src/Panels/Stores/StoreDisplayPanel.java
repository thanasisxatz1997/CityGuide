package Panels.Stores;

import Repository.Filtering;
import org.bson.Document;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class StoreDisplayPanel extends JPanel {
    public  ArrayList<Document> storesDocList;
    private ArrayList<StoresSinglePanel> singleStorePanelList;
    public StoreFilterPanel connectedFilterPanel;
    private static String searchedText;

    StoreDisplayPanel()
    {
        //this.setPreferredSize(new Dimension(250,-1));
        this.setPreferredSize(new Dimension(280,600));
        this.setMaximumSize(new Dimension(280,5000));
        Border margin=new EmptyBorder(10,0,10,10);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        Border raisedBorder=this.getBorder();
        this.setBorder(new CompoundBorder(raisedBorder,margin));
        BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        this.setVisible(true);
    }

    public void SetConnectedStoreFilterPanel(StoreFilterPanel filterPanel)
    {
        connectedFilterPanel=filterPanel;
    }

    public void AddSinglePanels() {
        this.removeAll();
        this.repaint();
        this.revalidate();
        int listSize = 0;
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            listSize++;
            System.out.println("size added");
            System.out.println("Size is : " + listSize);
        }
        this.setPreferredSize(new Dimension(280, listSize * 180 + 20));
        System.out.println("List is:" + singleStorePanelList);
        for (StoresSinglePanel singlePanel : singleStorePanelList) {
            System.out.println("added stores!");
            this.add(singlePanel);
        }
    }
    void DisplayPanels() //can be called only from classes inside of pachage
    {
        if(Filtering.CheckSearchedFilters(this.connectedFilterPanel.searchTextField.getText()))
        {
            System.out.println("RestaurantsCheck!");
            storesDocList=new ArrayList<Document>();
            System.out.println("1New list= "+Filtering.FindStoreType(this.connectedFilterPanel.searchTextField.getText()));
            storesDocList.addAll(Filtering.FindStoreType(this.connectedFilterPanel.searchTextField.getText()));

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
}
