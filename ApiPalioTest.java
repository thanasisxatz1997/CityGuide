import Repository.ConnectToDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import static Panels.Home.HomeHintsPanel.collection;


class ApiPalioTest {

  @Test
  public void GetData() throws Exception {

    new ConnectToDatabase();
    MongoDatabase database = ConnectToDatabase.mainDatabase;
    ArrayList<String> placesIdArrayList = new ArrayList<>();
    int i = 0;
    int k = 0;
    int s = 0;
    int j = 0;
    int w=0;
    int z=0;
    int h=0;
    int m=0;
    int g=0;
    int u=0;
    int x=0;
    int y=0;
    int a=0;
    int b=0;
    int r=0;
    int v=0;
    int e=0;
    int f=0;
    int c=0;
    collection = database.getCollection("cafe");
    ArrayList<Document> results = new ArrayList<>();
    FindIterable<Document> iterable = collection.find(new Document());
    iterable.into(results);
    for (Document doc : results) {
        if ( doc.containsKey("adr_address") && doc.containsKey("photos") && doc.containsKey("rating") && doc.containsKey("icon") && doc.containsKey("icon_background_color") && doc.containsKey("icon_mask_base_uri") && doc.containsKey("opening_hours")&& doc.containsKey("users_ratings_total")&& doc.containsKey("opening_hours")&& doc.containsKey("plus_code")&& doc.containsKey("reviews")&& doc.containsKey("address_components")&& doc.containsKey("formatted_address")&& doc.containsKey("website")&& doc.containsKey("formatted_phone_number")&& doc.containsKey("utc_offset")) {
            placesIdArrayList.add(doc.get("place_id").toString());
            System.out.println("The place_id " + i + " is: " + doc.get("place_id").toString());
            placesIdArrayList.add(doc.get("place_id").toString());
            i++;
            placesIdArrayList.add(doc.get("business_status").toString());
            System.out.println("business_status " + k + " is: " + doc.get("business_status").toString());
            placesIdArrayList.add(doc.get("business_status").toString());
            k++;
            placesIdArrayList.add(doc.get("name").toString());
            System.out.println("name " + s + " is: " + doc.get("name").toString());
            placesIdArrayList.add(doc.get("name").toString());
            s++;
            placesIdArrayList.add(doc.get("icon").toString());
            System.out.println("icon " + s + " is: " + doc.get("icon").toString());
            placesIdArrayList.add(doc.get("icon").toString());
            s++;
            placesIdArrayList.add(doc.get("icon_background_color").toString());
            System.out.println("icon_background_color " + w + " is: " + doc.get("icon_background_color").toString());
            placesIdArrayList.add(doc.get("icon_background_color").toString());
            w++;
            placesIdArrayList.add(doc.get("icon_mask_base_uri").toString());
            System.out.println("icon_mask_base_uri" + z + " is: " + doc.get("icon_mask_base_uri").toString());
            placesIdArrayList.add(doc.get("icon_mask_base_uri").toString());
            z++;
            placesIdArrayList.add(doc.get("photos").toString());
            System.out.println("photos " + g + " is: " + doc.get("photos").toString());
            placesIdArrayList.add(doc.get("photos").toString());
            g++;
            placesIdArrayList.add(doc.get("opening_hours").toString());
            System.out.println("opening_hours " + h  + " is: " + doc.get("opening_hours").toString());
            placesIdArrayList.add(doc.get("opening_hours").toString());
            h++;
            placesIdArrayList.add(doc.get("rating").toString());
            System.out.println("rating" + m + " is: " + doc.get("rating").toString());
            placesIdArrayList.add(doc.get("rating").toString());
            m++;
            placesIdArrayList.add(doc.get("users_ratings_total").toString());
            System.out.println("users_ratings_total " + x + " is: " + doc.get("users_ratings_total").toString());
            placesIdArrayList.add(doc.get("users_ratings_total").toString());
            x++;
            placesIdArrayList.add(doc.get("geometry").toString());
            System.out.println("geometry " + y + " is: " + doc.get("geometry").toString());
            placesIdArrayList.add(doc.get("geometry").toString());
            y++;
            placesIdArrayList.add(doc.get("plus_code").toString());
            System.out.println("plus_code " + u + " is: " + doc.get("plus_code").toString());
            placesIdArrayList.add(doc.get("plus_code").toString());
            u++;
            placesIdArrayList.add(doc.get("reference").toString());
            System.out.println("reference " + y + " is: " + doc.get("reference").toString());
            placesIdArrayList.add(doc.get("reference").toString());
            y++;
            placesIdArrayList.add(doc.get("vicinity").toString());
            System.out.println("vicinity " + a + " is: " + doc.get("vicinity").toString());
            placesIdArrayList.add(doc.get("vicinity").toString());
            a++;
            placesIdArrayList.add(doc.get("address_components").toString());
            System.out.println("address_components " + b + " is: " + doc.get("address_components").toString());
            placesIdArrayList.add(doc.get("address_components").toString());
            b++;
            placesIdArrayList.add(doc.get("adr_address").toString());
            System.out.println("adr_address " + r + " is: " + doc.get("adr_address").toString());
            placesIdArrayList.add(doc.get("adr_address").toString());
            r++;
            placesIdArrayList.add(doc.get("formatted_address").toString());
            System.out.println("formatted_address " + c + " is: " + doc.get("formatted_address").toString());
            placesIdArrayList.add(doc.get("formatted_address").toString());
            c++;
            placesIdArrayList.add(doc.get("formatted_phone_number").toString());
            System.out.println("formatted_phone_number " + v + " is: " + doc.get("formatted_phone_number").toString());
            placesIdArrayList.add(doc.get("formatted_phone_number").toString());
            v++;
            placesIdArrayList.add(doc.get("reviews").toString());
            System.out.println("reviews " + f + " is: " + doc.get("reviews").toString());
            placesIdArrayList.add(doc.get("reviews").toString());
            f++;
            placesIdArrayList.add(doc.get("utc_offset").toString());
            System.out.println("utc_offset " + e + " is: " + doc.get("utc_offset").toString());
            placesIdArrayList.add(doc.get("utc_offset").toString());
            e++;placesIdArrayList.add(doc.get("website").toString());
            System.out.println("website " + j + " is: " + doc.get("website").toString());
            placesIdArrayList.add(doc.get("website").toString());
            j++;
        }
    }
      System.out.println("We get it all!!!!!!!!!!!!!!!");
  }
}



