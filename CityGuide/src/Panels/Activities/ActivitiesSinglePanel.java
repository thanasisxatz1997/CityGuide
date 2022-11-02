package Panels.Activities;

import javax.swing.*;
import java.awt.*;

public class ActivitiesSinglePanel extends JPanel {
    JLabel activityName;
    public JButton buttonSave;
    public JButton buttonDetails;
    public JLabel labelName;
    public JLabel labelPicture;
    public JLabel labelRating;
    public JTextArea textarea;

    public ActivitiesSinglePanel()
    {
        //activityName= new JLabel();

        this.setPreferredSize(new Dimension(400,175));
        this.setMaximumSize(new Dimension(400,175));
        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        labelName = new JLabel("Activity Name");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 0;
        this.add(labelName, c);

        labelRating = new JLabel("Rating");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 2;
        c.gridy = 0;
        this.add(labelRating, c);

        labelPicture = new JLabel("Activity Picture");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 1;
        this.add(labelPicture, c);

        textarea= new JTextArea("Activity Description");
        textarea.setPreferredSize(new Dimension(100,100));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 40;       //make this component tall
        c.gridx = 2;
        c.gridy = 1;
        this.add(textarea, c);

        buttonDetails = new JButton("Details");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 15;
        c.gridx = 0;
        c.gridy = 2;
        this.add(buttonDetails, c);

        buttonSave = new JButton("Save");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 15;
        c.gridx = 2;
        c.gridy = 2;
        this.add(buttonSave, c);
    }

}
