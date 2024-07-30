package it.lottomatica.model;

import org.bson.types.ObjectId;

public class CustomDocument {
    private ObjectId id;
    private String name;

    public CustomDocument() {}

    public CustomDocument(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomDocument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
