package it.lottomatica.service;

import it.lottomatica.model.CustomDocument;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CustomMongoService {

    private final MongoCollection<Document> collection;

    public CustomMongoService(String connectionString, String databaseName, String collectionName) {
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
    }

    public List<CustomDocument> findAll() {
        List<CustomDocument> documents = new ArrayList<>();
        for (Document doc : collection.find()) {
            documents.add(mapDocument(doc));
        }
        return documents;
    }

    public CustomDocument findById(String id) {
        Document doc = collection.find(eq("_id", new ObjectId(id))).first();
        return doc != null ? mapDocument(doc) : null;
    }

    public CustomDocument save(CustomDocument customDocument) {
        Document doc = new Document("name", customDocument.getName());
        collection.insertOne(doc);
        customDocument.setId(doc.getObjectId("_id"));
        return customDocument;
    }

    public boolean update(String id, CustomDocument customDocument) {
        Document doc = new Document("name", customDocument.getName());
        Document updated = collection.findOneAndReplace(eq("_id", new ObjectId(id)), doc);
        return updated != null;
    }

    public boolean delete(String id) {
        Document deleted = collection.findOneAndDelete(eq("_id", new ObjectId(id)));
        return deleted != null;
    }

    private CustomDocument mapDocument(Document doc) {
        CustomDocument customDocument = new CustomDocument();
        customDocument.setId(doc.getObjectId("_id"));
        customDocument.setName(doc.getString("name"));
        return customDocument;
    }
}

