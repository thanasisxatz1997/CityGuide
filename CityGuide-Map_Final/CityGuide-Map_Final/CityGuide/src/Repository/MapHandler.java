package Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapHandler {
    public static void ClearMapData()
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
                "            infowindow.setContent(locations[i][0]);\n" +
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
