import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.function.Consumer;


public class ApiCall{
    public static JSONParser ParserAPI;

    public static ArrayList<String> JSONstrList;

    private static HttpURLConnection connection;
    public static void main( String[] args) throws ParseException {
        JSONstrList=new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.9027835%2C12.4963655&radius=4658&type=art_gallery&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(s -> JSONstrList.add(s))
                .thenAccept((Consumer<? super String>) ParserAPI.parse(String.valueOf(JSONstrList)))
                .join();
        //ParseAPI.getstrList();
        System.out.println("AFTER!");
        System.out.println(JSONstrList);
    }
}