package Repository;

import Forms.MainForm;
import com.mongodb.CursorType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.awt.*;
import java.util.ArrayList;

public class Filtering {
    public static MongoCollection collection;


    public static ArrayList<Document> FindStoreType(String type)
    {
        ArrayList<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterable = collection.find(new Document("type",type));
        iterable.into(results);
        System.out.println(results);
        return (results);
    }

    public static boolean CheckSearchedFilters(String filterStr)
    {
        if(filterStr.equals("Restaurant")||filterStr.equals("Cafe"))
        {
            return true;
        }
        return false;
    }

}


