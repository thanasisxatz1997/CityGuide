package Panels.TestPanels;

import Repository.ConnectToCityGuideDB;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

/*import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;*/

import java.io.File;
import java.util.*;
import java.util.List;


public class TestMapPanel extends JPanel {
    public  JFXPanel fxPanel;
    public WebView webView;
    private JPanel mapDisplayPanel;
    private Image backgroundImage;
    private JButton searchButton;
    private JTextField searchTextField;
    private JComboBox searchBy;

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
                    // List to hold locations
                    List<POI> locations;

                    // get search criteria
                    int choice = searchBy.getSelectedIndex();
                    // index 0: search by category, index 1: search by name
                    if (choice == 0) {
                        // get category locations from db
                        locations = searchByCategory(searchTextField.getText());
                    } else {
                        // get name locations for db
                        locations = searchByName(searchTextField.getText());
                    }

                    // convert locations to formatted string
                    String str_locations = convertLocationToString(locations);

                    // write locations to html file
                    try {
                        writeToHtml(str_locations);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                webView.getEngine().reload();
                            }
                        });

                    } catch (IOException eio) {
                        eio.printStackTrace();
                    }
                }
            }
        });
        c.anchor=GridBagConstraints.LINE_END;
        c.weightx=0.1;
        c.insets=new Insets(50,50,0,0);
        c.gridwidth=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=0;
        this.add(searchButton,c);

        searchTextField=new JTextField();
        c.anchor=GridBagConstraints.LINE_START;
        c.insets=new Insets(50,0,0,210);
        c.weightx=0.5;
        c.ipady=10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=2;
        c.gridy=0;
        c.gridx=1;

        this.add(searchTextField,c);

        String choices[] = {"search by Category", "search by Name"};
        searchBy = new JComboBox(choices);
        c.insets=new Insets(50,435,0,50);
        c.weightx=0.4;
        c.ipady=10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridy=0;
        c.gridx=2;

        this.add(searchBy,c);

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
                "<html lang=\"en\"><head><meta charset=\"utf-8\"></head><body style=\"padding:0; margin:0;\">\n" +
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
                "  zoom:10,\n" +
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
                "            infowindow.setContent(\"<h4>\" + locations[i][0]+ \"</h4>\" + locations[i][4] + \"</br>Rating: \" + locations[i][3]);\n" +
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
        // TestBackgroundPanel panel = (TestBackgroundPanel)this.getParent();
        // panel.AddMapPanel();
    }

    private String convertLocationToString(List<POI> locations)
    {
        String str_result = "";
        for (int i = 0; i < locations.size(); i++) {
            str_result = str_result + locations.get(i)+",";
        }
        return str_result;
    }

    private List<POI> searchByCategory(String key)
    {
        MongoDatabase database = connectToDB.getDatabase();

        MongoCollection<Document> collection = database.getCollection(key);
        List<Document> documents = collection.find().into(new ArrayList<>());

        List<POI> locations = new ArrayList<>();
        for(Document document: documents) {
            Document geo = (Document) document.get("geometry");
            if (geo != null) {
                Document location = (Document) geo.get("location");
                Double lat = (Double) location.get("lat");
                Double lng = (Double) location.get("lng");
                Object rating_obj = document.get("rating");
                String rating = "";
                if (rating_obj == null) {
                    rating = "N/A";
                } else {
                    rating = rating_obj.toString();
                }
                // Double rating = Double.parseDouble(document.getDouble("rating").toString());
                String name = document.getString("name");
                String address = document.getString("vicinity");

                locations.add(new POI(name, lat, lng, rating, address));
            }
        }

        return locations;
    }

    private List<POI> searchByName(String key) {

        MongoDatabase database = connectToDB.getDatabase();

        // get all collection names
        MongoIterable<String> collectionList = database.listCollectionNames();
        // create iterator
        MongoCursor<String> iterator = collectionList.iterator();

        // list to store POI's
        List<POI> locations = new ArrayList<>();

        // iterate all collection names
        while (iterator.hasNext()){
            // get a collection (one by a time)
            MongoCollection<Document> collection = database.getCollection(iterator.next());
            // create index for search by name
            collection.createIndex(Indexes.text("name"));
            // create filter by user input
            Bson filter = Filters.text(key);
            // get relevant documents
            List<Document> documents = collection.find(filter).into(new ArrayList<>());
            for(Document document: documents) {
                Document geo = (Document) document.get("geometry");
                if (geo != null) {
                    Document location = (Document) geo.get("location");
                    Double lat = (Double) location.get("lat");
                    Double lng = (Double) location.get("lng");
                    Object rating_obj = document.get("rating");
                    String rating = "";
                    if (rating_obj == null) {
                        rating = "N/A";
                    } else {
                        rating = rating_obj.toString();
                    }
                    // Double rating = Double.parseDouble(document.getDouble("rating").toString());
                    String name = document.getString("name");
                    String address = document.getString("vicinity");

                    locations.add(new POI(name, lat, lng, rating, address));
                }
            }
        }
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
        private String address;
        private String rating;

        public POI(){
            this.name = "";
            this.lat = 0.0;
            this.lng = 0.0;
            this.rating = "";
            this.address = "";
        }

        public POI(Double lat, Double lng) {
            this.name = "";
            this.lat = lat;
            this.lng = lng;
            this.rating = "";
            this.address = "";
        }

        public POI(String name, Double lat, Double lng){
            this.name = name;
            this.lat = lat;
            this.lng = lng;
        }

        public POI(String name, Double lat, Double lng, String rating, String address){
            this.name = name;
            this.lat = lat;
            this.lng = lng;
            this.rating = rating;
            this.address = address;
        }

        public String toString() {
            return "[\"" + this.name + "\"," + this.lat + "," + this.lng + ",'" + this.rating + "',\"" + this.address + "\"]";
        }
    }
}
