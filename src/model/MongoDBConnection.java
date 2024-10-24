package model;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static String connectionLocal = "mongodb://localhost:27017/";
    private static String connectionCloud = "mongodb+srv://admin:admin@atlascluster.mnaai2a.mongodb.net/?retryWrites=true&w=majority&appName=AtlasCluster";
    private static String databaseName = "Hotel";

    public MongoDBConnection() {
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
    
    public static void connection() {
        try {
            mongoClient = MongoClients.create(connectionLocal);
            database = mongoClient.getDatabase(databaseName);
            System.out.println("Connected to the MongoDB database successfully.");
        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }
}
