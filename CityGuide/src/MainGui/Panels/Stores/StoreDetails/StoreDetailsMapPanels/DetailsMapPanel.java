package MainGui.Panels.Stores.StoreDetails.StoreDetailsMapPanels;

import MainGui.Panels.Directions.DirectionFrame;
import MainGui.Panels.Map.TestMapPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailsMapPanel extends JPanel {
    private Document storeDoc;
    public JFXPanel fxPanel;
    public WebView webView;
    private JPanel mapDisplayPanel;
    private TestMapPanel testMapPanel;
    private JButton directionsButton;
    public DetailsMapPanel(Document doc)
    {
        this.storeDoc=doc;
        this.testMapPanel = new TestMapPanel();
        //Document geometryDoc=(Document) storeDoc.get("geometry");
        //Document locationDoc=(Document) geometryDoc.get("location");
        //Document locationDoc=(Document) ((Document) storeDoc.get("geometry")).get("location");
        Load();
    }

    private void Load()
    {
        this.setPreferredSize(new Dimension(550, 500));
        this.setMaximumSize(new Dimension(550, 500));
        mapDisplayPanel=new JPanel();
        mapDisplayPanel.setPreferredSize(new Dimension(550,400));
        mapDisplayPanel.setMaximumSize(new Dimension(550,400));
        mapDisplayPanel.setLayout(new BorderLayout());
        mapDisplayPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        this.add(mapDisplayPanel);
        directionsButton=new JButton("Directions");
        directionsButton.setPreferredSize(new Dimension(100,30));
        directionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DirectionFrame directionFrame =new DirectionFrame();
                directionFrame.directionsMainPanel.directionInfoPanel.textDestination.setText(storeDoc.get("vicinity").toString());
                directionFrame.setVisible(true);
            }
        });
        this.add(directionsButton);

        LoadData();
        LoadMap();

        this.setVisible(true);
    }

    private void LoadData(){
        List<TestMapPanel.POI> poiList = new ArrayList<>();
        poiList.add(testMapPanel.DocumentToPOI(this.storeDoc));
        String str_singlelocation = testMapPanel.convertLocationToString(poiList);
        // write locations to html file
        try {
            testMapPanel.writeToHtml(str_singlelocation);

        } catch (IOException eio) {
            eio.printStackTrace();
        }
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
                webView = new WebView();
                System.out.println("Ran again");
                File f = new File("src/Repository/HtmlFiles/mapInfo2.html");
                //Main.class.getClassLoader().getResource("mapInfo2.html").getFile()
                fxPanel.setScene(new Scene(webView));
                webView.getEngine().load(f.toURI().toString());
            });
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
