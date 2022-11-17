package Repository;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;

public class APIinfoplaces{

	public static ArrayList<String> JSONstrList;
	private static String testStr;

	private static HttpURLConnection connection;
	public static void main( String[] args) throws ParseException {

		String coll="night_club";
		JSONstrList=new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.9027835%2C12.4963655&radius=4658&type="+coll+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
						client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
						.thenApply(HttpResponse::body)
						.thenAccept(s -> testStr=s)
						.join();
		System.out.println("AFTER!");
		System.out.println(testStr);
		System.out.println("DONE");
		ParserAPI.Parse(testStr);
	}
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

//