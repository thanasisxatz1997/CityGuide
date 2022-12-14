package MainGui.Panels.TestPanels;

import MainGui.Forms.TestMainForm;
import MainGui.Panels.Stores.StoreDisplayPanel;
import MainGui.Panels.Stores.StoreFilterPanel;
import Repository.MapHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private JButton activitiesButton;


    private JButton tipsButton;
    private Dimension buttonDimension;
    private ApplicationButtonPanel applicationButtonPanel;

    public TestButtonPanel() {
        LoadPanel();
    }

    private void LoadPanel() {
        this.setPreferredSize(new Dimension(150, 600));
        this.setMaximumSize(new Dimension(150, 600));
        this.setMinimumSize(new Dimension(150, 600));
        this.setBackground(new Color(108, 139, 218));

        buttonDimension = new Dimension(150, 66);

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        homeButton = new JButton();
        storeButton = new JButton();
        favoritesButton = new JButton();
        mapButton = new JButton();
        recommendedButton = new JButton();
        userButton = new JButton();
        exitButton = new JButton();
        tipsButton = new JButton();
        activitiesButton = new JButton();


        LoadButtons(homeButton, "Home", "src/resources/ButtonIcons/HomeIcon.png");
        LoadButtons(storeButton, "Stores", "src/resources/ButtonIcons/storeIcon32px.png");
        LoadButtons(activitiesButton, "ACTIVITIES", "src/resources/ButtonIcons/attractions32px.png");
        LoadButtons(favoritesButton, "Favorites", "src/resources/ButtonIcons/favoritesIcon32px.png");
        LoadButtons(mapButton, "Map", "src/resources/ButtonIcons/mapIcon32px.png");
        LoadButtons(recommendedButton, "Recommended", "src/resources/Icons/recomendedIcon.png");
        recommendedButton.setFont(LoadFontWithFontSize(12));

        LoadButtons(tipsButton, "TIPS", "src/resources/ButtonIcons/TipsIcon.png");
        LoadButtons(userButton, "USER", "src/resources/ButtonIcons/userIcon32px.png");
        LoadButtons(exitButton, "EXIT", "src/resources/ButtonIcons/exitIcon32px.png");

        AddButtonListeners();
        this.setVisible(true);
    }

    private void LoadButtons(JButton button, String buttonText, String IconPath) {
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
            InputStream myStream = new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            buttonFont = ttfBase.deriveFont(Font.BOLD, 16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setFont(buttonFont);
        this.add(button);
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

    private void SetButtonSize(JButton button) {
        button.setPreferredSize(buttonDimension);
        button.setMinimumSize(buttonDimension);
        button.setMaximumSize(buttonDimension);
        button.setSize(buttonDimension);
    }

    private void AddButtonListeners() {
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
                if(StoreDisplayPanel.t2!=null &&StoreDisplayPanel.t2.isAlive())
                {
                    StoreDisplayPanel.t2.interrupt();
                }
                if(StoreFilterPanel.loadingAnimationTimer!=null)
                {
                    StoreFilterPanel.StopLoadingAnimation();
                }
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
                MapHandler.ClearMapData();
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
                MapHandler.ClearMapData();
                ArrayList<Frame> frameArrayList = new ArrayList<>();
                frameArrayList.addAll(List.of(Frame.getFrames()));
                for (Frame f : frameArrayList) {
                    f.dispose();
                }
                System.exit(0);
            }
        });
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.AddUserPanel();
            }
        });
        tipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.AddTipsPanel();
            }
        });
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestMainForm.mainPanel.backgroundPanel.AddActivitiesPanel();
            }
        });


    }
}
