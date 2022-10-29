package Panels.Activities;



import javax.swing.*;
import java.awt.*;


public class ActivitiesFilterPanel extends JPanel {
    JButton applyFiltersButton;
    JTextField searchTextField;
    JLabel typeLabel;
    JLabel ratingsLabel;
    JComboBox typeComboBox;
    JComboBox ratingsComboBox;
    JLabel openingHoursLabel;
    JComboBox openingHoursComboBox;
    JCheckBox familyFriendlyCheckBox;

    public ActivitiesDisplayPanel connectedActivitiesDisplayPanel;
    public ActivitiesFilterPanel()
    {
        ActivitiesLoadPanel();
    }
    public void SetConnectedActivitiesDisplayPanel(ActivitiesDisplayPanel activitiesDisplayPanel)
    {
        connectedActivitiesDisplayPanel=activitiesDisplayPanel;
    }

    private void ActivitiesLoadPanel()
    {
        this.setPreferredSize(new Dimension(200,400));
        this.setMaximumSize(new Dimension(200,400));
        this.setSize(new Dimension(200,400));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        LoadApplyFiltersButton(c);
        LoadSearchTextField(c);
        LoadTypeLabel(c);
        LoadRatingsLabel(c);
        LoadTypeComboBox(c);
        LoadRatingsComboBox(c);
        LoadOpeningHoursLabel(c);
        LoadOpeningHoursComboBox(c);
        LoadFamilyFriendlyCheckBox(c);


        this.revalidate();
        this.repaint();
        this.setVisible(true);

    }

    private void LoadFamilyFriendlyCheckBox(GridBagConstraints c) {
        familyFriendlyCheckBox=new JCheckBox("Family Friendly");
        familyFriendlyCheckBox.setFont(new Font("Times Roman",Font.BOLD,16));
        c.insets = new Insets(5,10,5,5);
        c.weightx=0.1;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=4;
        this.add(familyFriendlyCheckBox, c);
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

    private void LoadRatingsComboBox(GridBagConstraints c) {
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

    private void LoadTypeComboBox(GridBagConstraints c) {
        typeComboBox=new JComboBox();
        typeComboBox.addItem("");
        typeComboBox.addItem("Museum");
        typeComboBox.addItem("Tourist Attraction");
        typeComboBox.addItem("Festival");
        typeComboBox.addItem("Sports");
        c.insets = new Insets(5,10,5,5);
        c.weightx=0.9;
        c.weighty=0.1;
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=1;
        c.gridy=1;
        this.add(typeComboBox,c);
    }

    private void LoadRatingsLabel(GridBagConstraints c) {
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

    private void LoadTypeLabel(GridBagConstraints c) {
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

    private void LoadSearchTextField(GridBagConstraints c) {
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

    private void LoadApplyFiltersButton(GridBagConstraints c) {
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

}
