package org.acme.quickstart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.acme.quickstart.model.Person;
import org.bson.Document;

@ApplicationScoped
public class PersonService {

    @Inject
    MongoClient mongoClient;

    public List<Person> list() {
        List<Person> personList = new ArrayList<>();

        try (MongoCursor<Document> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Person person = new Person(document.getString("name"), document.getInteger("age"),
                        (Date) document.get("birthday"));
                personList.add(person);
            }
        }

        return personList;
    }

    public void add(Person person) {
        Document document = new Document().append("name", person.getName()).append("age", person.getAge())
                .append("birthday", person.getBirthday());
        getCollection().insertOne(document);
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("test").getCollection("person");
    }

}