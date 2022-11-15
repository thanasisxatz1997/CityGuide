package Repository;

import Panels.Home.HomeHintsPanel;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import java.util.logging.Logger;

public class ConnectToDatabase {
    public static ConnectionString connectionString;
    public ConnectToDatabase()
    {
        ConnectToMainDatabase();
    }

    public void ConnectToTestDatabase()
    {
        System.out.println("DONE");
        connectionString = new ConnectionString("mongodb+srv://admin:projectlogin2022@loginformdb.sbptfd0.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("Project_Log_In");
        Filtering.database=database;
        DataManager.SetDatabase(database);
        MongoCollection coll= database.getCollection("Stores");
        //Filtering.collection=coll;
        if(coll!=null)
        {
            System.out.println("CONNECTED TO DATABASE");
        }
        HomeHintsPanel.collection=database.getCollection("Facts");
    }

    public void ConnectToMainDatabase() {
        System.out.println("DONE");
        connectionString = new ConnectionString("mongodb+srv://Administrator:CityGuide2022@cluster0.6nkro6t.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("CityGuideCollection");
        Filtering.database = database;
        DataManager.SetDatabase(database);
        MongoCollection coll = database.getCollection("Stores");
        //Filtering.collection=coll;
        if (coll != null) {
            System.out.println("CONNECTED TO DATABASE");
        }
        HomeHintsPanel.collection = database.getCollection("Facts");


    }
}
