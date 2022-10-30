package Repository;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class APIinfoplaces {
	private static String str;
	private static ArrayList<String> strList;
	
	private static HttpURLConnection connection;

	public static void GetInfoFromAPI()
	{
		strList=new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		//Rome Radius= 4568
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.9028%2C12.4964&radius=2000&type=park&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> strList.add(s)).join();
		//System.out::println
		System.out.println("AFTER!");
		System.out.println(strList);
		System.out.println(strList.size());
	}

	/*public static void main( String[] args) {
		strList=new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		//Rome Radius= 4568
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.9028%2C12.4964&radius=2000&type=park&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> strList.add(s)).join();
		//System.out::println
		System.out.println("AFTER!");
		System.out.println(strList);
		System.out.println(strList.size());
	}*/
}