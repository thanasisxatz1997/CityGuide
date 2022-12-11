package Repository;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class ParserAPIDebateable {
    public static ArrayList<Document> Parse(String strToParse) throws RuntimeException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Document> docList = new ArrayList<>();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(strToParse);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            System.out.println("THE STRING IS: " + strToParse);
            for (int i = 0; i < jsonArray.size(); i++) {
                Document tempDoc = new Document();
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                jsonObject1= (JSONObject) jsonObject1.get("result");
                System.out.println("JSONOBJECT NOW IS :::::: "+jsonObject1);

                //  Business Status
                if (jsonObject1.containsKey("business_status")) {
                    tempDoc.put("business_status", jsonObject1.get("business_status"));
                }

                // Name of place
                if (jsonObject1.containsKey("name")) {
                    tempDoc.put("name", jsonObject1.get("name"));
                }

                //place id
                if (jsonObject1.containsKey("place_id")) {
                    tempDoc.put("place_id", jsonObject1.get("place_id"));
                }

                // Icon
                if (jsonObject1.containsKey("icon")) {
                    tempDoc.put("icon", jsonObject1.get("icon"));
                }

                // Icon Background Color
                if (jsonObject1.containsKey("icon_background_color")) {
                    tempDoc.put("icon_background_color", jsonObject1.get("icon_background_color"));
                }

                // Icon Mask Base URI
                if (jsonObject1.containsKey("icon_mask_base_uri")) {
                    tempDoc.put("icon_mask_base_uri", jsonObject1.get("icon_mask_base_uri"));
                }

                // Photo
                if (jsonObject1.containsKey("photos")) {
                    tempDoc.put("photos", jsonObject1.get ("photos"));
                }

                // Open right now
                if (jsonObject1.containsKey("opening_hours")) {
                    tempDoc.put("opening_hours", jsonObject1.get ("opening_hours"));
                }

                // Rating
                if (jsonObject1.containsKey("rating")) {
                    tempDoc.put("rating",jsonObject1.get ("rating"));
                }

                // Users Rating
                if (jsonObject1.containsKey("user_ratings_total")) {
                    tempDoc.put("users_ratings_total",jsonObject1.get ("user_ratings_total"));
                }

                // Preice Level
                if (jsonObject1.containsKey("price_level")) {
                    tempDoc.put("price_level",jsonObject1.get ("price_level"));
                }

                // Type
                if (jsonObject1.containsKey("types")) {
                    tempDoc.put("types", jsonObject1.get("types"));
                }

                //Geometry
                if (jsonObject1.containsKey("geometry")) {
                    tempDoc.put("geometry",jsonObject1.get("geometry"));
                }

                // Plus Code
                if (jsonObject1.containsKey("plus_code")) {
                    tempDoc.put("plus_code",jsonObject1.get ("plus_code"));
                }

                // Scope
                if (jsonObject1.containsKey("scope")) {
                    tempDoc.put("Scope", jsonObject1.get("scope"));
                }
                // Reference
                if (jsonObject1.containsKey("reference")) {
                    tempDoc.put("reference", jsonObject1.get("reference"));
                }

                // Vicinity Address
                if (jsonObject1.containsKey("vicinity")) {
                    tempDoc.put("vicinity", jsonObject1.get("vicinity"));
                }

                //address_components
                if (jsonObject1.containsKey("address_components")) {
                    tempDoc.put("address_components", jsonObject1.get("address_components"));
                }

                //adr_address
                if (jsonObject1.containsKey("adr_address")) {
                    tempDoc.put("adr_address", jsonObject1.get("adr_address"));
                }

                //formatted_address
                if (jsonObject1.containsKey("formatted_address")) {
                    tempDoc.put("formatted_address", jsonObject1.get("formatted_address"));
                }

                //formatted_phone_number
                if (jsonObject1.containsKey("formatted_phone_number")) {
                    tempDoc.put("formatted_phone_number", jsonObject1.get("formatted_phone_number"));
                }

                //reviews
                if (jsonObject1.containsKey("reviews")) {
                    tempDoc.put("reviews", jsonObject1.get("reviews"));
                }

                //utc_offset
                if (jsonObject1.containsKey("utc_offset")) {
                    tempDoc.put("utc_offset", jsonObject1.get("utc_offset"));
                }

                //website
                if (jsonObject1.containsKey("website")) {
                    tempDoc.put("website", jsonObject1.get("website"));
                }
                System.out.println("DOC HERE IS::::::::::::::::::::::::::::: "+tempDoc);
                docList.add(tempDoc);
                System.out.println(docList);
            }
            System.out.println("final: "+ docList);
            return docList;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}