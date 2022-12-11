package Repository;

import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DriveTransit{

    private static String ptStr;

    public static void main( String[] args) throws ParseException, URISyntaxException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/directions/json?origin=Cavour&destination=Termini&key=AIzaSyBgNG7tFRkbstl6J3tAp0otEwvBpsRsqDc")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> ptStr = s).join();
        System.out.println(ptStr);
    }
}
