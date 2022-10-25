package Panels.Stores;

import javax.swing.*;
import java.awt.*;

public class StoresSinglePanel extends JPanel {

    public JButton button;
    public JLabel namelabel;
    public JLabel picturelabel;
    public JLabel ratinglabel;
    public JTextArea textarea;
    public StoresSinglePanel()
    {
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

        namelabel = new JLabel("Activity Name");
        c.fill = GridBagConstraints.HORIZONTAL;
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 0;
        this.add(namelabel, c);

        ratinglabel = new JLabel("rating");
        c.fill = GridBagConstraints.HORIZONTAL;
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 2;
        c.gridy = 0;
        this.add(ratinglabel, c);

        picturelabel = new JLabel("Activity Picture");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 1;
        this.add(picturelabel, c);

        textarea= new JTextArea("Activity Description");
        textarea.setPreferredSize(new Dimension(100,100));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 40;       //make this component tall
        c.gridx = 2;
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
        c.gridx = 2;
        c.gridy = 2;
        this.add(button, c);
    }

}
