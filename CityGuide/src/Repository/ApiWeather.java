package Repository;

import org.bson.Document;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiWeather {
    private static String wStr;


    public static void main(String[] args) throws URISyntaxException {



        String API_KEY = "b442186095911e0202dd1a2e35917905";
        String LOCATION = "Rome,IT";
        String LON ="41.941402364287";
        String LAT = "12.463372646697835";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&lat="+LAT+"&lon="+LON+"&appid=" + API_KEY + "";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> wStr = s).join();
        System.out.println("AFTER!");
        System.out.println(wStr);

        Document tempDoc = Document.parse(wStr);

        Document coordDoc = (Document) tempDoc.get("coord");
        System.out.println("lon: "+coordDoc.get("lon"));
        System.out.println(coordDoc.get("lat"));
        System.out.println(tempDoc.get("weather"));
        System.out.println(tempDoc.get("base"));
        Document mainDoc = (Document) tempDoc.get("main");
        double temp= (double) mainDoc.get("temp");
        temp= (double) (temp-273.15);
        System.out.println("temp: "+temp);
        System.out.println(mainDoc.get("feels_like"));
        System.out.println(mainDoc.get("temp_min"));
        System.out.println(mainDoc.get("temp_max"));
        System.out.println(mainDoc.get("pressure"));
        System.out.println(mainDoc.get("humidity"));
        System.out.println(tempDoc.get("visibility"));
        Document windDoc = (Document) tempDoc.get("wind");
        System.out.println(windDoc.get("speed"));
        System.out.println(windDoc.get("deg"));
        Document cloudDoc = (Document) tempDoc.get("clouds");
        System.out.println(cloudDoc.get("all"));
        System.out.println(tempDoc.get("dt"));
        Document sysDoc = (Document) tempDoc.get("sys");
        System.out.println(sysDoc.get("type"));
        System.out.println(sysDoc.get("id"));
        System.out.println(sysDoc.get("country"));
        System.out.println(sysDoc.get("sunrise"));
        System.out.println(sysDoc.get("sunset"));
        System.out.println(tempDoc.get("timezone: "+"timezone"));
        System.out.println(tempDoc.get("id"));
        System.out.println(tempDoc.get("name"));
        System.out.println(tempDoc.get("cod"));
    }
}