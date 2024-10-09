package model;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private MongoClient mongoClient;
    private MongoDatabase database;
    String connectionLocal = "mongodb://localhost:27017/";
    String connectionCloud = "mongodb+srv://admin:admin@atlascluster.mnaai2a.mongodb.net/?retryWrites=true&w=majority&appName=AtlasCluster";
    String databaseName = "Hotel";

    public MongoDBConnection() {
        try {
            mongoClient = MongoClients.create(connectionLocal);
            database = mongoClient.getDatabase(databaseName);
            System.out.println("Connected to the MongoDB database successfully.");
        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
    public static void main(String[] args) {
        new MongoDBConnection();
    }
}
