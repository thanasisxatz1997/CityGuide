package Panels.TestPanels;

import Forms.MainForm;
import Forms.TestMainForm;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        button.setIcon(new ImageIcon("src/resources/ButtonIcons/HomeIcon.png"));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        /*button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);*/
        SetButtonSize(button);
        Font buttonFont;
        try {

           InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
           Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
           buttonFont = ttfBase.deriveFont(Font.BOLD,16);
           System.out.println("FONT CREATED");
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setFont(buttonFont);
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
                TestMainForm.mainPanel.backgroundPanel.AddMapPanel();
            }
        });
    }



}
