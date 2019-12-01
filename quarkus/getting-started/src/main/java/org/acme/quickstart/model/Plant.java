package org.acme.quickstart.model;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "plant", database = "test")
public class Plant extends PanacheMongoEntity {

    public String name;
    public int age;
    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm")
    public LocalDateTime lastWatered;

}