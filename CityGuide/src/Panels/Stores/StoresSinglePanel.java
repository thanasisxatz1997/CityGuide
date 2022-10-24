package Panels.Stores;

import javax.swing.*;
import java.awt.*;

public class StoresSinglePanel extends JPanel {

    public JButton button;
    public JLabel label;
    public JTextArea textarea;
    public StoresSinglePanel() {
        this.setPreferredSize(new Dimension(400,150));
        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.black));



        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        label = new JLabel("Store Name");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(label, c);

        label = new JLabel("Rating");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 2;
        c.gridy = 0;
        pane.add(label, c);

        label = new JLabel("Store Picture");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(label, c);

        textarea= new JTextArea("Store Description");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 40;       //make this component tall
        c.gridx = 3;
        c.gridy = 1;
        pane.add(textarea, c);

        button = new JButton("Details");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 2;
        pane.add(button, c);

        button = new JButton("Save");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 3;
        c.gridy = 2;
        pane.add(button, c);
    }

}
