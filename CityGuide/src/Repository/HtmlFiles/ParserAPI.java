package Repository.HtmlFiles;


import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ParserAPI {

    public static void Parse(String strToParse)
    {
        JSONParser jsonParser=new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject) jsonParser.parse(strToParse);
            //JSONObject jsonObject = (JSONObject)jsonParser.parse(new FileReader("P:\\parse.json"));
            //JSONObject jsonObject = (JSONObject)jsonParser.parse( strToParse);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            System.out.println("THE STRING IS: "+strToParse);
            for(int i=0;i<jsonArray.size();i++) {

                Document tempDoc=new Document();

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

                    // Open right now

                    if (jsonObject1.containsKey("opening_hours")) {
                        String opening_hours = jsonObject1.get("opening_hours").toString();
                        System.out.println("Opening hours:" + opening_hours);
                        tempDoc.put("Opening hours", opening_hours);
                    }

                    if (jsonObject1.containsKey("photos")) {
                        String photo = jsonObject1.get("photos").toString();
                        System.out.println("Photos: " + photo);
                        tempDoc.put("Photo", photo);
                    }


                    // Rating
                    if (jsonObject1.containsKey("rating")) {
                        String rating = jsonObject1.get("rating").toString();
                        System.out.println("Rating:" + rating);
                        tempDoc.put("Rating", rating);
                    }

                    if (jsonObject1.containsKey("user_ratings_total")) {
                        String user_ratings_total = jsonObject1.get("user_ratings_total").toString();
                        System.out.println("User ratings total: " + user_ratings_total);
                        tempDoc.put("User ratings total", user_ratings_total );
                    }

                    if (jsonObject1.containsKey("geometry")) {
                        String geometry = jsonObject1.get("geometry").toString();
                        System.out.println("Geometry: " + geometry);
                        tempDoc.put("Geometry", geometry );
                    }

                    if (jsonObject1.containsKey("types")) {
                        String type = jsonObject1.get("types").toString();
                        System.out.println("Types: " + type);
                        tempDoc.put("Types", type );
                    }

                    if (jsonObject1.containsKey("icon")) {
                        String icon = jsonObject1.get("icon").toString();
                        System.out.println("Icon: " + icon);
                        tempDoc.put("Icon", icon );
                    }

                    if (jsonObject1.containsKey("icon_background_color")) {
                        String icon_background_color = jsonObject1.get("icon_background_color").toString();
                        System.out.println("Icon background color: " + icon_background_color);
                        tempDoc.put("Icon background color", icon_background_color );
                    }

                    if (jsonObject1.containsKey("icon_mask_base_uri")) {
                        String icon_mask_base_uri = jsonObject1.get("icon_mask_base_uri").toString();
                        System.out.println("Icon mask base uri: " + icon_mask_base_uri);
                        tempDoc.put("Icon mask base uri", icon_mask_base_uri );
                    }

                    if (jsonObject1.containsKey("plus_code")) {
                        String plus_code = jsonObject1.get("plus_code").toString();
                        System.out.println("Plus code: " + plus_code);
                        tempDoc.put("Plus code", plus_code);
                    }

                    String reference = (String) jsonObject1.get("reference");
                    System.out.println("Reference: " + reference);
                    tempDoc.put("Reference", reference);

                    String scope = (String) jsonObject1.get("scope");
                    System.out.println("Scope: " + scope);
                    tempDoc.put("Scope", scope);

                    // Vicinity Address
                    String vicinity = (String) jsonObject1.get("vicinity");
                    System.out.println("Vicinity: " + vicinity);
                    tempDoc.put("Vicinity", vicinity);

                    //price level ������ error ����� ��� �������� �� ��� �� ������� price_level ��� �� API
                    // double  price_level=(Double)jsonObject1.get("price_level");
                    // System.out.println("price level:"+price_level);
                }
            }
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        } /*catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }


    /*public static void main( String[] args ){

    }

    public static Consumer<? super String> JSONstrList() throws IOException, ParseException {
        List<? super String> list = Collections.singletonList(new ArrayList<JSONObject>());
        return (Consumer<? super String>) ApiCall.ParserAPI.parse((Reader) JSONstrList());
    }*/
}
