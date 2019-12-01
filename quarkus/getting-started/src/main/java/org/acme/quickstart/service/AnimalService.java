package org.acme.quickstart.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.acme.quickstart.model.Animal;

@ApplicationScoped
public class AnimalService {

    @Inject
    MongoClient mongoClient;

    public List<Animal> list() {
        List<Animal> list = new ArrayList<>();

        try (MongoCursor<Animal> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        }

        return list;
    }

    public void add(Animal animal) {
        getCollection().insertOne(animal);
    }

    private MongoCollection<Animal> getCollection() {
        return mongoClient.getDatabase("test").getCollection("animal", Animal.class);
    }

}