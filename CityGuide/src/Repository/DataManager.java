package Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.imageio.ImageIO;
import javax.management.Query;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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


    public static void AddStoreToFavourites()
    {
        MongoCollection collection=database.getCollection("Users");
        Document doc=new Document();
        doc.append("name","reppas");
        collection.insertOne(doc);
    }

    public static boolean StoreExistsInFavourites(String storeName)
    {
        MongoCollection userCollection=database.getCollection("Users");

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

    public static Document GetRandomRecommendedStore()
    {
        MongoCollection recommendedStoreCollection= database.getCollection("Recomended");
        Document storeDoc;

        ArrayList<String> storeNameList= new ArrayList<>();

        FindIterable<Document> iterDoc = recommendedStoreCollection.find();
        Iterator it = iterDoc.iterator();
        int count=0;
        while (it.hasNext()) {
            count++;
            Document nextDoc= (Document)it.next();
            storeNameList.add(nextDoc.get("name").toString());
        }
        System.out.println(storeNameList);
        System.out.println("NUMBER OF DOCS IS: "+storeNameList.size());
        int randomNum= (int) Math.floor(Math.random()*(storeNameList.size()-1-1+1)+1);
        String randomStoreName= storeNameList.get(randomNum);
        storeDoc= (Document) recommendedStoreCollection.find(new Document("name",randomStoreName)).first();
        System.out.println("The Random store is: "+storeDoc);

        return storeDoc;
    }

    public static Document GetRandomRecommendedStoreTest()
    {
        MongoCollection recommendedStoreCollection= database.getCollection("recommended_stores");
        Document storeDoc;

        ArrayList<String> storeNameList= new ArrayList<>();

        FindIterable<Document> iterDoc = recommendedStoreCollection.find();
        Iterator it = iterDoc.iterator();
        int count=0;
        while (it.hasNext()) {
            count++;
            Document nextDoc= (Document)it.next();
            storeNameList.add(nextDoc.get("name").toString());
        }
        System.out.println(storeNameList);
        System.out.println("NUMBER OF DOCS IS: "+storeNameList.size());
        int randomNum= (int) Math.floor(Math.random()*(storeNameList.size()-1-1+1)+1);
        String randomStoreName= storeNameList.get(randomNum);
        storeDoc= (Document) recommendedStoreCollection.find(new Document("name",randomStoreName)).first();
        System.out.println("The Random store is: "+storeDoc);

        return storeDoc;
    }

    public static Image GetRandomStoreImage(Document doc)
    {
        final String[] photoStr = new String[1];
        photoStr[0] = doc.getList("photos", Map.class).stream().map(map -> photoStr[0] =map.toString()).collect(Collectors.toList()).toString();
        System.out.println("Height: "+ photoStr[0]);

        System.out.println("String exists in position: "+ photoStr[0].lastIndexOf("height"));
        char[] photoCharArray=photoStr[0].toCharArray();
        int photorefeRenceStrStart=photoStr[0].lastIndexOf("photo_reference=")+16;
        if(photorefeRenceStrStart!=-1)
        {
            System.out.println("String exists in position: "+ photorefeRenceStrStart);
            ArrayList<Character> photoReference = new ArrayList<>();
            for(int i=photorefeRenceStrStart;photoCharArray[i]!=',';i++)
            {
                photoReference.add(photoCharArray[i]);
            }
            String photoReferenceStr;
            StringBuilder builder = new StringBuilder(photoReference.size());
            for(Character ch: photoReference)
            {
                builder.append(ch);
            }
            photoReferenceStr=builder.toString();
            System.out.println("THE PHOTO REFERENCE IS: "+ photoReference);
            String photoFullStr="https://maps.googleapis.com/maps/api/place/photo?photoreference="+photoReferenceStr+"&sensor=false&maxheight="+"400"+"&maxwidth="+"400"+"&key="+"AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes";
            System.out.println("THE PHOTO STRING IS: "+photoFullStr);
            Image singleStoreImage=null;
            try {
                URL photoUrl=new URL(photoFullStr);
                singleStoreImage= ImageIO.read(photoUrl);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return singleStoreImage;
        }
        return null;
    }
}
