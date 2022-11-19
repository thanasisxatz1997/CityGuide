package Panels.TestPanels;

import LogInManager.Managers.DataManager;
import Main.Initialize;
import Repository.CurrentUser;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.bson.Document;
import org.bson.conversions.Bson;

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

import com.kingaspx.util.BrowserUtil; //get license
import com.kingaspx.version.Version; //get license
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.events.TitleEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;


public class TestMapPanel extends JPanel {
    public JFXPanel fxPanel;
    private JPanel mapDisplayPanel;
    private Image backgroundImage;
    private JButton searchButton;
    private JTextField searchTextField;


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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DOMDocument doc = browser.getDocument();

                DOMElement address_element = doc.findElement(By.id("address"));
                DOMElement search_element = doc.findElement(By.id("submit"));
                DOMElement button = (DOMElement) search_element;

                DOMInputElement address = (DOMInputElement) address_element;
                address.setValue(searchTextField.getText());

                button.click();
            }
        });

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
        mapDisplayPanel.setPreferredSize(new Dimension(550,390));
        mapDisplayPanel.setMaximumSize(new Dimension(550,390));
        mapDisplayPanel.setLayout(new BorderLayout());
        mapDisplayPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        c.anchor=GridBagConstraints.NORTH;
        c.fill=GridBagConstraints.BOTH;
        c.insets=new Insets(0,50,118,50);
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
                File f = new File("src/Repository/HtmlFiles/simple_map.html");
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
            BrowserUtil.setVersion(Version.V6_22);

            browser = new Browser();
            view = new BrowserView(browser);

            mapDisplayPanel.add(view, BorderLayout.CENTER);

            browser.addTitleListener((TitleEvent evt) -> {
                //setTitle(evt.getTitle());
            });

            browser.addConsoleListener((ConsoleEvent evt) -> {
                System.out.println("LOG: " + evt.getMessage());
            });

            browser.addLoadListener(new LoadAdapter() {
                @Override
                public void onFinishLoadingFrame(FinishLoadingEvent evt) {
                    evt.getBrowser().setZoomLevel(-2);
                }
            });

            browser.loadURL("src/Repository/HtmlFiles/simple_map.html");
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

    Browser browser;
    BrowserView view;

    /*private void open_site() {
        BrowserUtil.setVersion(Version.V6_22);

        browser = new Browser();
        view = new BrowserView(browser);

        mapDisplayPanel.add(view, BorderLayout.CENTER);

        browser.addTitleListener((TitleEvent evt) -> {
            //setTitle(evt.getTitle());
        });

        browser.addConsoleListener((ConsoleEvent evt) -> {
            System.out.println("LOG: " + evt.getMessage());
        });

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent evt) {
                evt.getBrowser().setZoomLevel(-2);
            }
        });

        browser.loadURL("src/Repository/HtmlFiles/simple_map.html");
    }*/

}
