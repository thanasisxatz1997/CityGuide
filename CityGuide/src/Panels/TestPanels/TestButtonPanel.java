package Panels.TestPanels;

import Forms.MainForm;
import Forms.TestMainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestButtonPanel extends JPanel {

    private JButton homeButton;
    private JButton storeButton;
    private JButton favoritesButton;
    private JButton mapButton;
    private Dimension buttonDimension;
    public TestButtonPanel()
    {
        LoadPanel();
    }

    private void LoadPanel()
    {
        this.setPreferredSize(new Dimension(150,600));
        this.setMaximumSize(new Dimension(150,600));
        this.setMinimumSize(new Dimension(150,600));
        this.setBackground(new Color(108,139,218));

        buttonDimension=new Dimension(150,60);

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        homeButton=new JButton();
        storeButton=new JButton();
        favoritesButton=new JButton();
        mapButton=new JButton();

        LoadButtons(homeButton,"Home");
        LoadButtons(storeButton,"Stores");
        LoadButtons(favoritesButton,"Favorites");
        LoadButtons(mapButton,"Map");

        AddButtonListeners();
        this.setVisible(true);
    }

    private void LoadButtons(JButton button,String buttonText)
    {
        button.setText(buttonText);
        SetButtonSize(button);
        button.setFont(new Font("Times Roman",Font.BOLD,20));
        this.add(button);
    }

    private void SetButtonSize(JButton button)
    {
        button.setPreferredSize(buttonDimension);
        button.setMinimumSize(buttonDimension);
        button.setMaximumSize(buttonDimension);
        button.setSize(buttonDimension);
    }

    private void AddButtonListeners()
    {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.removeAll();
                TestMainForm.mainPanel.backgroundPanel.repaint();
                TestMainForm.mainPanel.backgroundPanel.revalidate();
            }
        });

        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TestMainForm.mainPanel.backgroundPanel.AddStorePanel();
            }
        });

        favoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



}
