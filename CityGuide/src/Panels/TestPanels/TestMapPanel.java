package Panels.TestPanels;

import Main.Initialize;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;

/*import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;*/

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class TestMapPanel extends JPanel {
    public  JFXPanel fxPanel;
    private JPanel mapDisplayPanel;
    private Image backgroundImage;


    public TestMapPanel()
    {
        Load();

    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(750,600));
        this.setMaximumSize(new Dimension(750,600));
        GridBagConstraints c=new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        c.insets=new Insets(30,50,50,50);

        JButton searchButton=new JButton("");
        searchButton.setIcon(new ImageIcon("src/resources/Icons/searchIcon.png"));
        c.anchor=GridBagConstraints.LINE_END;
        c.weightx=0.1;
        c.insets=new Insets(50,100,0,0);
        c.gridwidth=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=0;
        this.add(searchButton,c);

        JTextField searchTextField=new JTextField();
        c.anchor=GridBagConstraints.LINE_START;
        c.insets=new Insets(50,0,0,100);
        c.weightx=0.9;
        c.ipady=10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=2;
        c.gridy=0;
        c.gridx=1;

        this.add(searchTextField,c);

        mapDisplayPanel=new JPanel();
        mapDisplayPanel.setPreferredSize(new Dimension(550,400));
        mapDisplayPanel.setMaximumSize(new Dimension(550,400));
        mapDisplayPanel.setLayout(new BorderLayout());
        mapDisplayPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        c.anchor=GridBagConstraints.NORTH;
        c.fill=GridBagConstraints.BOTH;
        c.insets=new Insets(0,50,100,50);
        c.weightx=1;
        c.weighty=1;
        c.gridwidth=3;
        c.gridheight=3;
        c.ipady=600;
        c.gridx=0;
        c.gridy=1;
        this.add(mapDisplayPanel,c);



        LoadBackgroundImage();
        LoadMap();

        this.setVisible(true);
    }
    private void LoadMap()
    {
        SwingUtilities.invokeLater(()->{
            fxPanel = new JFXPanel();
            fxPanel.setPreferredSize(new Dimension(550,400));
            fxPanel.setMaximumSize(new Dimension(550,400));
            mapDisplayPanel.add(fxPanel);
            mapDisplayPanel.setVisible(true);
            System.out.println("Here again");
            Platform.setImplicitExit(false);
            Platform.runLater(()->{
                WebView webView = new WebView();
                System.out.println("Ran again");
                File f = new File("src/Repository/HtmlFiles/mapInfo2.html");
                //Main.class.getClassLoader().getResource("mapInfo2.html").getFile()
                fxPanel.setScene(new Scene(webView));
                webView.getEngine().load(f.toURI().toString());
            });
            /*
            Platform.runLater(()->{
                WebView webView = new WebView();
                fxPanel.setScene(new Scene(webView));
                webView.getEngine().load("https://maps.google.com");
            });*/
        });
    }

    private void LoadBackgroundImage()
    {
        Dimension dim= new Dimension();
        backgroundImage=new ImageIcon("src/resources/BackgroundImages/RomeImageResized.jpg").getImage();
        dim.width=backgroundImage.getWidth(null);
        dim.height=backgroundImage.getHeight(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);

        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        this.revalidate();
        this.repaint();
    }
}
