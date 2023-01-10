package Panels.Directions;

import Repository.ManipulationString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionInfoPanel extends JPanel {
    DirectionsMainPanel connectedMainPanel;
    JButton submitButton=new JButton();
    public DirectionInfoPanel()
    {
        load();

    }
    private void load()
    {
        this.setPreferredSize(new Dimension(250,650));
        //this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setLayout(null);
        JLabel labelOrigin= new JLabel("Origin");
        labelOrigin.setBounds(110, 10, 200, 30);
        JTextField textOrigin = new JTextField();
        //set size of the text box
        textOrigin.setBounds(30, 40, 200, 30);
        JLabel labelDestination = new JLabel("Destination");
        labelDestination.setBounds(100, 68, 200, 30);
        JTextField textDestination = new JTextField();
        textDestination.setBounds(30, 95, 200, 30);
        String s1[] = { "Metro","Train","Bus", "Driving", "Walking" };
        JLabel labelComboX = new JLabel("Select your Transit ");
        labelComboX.setBounds(80,125,200,30);
        // create cbox
        JComboBox comboBox=new JComboBox(s1);
        comboBox.setBounds(90, 150,90,20);
        submitButton=new JButton();
        submitButton.setText("GO");
        submitButton.setBounds(30,180,200,30);
        //add elements to the frame
        this.add(comboBox);
        this.add(labelComboX);
        this.add(labelOrigin);
        this.add(textOrigin);
        this.add(labelDestination);
        this.add(textDestination);
        this.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedMainPanel.directionDisplayPanel.DisplayDirections(ManipulationString.ChangeSpacesToPlus(textOrigin.getText()),ManipulationString.ChangeSpacesToPlus(textDestination.getText()),comboBox.getSelectedItem().toString());

            }
        });


    }
}
