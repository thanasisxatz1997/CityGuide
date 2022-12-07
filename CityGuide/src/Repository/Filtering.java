package Repository;

import Forms.MainForm;
import com.mongodb.CursorType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.apache.http.util.TextUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;

public class Filtering {
    //public static MongoCollection collection;
    public static MongoDatabase database;

    public static ArrayList<Document> FindStoreType(String type)
    {
        MongoCollection collection;
        collection=database.getCollection("Stores");
        ArrayList<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterable = collection.find(new Document("type",type));
        iterable.into(results);
        System.out.println(results);
        return (results);
    }

    private static String ChangeType(String type)
    {
        type=type.toLowerCase();
        char[] typeArray=type.toCharArray();
        ArrayList<Character> typeArrayList=new ArrayList<>();
        for (char c:typeArray) {
            typeArrayList.add(c);
        }
        int lastIndex=typeArrayList.size()-1;
        System.out.println("Size is: "+lastIndex);
        System.out.println("Removed the : "+typeArrayList.get(lastIndex));
        typeArrayList.remove(lastIndex);

        String newType = new String();
        for (char c:typeArrayList) {
            newType=newType+c;
        }
        System.out.println("New type is: "+newType);
        return newType;
    }

    public static boolean CheckSearchedFilters(String filterStr)
    {
        ArrayList<String> availableTypes=new ArrayList<String>();
        availableTypes.add("restaurant");
        availableTypes.add("cafe");
        availableTypes.add("casino");
        availableTypes.add("bakery");
        availableTypes.add("library");
        availableTypes.add("movie_theater");
        availableTypes.add("park");
        availableTypes.add("spa");
        availableTypes.add("shopping_mall");
        availableTypes.add("stadium");
        availableTypes.add("theatres");
        availableTypes.add("activities");
        availableTypes.add("bus_station");
        availableTypes.add("airport");
        availableTypes.add("activities");
        availableTypes.add("random_stores");
        availableTypes.add("night_club");
        availableTypes.add("hospital");
        availableTypes.add("church");
        availableTypes.add("art_gallery");
        availableTypes.add("subway_station");
        availableTypes.add("zoo");
        availableTypes.add("taxi_stand");
        availableTypes.add("transit_station");
        availableTypes.add("museum");
        availableTypes.add("tourist_attraction");
        availableTypes.add("atm");
        if(availableTypes.contains(filterStr))
        {
            return true;
        }
        return false;
    }

    public static ArrayList<Document> FilterStores(String type,String rating,String searchedText)
    {
        System.out.println("Searching for: "+type+rating+searchedText);
        ArrayList<String> typeArraylist= new ArrayList<>();
        if(!type.equals(""))
        {
            typeArraylist.add(type.toLowerCase());
        }
        if(!searchedText.equals("") && !searchedText.equals(type))
        {
            typeArraylist.add(searchedText);
        }
        float ratingNum = 0;
        if(!rating.equals(""))
        {
            ratingNum=Float.parseFloat(rating);
        }
        System.out.println("I AM HERE AND I AM A : "+typeArraylist.get(0));
        MongoCollection collection=database.getCollection(typeArraylist.get(0));
        //MongoCollection collection=database.getCollection("Stores");
        Bson projectionFields= Projections.fields(Projections.include("name","photos","rating","place_id","reviews","opening_hours","website"),Projections.excludeId());
        ArrayList<Document> results = new ArrayList<Document>();
        //FindIterable<Document> iterable = collection.find(new Document("type",type));
        System.out.println("TYPES TO SEARCH ARE: "+typeArraylist);
        Bson filter=Filters.gte("rating",ratingNum);
        //Bson filter= Filters.and(Filters.gte("rating",ratingNum),Filters.eq("types","cafe"));
        for (String s:typeArraylist) {
            System.out.println("Type s is :"+s);
            filter=Filters.and(filter,Filters.eq("types",s));
        }
        FindIterable<Document> iterable = collection.find(filter).projection(projectionFields).limit(200);
        System.out.println("Iterable is : "+iterable);
        //FindIterable<Document> iterable = collection.find(gte("rating",ratingNum)).projection(projectionFields);
        //FindIterable<Document> iterable = collection.find(new Document("name","Villa Pirandello Hotel & Caf?"));
        iterable.into(results);
        System.out.println("Results2: "+results);
        return (results);
    }
}


