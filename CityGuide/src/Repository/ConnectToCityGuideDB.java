package Repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ConnectToCityGuideDB {

    public static ConnectionString connectionString;

    private MongoDatabase database;
    public ConnectToCityGuideDB()
    {
        System.out.println("DONE");
        connectionString = new ConnectionString("mongodb+srv://Administrator:CityGuide2022@cluster0.6nkro6t.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        database = mongoClient.getDatabase("CityGuideCollection");
        Filtering.database=database;
        DataManager.SetDatabase(database);

    }
    public MongoDatabase getDatabase()
    {
        return this.database;
    }
}
