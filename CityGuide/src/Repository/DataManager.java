package Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class DataManager {
    private static MongoDatabase database;


    public DataManager()
    {
    }

    public static void SetDatabase(MongoDatabase db)
    {
        database=db;
    }


    public static boolean StoreExistsInFavourites(String storeName)
    {
        MongoCollection storeCollection=database.getCollection("Stores");
        MongoCollection userCollection=database.getCollection("User");

        if(UserExistsInFavourites())
        {
            System.out.println("User Exists.");
            System.out.println("current user name!!!!!!!!: "+ CurrentUser.userName);
            try {
                //Document result= (Document) DbCollection.find(new Document("name",name)).first();
                //return result.get("email").toString();

                Document doc = (Document) userCollection.find(new Document("name",CurrentUser.userName)).first();
                if (doc==null)
                {
                    System.out.println("null doc");
                }
                else
                {
                    System.out.println("not null doc");
                }
                String tempName = doc.get("name").toString();
                System.out.println("Tempname= " + tempName);

                //ArrayList<Objects> storeDocList = new ArrayList<>();
                //storeDocList.addAll((Collection<? extends Objects>) doc.get("favourites"));
                //System.out.println("Stores List is: "+storeDocList);

                //List<Document> storeDocList;
                //storeDocList.addAll((List<Document>) doc.get("favourites"));
                //System.out.println("Stores List is: "+storeDocList);

                /*if(storeDocList==null)
                {
                    System.out.println("Stores null");
                }
                else
                {
                    System.out.println("Stores not null");
                }*/
                //for (Document d: storeDocList) {
                    //System.out.println("One doc: "+d);
                //}
                //objectArr.add(doc.getList("favourites",Object.class));
                //for (Object obj:objectArr) {
                //    System.out.println("object: "+obj);
                //}
            }
            catch(Exception e)
            {
            }
        }
        else {
            System.out.println("User does not exist.");
        }

        return false;
    }

    public static boolean UserExistsInFavourites()
    {
        MongoCollection userCollection=database.getCollection("User");
        String userName=CurrentUser.userName;
        if(userCollection.find(new Document("name",userName)).first()==null)
        {
            return false;
        }
        else {
            return true;
        }
    }
}
