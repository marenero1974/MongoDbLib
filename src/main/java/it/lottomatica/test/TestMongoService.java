package it.lottomatica.test;


import it.lottomatica.model.CustomDocument;
import it.lottomatica.service.CustomMongoService;

import java.util.List;

public class TestMongoService {
    public static void main(String[] args) {
        CustomMongoService service = new CustomMongoService("mongodb://localhost:27017", "testdb", "customCollection");

        // Create a new document
        CustomDocument newDocument = new CustomDocument("Test Document");
        CustomDocument savedDocument = service.save(newDocument);
        System.out.println("Saved Document: " + savedDocument);

        // Find all documents
        List<CustomDocument> documents = service.findAll();
        System.out.println("All Documents: " + documents);

        // Find by ID
        CustomDocument foundDocument = service.findById(savedDocument.getId().toHexString());
        System.out.println("Found Document: " + foundDocument);

        // Update document
        savedDocument.setName("Updated Document");
        boolean updated = service.update(savedDocument.getId().toHexString(), savedDocument);
        System.out.println("Document Updated: " + updated);

        // Delete document
        boolean deleted = service.delete(savedDocument.getId().toHexString());
        System.out.println("Document Deleted: " + deleted);
    }
}

