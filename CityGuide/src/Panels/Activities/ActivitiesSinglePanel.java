package Panels.Activities;

import javax.swing.*;
import java.awt.*;

public class ActivitiesSinglePanel extends JPanel {
    JLabel activityName;
    public JButton button;
    public JLabel label;
    public JTextArea textarea;

    public ActivitiesSinglePanel()
    {
        //activityName= new JLabel();

        this.setPreferredSize(new Dimension(400,150));
        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.setBorder(BorderFactory.createLineBorder(Color.black));



        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        label = new JLabel("Activity Name");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        this.add(label, c);

        label = new JLabel("Rating");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 2;
        c.gridy = 0;
        this.add(label, c);

        label = new JLabel("Activity Picture");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        this.add(label, c);

        textarea= new JTextArea("Activity Description");
        textarea.setPreferredSize(new Dimension(100,100));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 40;       //make this component tall
        c.gridx = 3;
        c.gridy = 1;
        this.add(textarea, c);

        button = new JButton("Details");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 2;
        this.add(button, c);

        button = new JButton("Save");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 3;
        c.gridy = 2;
        this.add(button, c);
    }
}
