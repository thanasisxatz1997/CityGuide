package Panels.TestPanels;

import Forms.MainForm;
import Forms.TestMainForm;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestButtonPanel extends JPanel {

    private JButton homeButton;
    private JButton storeButton;
    private JButton favoritesButton;
    private JButton mapButton;
    private JButton recommendedButton;
    private JButton userButton;
    private JButton exitButton;
    private Dimension buttonDimension;
    private ApplicationButtonPanel applicationButtonPanel;
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

        buttonDimension=new Dimension(150,66);

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        BoxLayout boxLayout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        homeButton=new JButton();
        storeButton=new JButton();
        favoritesButton=new JButton();
        mapButton=new JButton();
        recommendedButton=new JButton();
        userButton=new JButton();
        exitButton=new JButton();

        LoadButtons(homeButton,"Home","src/resources/ButtonIcons/HomeIcon.png");
        LoadButtons(storeButton,"Stores","src/resources/ButtonIcons/storeIcon32px.png");
        LoadButtons(favoritesButton,"Favorites","src/resources/ButtonIcons/favoritesIcon32px.png");
        LoadButtons(mapButton,"Map","src/resources/ButtonIcons/mapIcon32px.png");
        LoadButtons(recommendedButton,"Recommended","src/resources/Icons/recomendedIcon.png");
        recommendedButton.setFont(LoadFontWithFontSize(12));
        LoadButtons(new JButton(),"","");
        LoadButtons(new JButton(),"","");
        LoadButtons(userButton,"USER","src/resources/ButtonIcons/userIcon32px.png");
        LoadButtons(exitButton,"EXIT","src/resources/ButtonIcons/exitIcon32px.png");
        AddButtonListeners();
        this.setVisible(true);
    }

    private void LoadButtons(JButton button,String buttonText,String IconPath)
    {
        button.setText(buttonText);
        button.setIcon(new ImageIcon(IconPath));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        /*button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);*/
        SetButtonSize(button);
        Font buttonFont;
        button.setBackground(new Color(208, 184, 168));
        try {
           InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
           Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
           buttonFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setFont(buttonFont);
        this.add(button);
    }

    private Font LoadFontWithFontSize(int fontSize)
    {
        Font customFont;
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD,fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
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
                TestMainForm.mainPanel.backgroundPanel.LoadHintsPanel();
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
                TestMainForm.mainPanel.backgroundPanel.AddFavouritesPanel();
            }
        });

        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.AddMapPanel();
            }
        });
        recommendedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.AddRecommendedPanel();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMapData();
                ArrayList<Frame> frameArrayList=new ArrayList<>();
                frameArrayList.addAll(List.of(Frame.getFrames()));
                for (Frame f:frameArrayList) {
                    f.dispose();
                }
            }
        });
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.AddUserPanel();
            }
        });
    }

    private void clearMapData()
    {
        String prefix = "<!DOCTYPE html>\n" +
                "<html><body style=\"padding:0; margin:0;\">\n" +
                "\n" +
                "<div id=\"googleMap\" style=\"width:100%;height:390px; position=relative; left:0px; top:0px; padding:0; margin:0;\" ></div>\n" +
                "\n" +
                "<script>\n" +
                "function myMap() {\n" +
                "\n" +
                "var locations = [";

        String postfix = "];\n" +
                "\n" +
                "var mapProp= {\n" +
                "  center:new google.maps.LatLng(41.90,12.49),\n" +
                "  zoom:10.5,\n" +
                "};\n" +
                "\n" +
                "var map = new google.maps.Map(document.getElementById(\"googleMap\"),mapProp);\n" +
                "var infowindow = new google.maps.InfoWindow();\n" +
                "var marker, i;\n" +
                "\n" +
                "for (i=0; i<locations.length; i++) {\n" +
                "    marker = new google.maps.Marker({\n" +
                "        position: new google.maps.LatLng(locations[i][1], locations[i][2]), map: map });\n" +
                "\n" +
                "    google.maps.event.addListener(marker, 'click', (function(marker, i) {\n" +
                "        return function() {\n" +
                "            infowindow.setContent(locations[i][0]);\n" +
                "            infowindow.open(map, marker);\n" +
                "        }\n" +
                "    })(marker, i));\n" +
                "}\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyArkQ61IThme_qefwNNMustbwQ3Ms9kalg&callback=myMap\"></script>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        String locations = "";
        String filename = "src/Repository/HtmlFiles/mapInfo2.html";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
            writer.write("");
            writer.close();
            writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(prefix);
            writer.append(locations);
            writer.append(postfix);

            writer.close();
        } catch (IOException eio){
            eio.printStackTrace();
        }
    }

}
