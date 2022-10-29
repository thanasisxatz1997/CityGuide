package Panels.Stores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StoreFilterPanel extends JPanel {
    JButton applyFiltersButton;
    JTextField searchTextField;
    JLabel typeLabel;
    JLabel ratingsLabel;
    JComboBox typeComboBox;
    JComboBox ratingsComboBox;
    JLabel openingHoursLabel;
    JComboBox openingHoursComboBox;
    JCheckBox deliveryCheckBox;

    public StoreDisplayPanel connectedStoreDisplayPanel;
    public StoreFilterPanel()
    {
        StoreLoadPanel();
    }
    public void SetConnectedStoreDisplayPanel(StoreDisplayPanel storeDisplayPanel)
    {
        connectedStoreDisplayPanel=storeDisplayPanel;
    }

    private void StoreLoadPanel()
    {
        this.setPreferredSize(new Dimension(200,400));
        this.setMaximumSize(new Dimension(200,400));
        this.setSize(new Dimension(200,400));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        LoadFilterButton(c);
        LoadSearchTextField(c);
        LoadTypeLabel(c);
        LoadRatingsLabel(c);
        LoadTypeComboBox(c);
        LoadRatingsComboBox(c);
        LoadOpeningHoursLabel(c);
        LoadOpeningHoursComboBox(c);
        LoadDeliveryCheckBox(c);
        LoadActionListener();



        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    private void LoadActionListener()
    {
        AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedStoreDisplayPanel.DisplayPanels();
            }
        };
        applyFiltersButton.addActionListener(buttonPressed);
        applyFiltersButton.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "=ENTER_pressed");
        applyFiltersButton.getActionMap().put("=ENTER_pressed",buttonPressed);
        applyFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedStoreDisplayPanel.DisplayPanels();
            }
        });
    }

    private void LoadFilterButton(GridBagConstraints c)
    {
        applyFiltersButton= new JButton("");
        applyFiltersButton.setIcon(new ImageIcon("src/resources/Icons/searchIcon.png"));
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(10,10,5,5);  //padding
        c.weightx = 0.1;
        c.weighty =1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(applyFiltersButton, c);
    }

    private void LoadSearchTextField (GridBagConstraints c)
    {
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
    }
    private void LoadTypeLabel(GridBagConstraints c)
    {
        typeLabel=new JLabel("Type:");
        typeLabel.setFont(new Font("Times Roman",Font.BOLD,16));
        c.weightx=0.9;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(typeLabel,c);

    }
    private void LoadRatingsLabel(GridBagConstraints c)
    {
        ratingsLabel=new JLabel("Rating:");
        ratingsLabel.setFont(new Font("Times Roman",Font.BOLD,16));
        c.weightx=0.1;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(ratingsLabel,c);
    }

    private void LoadTypeComboBox(GridBagConstraints c)
    {
        typeComboBox=new JComboBox();
        typeComboBox.addItem("");
        typeComboBox.addItem("Cafe");
        typeComboBox.addItem("Restaurant");
        typeComboBox.addItem("Market");
        typeComboBox.addItem("Night Life");
        c.insets = new Insets(5,10,5,5);
        c.weightx=0.9;
        c.weighty=0.1;
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=1;
        c.gridy=1;
        this.add(typeComboBox,c);
    }

    private void LoadRatingsComboBox(GridBagConstraints c)
    {
        ratingsComboBox=new JComboBox();
        ratingsComboBox.addItem("");
        ratingsComboBox.addItem("1.1 - 2");
        ratingsComboBox.addItem("2.1 - 3");
        ratingsComboBox.addItem("3.1 - 4");
        ratingsComboBox.addItem("4.1 - 5");
        c.insets = new Insets(5,10,5,5);
        c.weightx=0.9;
        c.weighty=1;
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=1;
        c.gridy=2;
        this.add(ratingsComboBox,c);
    }


    private void LoadOpeningHoursLabel(GridBagConstraints c) {
        openingHoursLabel=new JLabel("Opening" + "\n" + "Hours:");
        openingHoursLabel.setFont(new Font("Times Roman",Font.BOLD,16));
        c.weightx=0.1;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=3;
        this.add(openingHoursLabel,c);

    }

    private void LoadOpeningHoursComboBox(GridBagConstraints c) {
        openingHoursComboBox=new JComboBox();
        openingHoursComboBox.addItem("");
        openingHoursComboBox.addItem("Morning");
        openingHoursComboBox.addItem("Noon");
        openingHoursComboBox.addItem("Afternoon");
        openingHoursComboBox.addItem("Evening");
        openingHoursComboBox.addItem("Midnight");
        c.weightx=0.9;
        c.weighty=1;
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=1;
        c.gridy=3;
        this.add(openingHoursComboBox,c);

    }

    private void LoadDeliveryCheckBox(GridBagConstraints c) {
        deliveryCheckBox=new JCheckBox("Delivery");
        deliveryCheckBox.setFont(new Font("Times Roman",Font.BOLD,16));
        c.insets = new Insets(5,10,5,5);
        c.weightx=0.1;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=4;
        this.add(deliveryCheckBox, c);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }


}
