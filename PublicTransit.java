package Repository;
import org.json.simple.parser.ParseException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class PublicTransit {

    public static String ptStr;

    /*public static void main( String[] args) throws ParseException, URISyntaxException {
        String path;
        path=CalculatePath("Battistini0 0167 Roma RM", "Anagnina 00173 Roma RM", "TRANSIT");
        System.out.println(path);
    }*/

    public static String CalculatePath(String startingLocation, String finalLocation, String mode) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/directions/json?origin="+startingLocation+"&destination="+finalLocation+"&mode="+mode+"&key=AIzaSyBgNG7tFRkbstl6J3tAp0otEwvBpsRsqDc")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> ptStr = s).join();
        System.out.println("DONE"+ptStr);

        return ptStr;
    }

}
