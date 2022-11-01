import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;



public class APIinfoPlaces{
    private static String JSONstr;
    private static ArrayList<String> JSONstrList;

    private static HttpURLConnection connection;
    public static void main( String[] args) {
        JSONstrList=new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.9027835%2C12.4963655&radius=4658&type=art_gallery&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(s -> JSONstrList.add(s))
                .thenAccept(ParseAPI.JSONstrList())
                .join();
        //ParseAPI.getstrList();
        System.out.println("AFTER!");
        System.out.println(JSONstrList);
    }



    /*public static <javaIOFileDescriptorAccess> String parse(String responseBody){
        JSONArray results = new JSONArray(responseBody);

        for(int i=0;i<results.size();++1){
            javaIOFileDescriptorAccess jsonArray;
            JSONObject res = (JSONObject) JSONArray.get(1);
            String place_id =res.getstrList("place_id");
            System.out.println(place_id);
        }
        return null;
    }*/


}

// %2C = �� ����
// API KEY = AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes
// ("https://maps.googleapis.com/maps/api/place/nearbysearch/details/json?location=41.088904%2C23.546338&radius=2000&type=coffee&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")
//bowling_alley

