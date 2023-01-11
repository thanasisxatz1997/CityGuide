package MainGui.Panels.Directions;

import Repository.StringManipulationHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DirectionInfoPanel extends JPanel {
    private Font customFont;
    public JTextField textOrigin;
    public JTextField textDestination;
    DirectionsMainPanel connectedMainPanel;
    JButton submitButton=new JButton();
    JLabel labelOrigin;
    JLabel labelDestination;
    public DirectionInfoPanel()
    {
        load();

    }
    private void load()
    {
        customFont=LoadFontWithFontSize(18);
        this.setBackground(new Color(0,0,0,0));
        this.setPreferredSize(new Dimension(250,570));
        //this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setLayout(null);
        labelOrigin= new JLabel("Origin");
        labelOrigin.setBounds(110, 10, 200, 30);
        textOrigin = new JTextField();
        //set size of the text box
        textOrigin.setBounds(30, 40, 200, 30);
        labelDestination = new JLabel("Destination");
        labelDestination.setBounds(100, 68, 200, 30);
        textDestination = new JTextField();
        textDestination.setBounds(30, 95, 200, 30);
        String s1[] = { "Metro","Train","Bus", "Driving", "Walking" };
        //JLabel labelComboX = new JLabel("Select your Transit ");
        //labelComboX.setBounds(80,125,200,30);
        // create cbox
        JComboBox comboBox=new JComboBox(s1);
        comboBox.setBounds(90, 150,90,20);
        submitButton=new JButton();
        submitButton.setText("GO");
        submitButton.setBounds(30,180,200,30);
        labelOrigin.setFont(customFont);
        labelDestination.setFont(customFont);
        //add elements to the frame
        this.add(comboBox);
        //this.add(labelComboX);
        this.add(labelOrigin);
        this.add(textOrigin);
        this.add(labelDestination);
        this.add(textDestination);
        this.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedMainPanel.directionDisplayPanel.DisplayDirections(StringManipulationHandler.ChangeSpacesToPlus(textOrigin.getText()),StringManipulationHandler.ChangeSpacesToPlus(textDestination.getText()),comboBox.getSelectedItem().toString());

            }
        });
    }
    private Font LoadFontWithFontSize(int fontSize) {
        Font customFont;
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
    }
}