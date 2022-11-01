import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public class ParseAPI {
    public static void main( String[] args ){
        JSONParser jsonParser=new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new FileReader(".json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            for(int i=0;i<jsonArray.size();i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                //  Business Status
                String business_status = (String) jsonObject1.get("businx`ess_status");

                // See if Business is Operational
                if (business_status.equals("OPERATIONAL")) {
                    System.out.println("Business status:" + business_status);
                    // Name of place
                    String name = (String) jsonObject1.get("name");
                    System.out.println("Name: " + name);

                    //place id
                    String placeId = (String) jsonObject1.get("place_id");
                    System.out.println("Place ID: " + placeId);

                    // Open right now
                    Map<Object, Object> opening_hours = (Map<Object, Object>) jsonObject1.get("opening_hours");
                    opening_hours.forEach((key, value) -> System.out.println(key + ": " + value));

                    // Rating
                    double rating = (double) jsonObject1.get("rating");
                    System.out.println("Rating:" + rating);

                    // Vicinity Address
                    String vicinity = (String) jsonObject1.get("vicinity");
                    System.out.println("Vicinity: " + vicinity);

                    //price level ������ error ����� ��� �������� �� ��� �� ������� price_level ��� �� API
                    // double  price_level=(Double)jsonObject1.get("price_level");
                    // System.out.println("price level:"+price_level);

                }
            }
        }
        catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

   // public static Collection<String> getstrList(){
        //Object JSONObject = new Object();

       // return getstrList().toJSONObject();
        //return JSONObject();
    //}

    public static Consumer<? super String> JSONstrList(){
    }
}