package Repository;

import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PublicTransit {

    private static String ptStr;

    public static void main( String[] args) throws ParseException, URISyntaxException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://transit.router.hereapi.com/v8/routes?apiKey=TJsk0UpcvOq9K77T3BMByP6QPaZDEkgbLlhr1pNcMF8&origin=41.90134436117869%2C12.499994386842188&destination=41.905954202468685%2C12.415048339633923")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> ptStr = s).join();
        System.out.println(ptStr);
    }
}
