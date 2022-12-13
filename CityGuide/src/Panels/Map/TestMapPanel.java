package Panels.Map;


import Panels.TestPanels.TestBackgroundPanel;
import Repository.ConnectToCityGuideDB;
import Repository.ConnectToDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

public class TestMapPanel extends JPanel {
    public  JFXPanel fxPanel;
    public WebView webView;
    private JPanel mapDisplayPanel;
    private Image backgroundImage;
    private JButton searchButton;
    private JTextField searchTextField;
    private MongoDatabase database;
    private JComboBox cmBox;

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
                if ((String) cmBox.getItemAt(cmBox.getSelectedIndex()) == "Select Store Category:"){
                    JOptionPane.showMessageDialog(null, "You must select a correct Store Category",  "Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    // if user type some keyword to search
                    if (searchTextField.getText().length() == 0){

                        // then get locations from db
                        List<POI> locations = searchByCategory((String) cmBox.getItemAt(cmBox.getSelectedIndex()));

                        // convert locations to formatted string
                        String str_locations = convertLocationToString(locations);

                        // write locations to html file
                        try {
                            writeToHtml(str_locations);
                            // reload map with new markers
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
                    if (searchTextField.getText().length() > 0){
                        // search for a single store
                        List<POI> singlelocation = searchByName(searchTextField.getText(),(String) cmBox.getItemAt(cmBox.getSelectedIndex()));

                        // convert locations to formatted string
                        String str_singlelocation = convertLocationToString(singlelocation);

                        // write locations to html file
                        try {
                            writeToHtml(str_singlelocation);
                            // reload map with new markers
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


            }
        });
        c.anchor=GridBagConstraints.LINE_END;
        c.weightx=0.1;
        c.insets=new Insets(50,0,0,50);
        c.gridwidth=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=2;

        this.add(searchButton,c);

        // add ComboBox
        String[] collectionscmBox = {"Select Store Category:","airport","art_gallery","atm","bakery","bus_station","cafe","casino","church","hospital","library","movie_theater",
                "museum","night_club","park","random_stores","restaurant","shopping_mall","spa","stadium","subway_station","taxi_stand","tourist_attraction","transit_station"};
        cmBox=new JComboBox(collectionscmBox);
        c.anchor=GridBagConstraints.LINE_START;
        c.insets=new Insets(50,50,0,0);
        c.weightx=0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridy=0;
        c.gridx=0;
        c.ipady=10;
        this.add(cmBox,c);

        searchTextField=new JTextField();
        c.anchor=GridBagConstraints.LINE_START;
        c.insets=new Insets(50,0,0,0);
        c.weightx=0.9;
        c.ipady=10;
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
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
        c.gridwidth=4;
        c.gridheight=4;
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

    /**
     * Write markers (POI's) formatted as string, into html file
     * @param locations
     * @throws IOException
     */
    public void writeToHtml(String locations) throws IOException
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
                "<script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBgNG7tFRkbstl6J3tAp0otEwvBpsRsqDc&callback=myMap\"></script>\n" +
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
    }

    /**
     * Converts a list of POI's to String.
     * @param locations
     * @return String
     */
    public String convertLocationToString(List<POI> locations)
    {
        StringBuilder str_result = new StringBuilder();
        for (POI location : locations) {
            str_result.append(location).append(",");
        }
        return str_result.toString();
    }

    /**
     * Search database for POI's only in one collection specified by user input.
     * @param key  user input (category to search for)
     * @return   list of POI's
     */
    private List<POI> searchByCategory(String key)
    {
        // list to store POI's
        List<POI> locations = new ArrayList<>();

        // connect to database
        MongoDatabase database = connectToDB.getDatabase();
        // get collection by user input
        MongoCollection<Document> collection = database.getCollection(key);
        // create empty filter (we need all documents from collection)
        Bson filter = Filters.empty();
        // create projection with fields we need
        Bson projection = fields(include("geometry.location", "rating", "name", "vicinity"), excludeId());
        // get relevant documents
        MongoCursor<Document> cursor = collection.find(filter).projection(projection).cursor();

        // iterate cursor to extract fields
        while (cursor.hasNext()) {
            // for each document
            Document doc = cursor.next();
            // convert document to POI and add it to locations array list
            locations.add(DocumentToPOI(doc));
        }
        return locations;
    }

    public POI DocumentToPOI(Document doc) {
        // POI to return
        POI poi = new POI();
        // get geometry field
        Document geo = (Document) doc.get("geometry");
        // if geometry is not null
        if (geo != null) {
            // get location inner field
            Document location = (Document) geo.get("location");
            // and extract latitude...
            Double lat = (Double) location.get("lat");
            // and longitude
            Double lng = (Double) location.get("lng");
            // then get rating field
            Object rating_obj = doc.get("rating");
            String rating = "";
            // if rating is null
            if (rating_obj == null) {
                // then rating is not available
                rating = "N/A";
            } else {
                // else convert rating to string
                rating = rating_obj.toString();
            }
            // get name of location
            String name = doc.getString("name");
            // and get address
            String address = doc.getString("vicinity");
            poi = new POI(name, lat, lng, rating, address);
        }
        return poi;
    }

    /*private List<POI> searchByName1(String key) {

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
    }*/

    private List<POI> searchByName(String key, String key2) {
        database = ConnectToDatabase.mainDatabase;
        MongoCollection<Document> collection = database.getCollection(key2);
        //MongoCollection<Document> collectioncasino = database.getCollection("casino");
        Bson projectionFields = Projections.fields(Projections.include("name", "geometry.location.lat", "geometry.location.lng","rating","vicinity"), Projections.excludeId());
        Bson projectionFieldsLat = Projections.fields(Projections.include("geometry.location.lat"), Projections.excludeId());
        Bson projectionFieldsLng = Projections.fields(Projections.include("geometry.location.lng"), Projections.excludeId());
        Document doc = collection.find(eq("name", key)).projection(projectionFields).first();
        Document latitude = collection.find().projection(projectionFieldsLat).first();
        Document longitude = collection.find().projection(projectionFieldsLng).first();

        List<POI> singlelocation = new ArrayList<>();
        Double lat = collection.distinct("geometry.location.lat", eq("name",key), Double.class).first();
        Double lng = collection.distinct("geometry.location.lng", eq("name",key), Double.class).first();
/*
        Double named = collection.distinct("name", eq("name",key), Double.class).first();
        String name=String.valueOf(named);
        Double ratingd = collection.distinct("rating", eq("name",key), Double.class).first();
        String rating=String.valueOf(ratingd);
        Double addressd = collection.distinct("vicinity", eq("name",key), Double.class).first();
        String address=String.valueOf(addressd);
*/

        // get all collection names
        MongoIterable<String> collectionList = database.listCollectionNames();
        //MongoIterable<String> collectionList = database.getCollection(key2);
        // create iterator
        MongoCursor<String> iterator = collectionList.iterator();

        while (iterator.hasNext()){
            // get a collection (one by a time)
            MongoCollection<Document> collection1 = database.getCollection(iterator.next());
            // create index for search by name
            collection1.createIndex(Indexes.text("name"));
            // create filter by user input
            Bson filter = Filters.text(key);
            // get relevant documents
            List<Document> documents = collection1.find(filter).into(new ArrayList<>());
            for(Document document: documents) {
                Document geo = (Document) document.get("geometry");
                if (geo != null) {
                    //Document location = (Document) geo.get("location");
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

                    //locations.add(new POI(name, lat, lng, rating, address));
                    singlelocation.add(new POI(name, lat, lng, rating, address));
                }
            }
        }

        //singlelocation.add(new POI(name, lat, lng, rating, address));

        if (doc == null || lat == null || lng == null) {
            System.out.println("No results found.");
            JOptionPane.showMessageDialog(null, "Wrong Store Name!",  "Error",JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println("found store " + doc.toJson());
            System.out.println("get lat " + lat);
            System.out.println("get lng " + lng);
        }

        return singlelocation;
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
