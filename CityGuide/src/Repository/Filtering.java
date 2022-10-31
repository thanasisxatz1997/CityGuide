package Repository;

import Forms.MainForm;
import com.mongodb.CursorType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

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

    public static boolean CheckSearchedFilters(String filterStr)
    {
        if(filterStr.equals("restaurant")||filterStr.equals("cafe"))
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

        //MongoCollection collection=database.getCollection(typeArraylist.get(0));
        MongoCollection collection=database.getCollection("Stores");

        Bson projectionFields= Projections.fields(Projections.include("name","photos","rating"),Projections.excludeId());

        ArrayList<Document> results = new ArrayList<Document>();
        //FindIterable<Document> iterable = collection.find(new Document("type",type));
        System.out.println("TYPES TO SEARCH ARE: "+typeArraylist);
        Bson filter=Filters.gte("rating",ratingNum);
        //Bson filter= Filters.and(Filters.gte("rating",ratingNum),Filters.eq("types","cafe"));
        for (String s:typeArraylist) {
            filter=Filters.and(filter,Filters.eq("types",s));
        }
        FindIterable<Document> iterable = collection.find(filter).projection(projectionFields);
        //FindIterable<Document> iterable = collection.find(gte("rating",ratingNum)).projection(projectionFields);
        //FindIterable<Document> iterable = collection.find(new Document("name","Villa Pirandello Hotel & Caf?"));
        iterable.into(results);
        System.out.println(results);
        return (results);
    }
}


