package Repository;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;




public class ParserAPI {
    public static ArrayList<String> strList;
    public static MongoCollection dbCollection;
    public static   MongoDatabase database;

    public static void Parse(String strToParse) {
        strList=new ArrayList<>();
        JSONParser jsonParser=new JSONParser();
        Document tempDoc = new Document();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(strToParse);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            System.out.println("THE STRING IS: " + strToParse);
            ArrayList<Document> docList=new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                //  Business Status
                String business_status = (String) jsonObject1.get("business_status");

                // See if Business is Operational
                if (business_status.equals("OPERATIONAL")) {
                    System.out.println("Business status:" + business_status);
                    tempDoc.put("Business status", business_status);

                    // Name of place
                    String name = (String) jsonObject1.get("name");
                    System.out.println("Name: " + name);
                    tempDoc.put("Name", name);

                    //place id
                    String placeId = (String) jsonObject1.get("place_id");
                    System.out.println("Place ID: " + placeId);
                    tempDoc.put("Place ID", placeId);

                    // Icon
                    String icon = (String) jsonObject1.get("icon");
                    System.out.println("Icon: " + icon);
                    tempDoc.put("Icon", icon);

                    // Icon Background Color
                    String icon_background_color = (String) jsonObject1.get("icon_background_color");
                    System.out.println("icon_background_color: " + icon_background_color);
                    tempDoc.put("icon_background_color", icon_background_color);

                    // Icon Mask Base URI
                    String icon_mask_base_uri = (String) jsonObject1.get("icon_mask_base_uri");
                    System.out.println("icon_mask_base_uri: " + icon_mask_base_uri);
                    tempDoc.put("icon_mask_base_uri", icon_mask_base_uri);

                    // Photo
                    if (jsonObject1.containsKey("photos")) {
                        String photo = jsonObject1.get("photos").toString();
                        System.out.println("Photos: " + photo);
                        tempDoc.put("Photos", photo);
                    }

                    // Open right now
                    if (jsonObject1.containsKey("opening_hours")) {
                        String opening_hours = jsonObject1.get("opening_hours").toString();
                        System.out.println("Opening hours:" + opening_hours);
                        tempDoc.put("Opening hours", opening_hours);
                    }

                    // Rating
                    if (jsonObject1.containsKey("rating")) {
                        Double rating = (Double) jsonObject1.get("rating");
                        System.out.println("Rating:" + rating);
                        tempDoc.put("Rating", rating);
                    }

                    // Users Rating
                    if (jsonObject1.containsKey("user_ratings_total")) {
                        String user_ratings_total = (String) jsonObject1.get("user_ratings_total").toString();
                        System.out.println("users_ratings_total:" + user_ratings_total);
                        tempDoc.put("users_ratings_total", user_ratings_total);
                    }

                    // Preice Level
                    if (jsonObject1.containsKey("price_level")) {
                        String price_level =(String) jsonObject1.get("price_level").toString();
                        System.out.println("price_level:" + price_level);
                        tempDoc.put("price_level", price_level);
                    }

                    // Type
                    if (jsonObject1.containsKey("types")) {
                        String type = jsonObject1.get("types").toString();
                        System.out.println("Types: " + type);
                        tempDoc.put("Type", type);
                    }

                    //Geometry
                    if (jsonObject1.containsKey("geometry")) {
                        //JSONObject geometry = (JSONObject) jsonObject1.get("geometry");
                        //System.out.println("Geometry: " + geometry);
                        tempDoc.put("geometry",jsonObject1.get("geometry"));
                    }

                    // Plus Code
                    if (jsonObject1.containsKey("plus_code")) {
                        String plus_code = jsonObject1.get("plus_code").toString();
                        System.out.println("Plus code: " + plus_code);
                        tempDoc.put("Plus code", plus_code);
                    }

                    // Scope
                    String scope = (String) jsonObject1.get("scope");
                    System.out.println("Scope: " + scope);
                    tempDoc.put("Scope", jsonObject1.get("scope"));

                    // Reference
                    String reference = (String) jsonObject1.get("reference");
                    System.out.println("reference: " + reference);
                    tempDoc.put("reference", reference);

                    // Vicinity Address
                    String vicinity = (String) jsonObject1.get("vicinity");
                    System.out.println("Vicinity: " + vicinity);
                    tempDoc.put("Vicinity", jsonObject1.get("vicinity"));

                    System.out.println("DOC IS::::::::::::::::::::::::::::: "+tempDoc);
                    docList.add(tempDoc);
                }
            }
            /*database.listCollectionNames();
            for (String s:database.listCollectionNames()) {
                System.out.println(s);

            }
            dbCollection=database.getCollection("night_club");
            for (Document doc: docList) {
                dbCollection.insertOne(doc);
            }*/

        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}