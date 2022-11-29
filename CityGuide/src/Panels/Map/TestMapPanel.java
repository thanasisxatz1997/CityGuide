package Panels.Map;


import Main.Initialize;
import Panels.TestPanels.TestBackgroundPanel;
import Repository.ConnectToCityGuideDB;
import Repository.ConnectToDatabase;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.geojson.Position;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.bson.Document;

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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.computed;
import static java.util.Arrays.*;
import static com.mongodb.client.model.Projections.include;

public class TestMapPanel extends JPanel {
    public  JFXPanel fxPanel;
    public WebView webView;
    private JPanel mapDisplayPanel;
    private Image backgroundImage;
    private JButton searchButton;
    private JTextField searchTextField;
    private MongoDatabase database;

    private ConnectToCityGuideDB connectToDB;


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

        searchButton=new JButton("");
        searchButton.setIcon(new ImageIcon("src/resources/Icons/searchIcon.png"));
        // add listener to button to handle the click event
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // if user type some keyword to search
                if (searchTextField.getText().length() > 0){
                    // search for a single store
                    List<POI> locations = getDBNameData(searchTextField.getText());

                    // then get locations from db
                    //List<POI> locations = getDBData(searchTextField.getText());

                    // convert locations to formatted string
                    String str_locations = convertLocationToString(locations);

                    // write locations to html file
                    try {
                        writeToHtml(str_locations);

                    } catch (IOException eio) {
                        eio.printStackTrace();
                    }

                }

            }
        });
        c.anchor=GridBagConstraints.LINE_END;
        c.weightx=0.1;
        c.insets=new Insets(50,100,0,0);
        c.gridwidth=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=0;
        this.add(searchButton,c);

        searchTextField=new JTextField();
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

        // connect to database
        connectToDB = new ConnectToCityGuideDB();

        LoadBackgroundImage();
        LoadMap();

        this.setVisible(true);
    }

    private void writeToHtml(String locations) throws IOException
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

        String filename = "src/Repository/HtmlFiles/mapInfo2.html";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
        writer.write("");
        writer.close();
        writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(prefix);
        writer.append(locations);
        writer.append(postfix);

        writer.close();

        // reload map
        TestBackgroundPanel panel = (TestBackgroundPanel)this.getParent();
        panel.AddMapPanel();
    }

    private String convertLocationToString(List<POI> locations)
    {
        String str_result = "";
        for (int i = 0; i < locations.size(); i++) {
            str_result = str_result + locations.get(i)+",";
        }
        return str_result;
    }

    private List<POI> getDBData(String key)
    {
        database= ConnectToDatabase.mainDatabase;
        MongoCollection<Document> collection = database.getCollection(key);
        DistinctIterable<Document> geo = collection.distinct("geometry.location", Document.class);
        List<Document> geo_results = new ArrayList<>();
        geo.into(geo_results);
        // List<Point> locations = new ArrayList<>();
        List<POI> locations = new ArrayList<>();
        for (int i = 0; i < geo_results.size(); i++) {
            Double lat  = (Double)geo_results.get(i).get("lat");
            Double lng = (Double)geo_results.get(i).get("lng");
            // locations.add(new Point(null, new Position(lat, lng)));
            locations.add(new POI(lat, lng));
        }
        //names.into(names_results);
        /*List<POI> locations = new ArrayList<>();
        for(int i = 0; i < geo_results.size(); i++)
        {
            String name = (String)names_results.get(i).get("name");
            Double lat  = (Double)geo_results.get(i).get("lat");
            Double lng = (Double)geo_results.get(i).get("lng");
            locations.add(new POI(name, lat, lng));
        }*/

        /*for (int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i).toString());
        }*/

        //System.out.println(results);
        return locations;
    }
    private List<POI> getDBNameData(String key) {
        database = ConnectToDatabase.mainDatabase;
        MongoCollection<Document> collection = database.getCollection("cafe");
        Bson projectionFields = Projections.fields(Projections.include("name", "geometry.location.lat", "geometry.location.lng"), Projections.excludeId());
        Bson projectionFieldsLat = Projections.fields(Projections.include("geometry.location.lat"), Projections.excludeId());
        Bson projectionFieldsLng = Projections.fields(Projections.include("geometry.location.lng"), Projections.excludeId());
        Document doc = collection.find(eq("name", key)).projection(projectionFields).first();
        Document latitude = collection.find().projection(projectionFieldsLat).first();
        Document longitude = collection.find().projection(projectionFieldsLng).first();

        if (doc == null) {
            System.out.println("No results found.");
        } else {
            System.out.println("found lat lng " + doc.toJson());
            System.out.println("lat " + latitude.toJson());
            System.out.println("lng " + longitude.toJson());
        }

        List<POI> locations = new ArrayList<>();
        Double lat = collection.distinct("geometry.location.lat", eq("name",key), Double.class).first();
        System.out.println("get lat " + lat);
        Double lng = collection.distinct("geometry.location.lng", eq("name",key), Double.class).first();
        System.out.println("get lng " + lng);
        locations.add(new POI(lat, lng));
        return locations;
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

    public class POI {
        private Double lat;
        private Double lng;
        private String name;

        public POI(){
            this.name = "NA";
            this.lat = 0.0;
            this.lng = 0.0;
        }

        public POI(Double lat, Double lng) {
            this.name = "";
            this.lat = lat;
            this.lng = lng;
        }

        public POI(String name, Double lat, Double lng){
            this.name = name;
            this.lat = lat;
            this.lng = lng;
        }

        public String toString() {
            return "['" + this.name + "'," + this.lat + "," + this.lng + "]";
        }
    }
}
