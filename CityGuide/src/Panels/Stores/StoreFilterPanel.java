package Panels.Stores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreFilterPanel extends JPanel {
    JButton applyFiltersButton;
    JTextField searchTextField;
    JLabel searchLabel;
    public StoreDisplayPanel connectedStoreDisplayPanel;
    public StoreFilterPanel()
    {
        LoadPanel();
    }
    public void SetConnectedStoreDisplayPanel(StoreDisplayPanel storeDisplayPanel)
    {
        connectedStoreDisplayPanel=storeDisplayPanel;
    }

    private void LoadPanel()
    {
        this.setPreferredSize(new Dimension(200,600));
        this.setMaximumSize(new Dimension(200,600));
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new GridBagLayout());

        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints c = new GridBagConstraints();



        applyFiltersButton= new JButton("");
        applyFiltersButton.setIcon(new ImageIcon("src/resources/Icons/searchIcon.png"));
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,5,5);  //padding
        c.weightx = 0.1;
        c.weighty =1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(applyFiltersButton, c);

        searchTextField=new JTextField();
        searchTextField.setFont(new Font("Times Roman",Font.BOLD,16));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx = 0.9;
        c.weighty=1;
        c.ipadx=5;
        c.ipady=5;
        c.gridwidth=2;
        c.gridx = 1;
        c.gridy = 0;
        this.add(searchTextField,c);

        applyFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedStoreDisplayPanel.DisplayPanels();
            }
        });

        this.setVisible(true);
    }

}
