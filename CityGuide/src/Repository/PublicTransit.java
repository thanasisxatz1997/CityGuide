package Repository;

import com.google.gson.Gson;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class PublicTransit {
    static ArrayList<Document> docList = null;
    public static String ptStr;

    public static void main( String[] args) throws ParseException, URISyntaxException {
        String path= new String();
        path=CalculatePath("Battistini+00167+Roma+RM", "Anagnina+00173+Roma+RM", "TRANSIT");
        //System.out.println(path);
        Document doc=Document.parse(path);
        System.out.println("Doc: "+doc);
        System.out.println(PublicTransitParser.GetInstructions(doc));
    }

    public static String CalculatePath(String startingLocation, String finalLcation, String mode) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/directions/json?origin="+startingLocation+"&destination="+finalLcation+"&mode="+mode+"&key=AIzaSyBgNG7tFRkbstl6J3tAp0otEwvBpsRsqDc")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> ptStr = s).join();
        return ptStr;
    }


}