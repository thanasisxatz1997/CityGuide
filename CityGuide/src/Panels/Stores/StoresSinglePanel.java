package Panels.Stores;

import javax.swing.*;
import java.awt.*;

public class StoresSinglePanel extends JPanel{
    JPanel activityName;

    public  StoresSinglePanel() {
        activityName = new JPanel();
        JButton button;
        JLabel label;
        JTextArea textarea;
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

        label = new JLabel("Activity Picture");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        this.add(label, c);

        textarea= new JTextArea("Activity Description");
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
