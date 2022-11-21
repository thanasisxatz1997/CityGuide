package Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.parser.ParseException;


import org.json.simple.parser.ParseException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


public class APIinfoplaces{

	public static ArrayList<String> JSONstrList;

	private static String testStr;
	public static MongoDatabase database;
	public static int counter=0;

	private static HttpURLConnection connection;
	public static void main( String[] args) throws ParseException, URISyntaxException {

		new ConnectToDatabase();
		String collName = "restaurant";
		JSONstrList = new ArrayList<>();
		ArrayList<String> regions = new ArrayList<>();
		//municipioxi
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.90310906469838%2C12.486088594885388&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxii
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.92199032137794%2C12.49706609838948&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxiii
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.94259734412539%2C12.537557626957634&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxv
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.911813435604145%2C12.51550388722295&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxvi
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.8923761202515%2C12.551542738743551&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxvii
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.85080260304638%2C12.572528744856111radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxxiii
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.893608255787285%2C12.43799958678042&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxii
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.86913619388441%2C12.435364043189935&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxxv
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.941402364287%2C12.463372646697835&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxxiv
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.92663991727849%2C12.403941150348558&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");
		//municipioxvaticano
		regions.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.9023972701211%2C12.454238702237726&radius=3000&type="+collName+"&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes");

		for (int i=0; i< regions.size();i++) {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(regions.get(i))).build();
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> testStr = s).join();
			System.out.println("AFTER!");
			System.out.println(testStr);
			System.out.println("DONE");
			ArrayList<Document> docList;
			docList = ParserAPI.Parse(testStr);
			MongoCollection collection = database.getCollection(collName);

			//collection.insertMany(docList);*/
			System.out.println("docListSize beforeLoop: "+docList.size());
			for(int j=0;j<docList.size();j++)
			{
				Document tempDoc=docList.get(j);
				String docName= (String) tempDoc.get("name");
				System.out.println("Name: "+tempDoc.get("name"));

				if(!CheckIfDataExists(docName, collection))
				{
					System.out.println("STORE ADDED ADDED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					collection.insertOne(tempDoc);
					counter++;
				}
				else
				{
					System.out.println("NOPE NOPE NOPE");
				}
			}
			System.out.println("AFTER LOOP");
			System.out.println("COUNTER:::: "+counter+" CollName:::: "+collName);
		}
	}

	public static boolean CheckIfDataExists(String name,MongoCollection collection) //this function....
	{
		if(collection.find(new Document("name",name)).first()==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}