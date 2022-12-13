package Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.imageio.ImageIO;
import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
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


    public static void AddStoreToFavourites(Document doc)
    {
        MongoCollection collection=database.getCollection("Favourites");
        if(UserExistsInFavourites())
        {
            System.out.println("THE DOC TO BE ADDED IS:  "+doc);
            if(!StoreExistsInFavourites(doc.get("place_id").toString()))
            {
                collection.findOneAndUpdate(Filters.eq("name",CurrentUser.userName),Updates.push("stores",doc));
                System.out.println("Doc added to favourites under name: "+CurrentUser.userName);
            }
            else
            {
                System.out.println("Store Already in favourites!");
            }
        }
        else
        {
            AddUserToFavourites();
            AddStoreToFavourites(doc);
        }
    }

    public static void AddUserToFavourites()
    {
        MongoCollection collection=database.getCollection("Favourites");
        Document doc=new Document();
        doc.append("name",CurrentUser.userName);
        collection.insertOne(doc);
        ArrayList<String> emptyList =new ArrayList<>();
        collection.findOneAndUpdate(Filters.eq("name",CurrentUser.userName), Updates.pushEach("stores",emptyList));
    }

    public static boolean StoreExistsInFavourites(String storeId)
    {
        MongoCollection collection=database.getCollection("Favourites");
        Document doc= (Document) collection.find(Filters.eq("name",CurrentUser.userName)).first();
        ArrayList<Document> storeDocs;
        storeDocs= (ArrayList<Document>) doc.getList("stores",Document.class);
        for(int i=0;i<storeDocs.size();i++)
        {
            if (storeDocs.get(i).get("place_id").equals(storeId))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean UserExistsInFavourites()
    {
        MongoCollection coll=database.getCollection("Favourites");
        if(coll.find(new Document("name",CurrentUser.userName)).first()!=null)
        {
            return true;
        }
        return false;
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

    public static ArrayList<Document> GetFavouriteStores()
    {
        ArrayList<Document> storeList;
        MongoCollection collection=database.getCollection("Favourites");
        Document doc= (Document) collection.find(Filters.eq("name",CurrentUser.userName)).first();
        storeList= (ArrayList<Document>) doc.getList("stores",Document.class);
        return storeList;
    }

    public static Image GetActivitiesImage(Document doc)
    {
        Image singleStoreImage = new ImageIcon("src/resources/BackgroundImages/colosseum.png").getImage();
        try {
            URL photoUrl = new URL((String) doc.get("photo"));
            singleStoreImage = ImageIO.read(photoUrl.openStream());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return singleStoreImage;
    }

    public static Image GetStoreImage(Document doc)
    {
        if(doc.get("photos")!=null) {
            final String[] photoStr = new String[1];
            System.out.println("Doc is: " + doc);
            photoStr[0] = doc.getList("photos", Map.class).stream().map(map -> photoStr[0] = map.toString()).collect(Collectors.toList()).toString();
            System.out.println("Height: " + photoStr[0]);
            System.out.println("String exists in position: " + photoStr[0].lastIndexOf("height"));
            char[] photoCharArray = photoStr[0].toCharArray();
            int photorefeRenceStrStart = photoStr[0].lastIndexOf("photo_reference=") + 16;
            if (photorefeRenceStrStart != -1) {
                System.out.println("String exists in position: " + photorefeRenceStrStart);
                ArrayList<Character> photoReference = new ArrayList<>();
                for (int i = photorefeRenceStrStart; photoCharArray[i] != ','; i++) {
                    photoReference.add(photoCharArray[i]);
                }
                String photoReferenceStr;
                StringBuilder builder = new StringBuilder(photoReference.size());
                for (Character ch : photoReference) {
                    builder.append(ch);
                }
                photoReferenceStr = builder.toString();

                char[] photoWidthArray = photoStr[0].toCharArray();
                int photoWidthStrStart = photoStr[0].lastIndexOf("width=") + 6;
                ArrayList<Character> photoWidth = new ArrayList<>();
                for (int i = photoWidthStrStart; photoWidthArray[i] != '}' && photoWidthArray[i] != ','; i++) {
                    photoWidth.add(photoWidthArray[i]);
                }
                String photoWidthStr;
                StringBuilder widthBuilder = new StringBuilder(photoWidth.size());
                for (Character ch : photoWidth) {
                    widthBuilder.append(ch);
                }
                photoWidthStr = builder.toString();


                char[] photoHeightArray = photoStr[0].toCharArray();
                int photoHeightStrStart = photoStr[0].lastIndexOf("height=") + 7;
                ArrayList<Character> photoHeight = new ArrayList<>();
                for (int i = photoHeightStrStart; photoHeightArray[i] != ',' && photoHeightArray[i] != '}'; i++) {
                    photoHeight.add(photoHeightArray[i]);
                }
                String photoHeightStr;
                StringBuilder heightBuilder = new StringBuilder(photoHeight.size());
                for (Character ch : photoHeight) {
                    heightBuilder.append(ch);
                }
                photoHeightStr = builder.toString();


                System.out.println("THE PHOTO REFERENCE IS: " + photoReference);
                System.out.println("THE PHOTO WIDTH IS: " + photoWidth);
                System.out.println("THE PHOTO HEIGHT IS: " + photoHeight);
                String lastPhotoWidthStr = "";
                String lastPhotoHeightStr = "";
                for (char c : photoWidth) {
                    lastPhotoWidthStr = lastPhotoWidthStr + c;
                }
                for (char c : photoHeight) {
                    lastPhotoHeightStr = lastPhotoHeightStr + c;
                }

                String photoFullStr = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + photoReferenceStr + "&sensor=false&maxheight=" + lastPhotoHeightStr + "&maxwidth=" + lastPhotoWidthStr + "&key=" + "AIzaSyBgNG7tFRkbstl6J3tAp0otEwvBpsRsqDc";
                System.out.println("THE PHOTO STRING IS: " + photoFullStr);
                Image singleStoreImage = new ImageIcon("src/resources/BackgroundImages/colosseum.png").getImage();
                try {
                    URL photoUrl = new URL(photoFullStr);
                    singleStoreImage = ImageIO.read(photoUrl.openStream());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return singleStoreImage;
            }
        }
        return null;
    }

    public static ArrayList<Document> GetActivities()
    {
        MongoCollection collection=database.getCollection("activities");
        ArrayList<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterable = collection.find(new Document());
        iterable.into(results);
        return results;
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
            String photoFullStr="https://maps.googleapis.com/maps/api/place/photo?photoreference="+photoReferenceStr+"&sensor=false&maxheight="+"400"+"&maxwidth="+"400"+"&key="+"AIzaSyBgNG7tFRkbstl6J3tAp0otEwvBpsRsqDc";
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
