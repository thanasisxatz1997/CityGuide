package Repository;

import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static Repository.ParserAPIDebateable.Parse;

public class ApiPalio {
    public static ArrayList<String> JSONstrList = new ArrayList<>();
    private static HttpURLConnection connection;



    private static String testStr;
    public static MongoDatabase database;
    public static int counter=0;



    public static void main(String[] args) throws ParseException, IOException {

        new ConnectToDatabase();
        database = ConnectToDatabase.mainDatabase;
        String collName = "atm";
        JSONstrList = new ArrayList<>();


        String resultString ="";
        resultString=AddResults(resultString);

        MongoCollection collection = database.getCollection(collName);

        ArrayList<String> placesIdList = GetRestaurantIds(collection, collName);
        ArrayList<Document> docList = null;
        int j;
        //placesIdList.size()
        for (j = 0; j < 2; j++) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/details/json?fields=address_components%2Cadr_address%2Cbusiness_status%2Cformatted_address%2Cformatted_phone_number%2Cgeometry%2Cicon%2Cicon_background_color%2Cicon_mask_base_uri%2Cinternational_phone_number%2Cname%2Copening_hours%2Cphotos%2Cplace_id%2Cplus_code%2Crating%2Creference%2Creviews%2Ctypes%2Curl%2Cuser_ratings_total%2Cutc_offset%2Cvicinity%2Cwebsite&place_id=" + placesIdList.get(j) + "&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> testStr = s).join();
            //testStr=RemoveStatus(testStr);
            resultString = resultString +",\n"+ testStr;
            System.out.println("CALL NUMBER " + j + "DONE");
        }
        System.out.println("Merging Doc: " + j);
        System.out.println("AFTER!");
        resultString=FinishResults(resultString);
        System.out.println(resultString);
        System.out.println("DONE, Now parsing!");
        docList = ParserAPIDebateable.Parse(resultString);
        collection = database.getCollection(collName);
        System.out.println("docListSize beforeLoop: " + docList.size());
        for (int z = 0; z < docList.size(); z++) {
            Document tempDoc = docList.get(z);
            String place_id = (String) tempDoc.get("place_id");

            if (CheckIfDataExists(place_id, collection) && !DocIsReplaced(tempDoc,collection)) {
                System.out.println("STORE ADDED ADDED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("The tempDoc is: "+tempDoc);
                collection.findOneAndReplace(Filters.eq("place_id",place_id),tempDoc);
                /*collection.findOneAndUpdate((ClientSession) Filters.eq("place_id", place_id),  Updates.push("address_components", tempDoc.get("address_components")) ,Updates.push("adr_address", tempDoc.get("adr_address")), (FindOneAndUpdateOptions) Updates.push("formatted_address", tempDoc.get("formatted_address")));
                System.out.println("address_components ADDED, adr_address ADDED, formatted_address ADDED::::::::::::::::::::::::");
                collection.findOneAndUpdate((ClientSession) Filters.eq("place_id", place_id), Updates.push("formatted_phone_number", tempDoc.get("formatted_phone_number")),Updates.push("reviews", tempDoc.get("reviews")), (FindOneAndUpdateOptions) Updates.push("utc_offset", tempDoc.get("utc_offset")));
                System.out.println("formatted_phone_number ADDED, reviews ADDED, utc_offset ADDED::::::::::::::::::::::::");
                collection.findOneAndUpdate((ClientSession) Filters.eq("place_id", place_id), Updates.push("website", tempDoc.get("website")),Updates.push("international_phone_number", tempDoc.get("international_phone_number")));
                System.out.println("website ADDED, international_phone_number ADDED:::::::::::::::::::::::::");*/
                counter++;

            } else {

                System.out.println("NOPE NOPE NOPE, Doc already replaced");
            }
        }
        System.out.println("AFTER LOOP");
        System.out.println("COUNTER:::: " + counter + " CollName:::: " + collName);
    }

    private static boolean DocIsReplaced(Document doc,MongoCollection coll)
    {
        Document testDoc= (Document) coll.find(new Document("place_id",doc.get("place_id"))).first();

        if(testDoc!= null)
        {
            if(!testDoc.containsKey("formatted_address"))
            {
                return false;
            }
        }
        return true;
    }

    public static String AddResults(String str)
    {
        str="{\n\t"+'"'+"results"+'"'+":[\n";
        return str;
    }

    public static String FinishResults(String str)
    {
        str=str+"\n]\n}";
        return str;
    }


    public static String RemoveStatus(String str)
    {
        //Finding first and last instances.
        int first=str.indexOf('{');
        int last=str.lastIndexOf('}');
        System.out.println("first index of { in pos: "+first+"Last index of } in pos: "+last);

        //Now to remove.
        String newStr="";
        for (int i=first;i<last;i++)
        {
            newStr=newStr+str.charAt(i);
        }
        //Now we have removed extra {}
        System.out.println("STR WITHOUT {} ::::::");
        System.out.println(newStr);

        //Now to remove status::OK

        //First we find possition of lst comma
        String finalStr="";
        int lastCommaPos=newStr.lastIndexOf(',');
        for(int i=0;i<lastCommaPos;i++)
        {
            finalStr=finalStr+newStr.charAt(i);
        }
        //Lets see if it works
        //Printing final Str
        System.out.println("NOW THE FINAL WORKING STR IS::::: ");
        System.out.println(finalStr);
        return finalStr;
    }

    public static boolean CheckIfDataExists (String place_id, MongoCollection collection) //this function....
    {
        if (collection.find(new Document("place_id", place_id)).first() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static ArrayList<String> GetRestaurantIds(MongoCollection collection, String collName) {
        ArrayList<String> placesIdArrayList=new ArrayList<>();
        int i=0;
        collection = database.getCollection(collName);
        ArrayList<Document> results = new ArrayList<>();
        FindIterable<Document> iterable = collection.find(new Document());
        iterable.into(results);
        for (Document doc :results) {
            System.out.println("The place_id "+i+" is: "+doc.get("place_id").toString());
            placesIdArrayList.add(doc.get("place_id").toString());
            i++;
        }
        System.out.println("DONE TAKING PLACE ID");
        return placesIdArrayList;
    }
}