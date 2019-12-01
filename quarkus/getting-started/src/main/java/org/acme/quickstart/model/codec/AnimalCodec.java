package org.acme.quickstart.model.codec;

import java.util.UUID;

import com.mongodb.MongoClient;

import org.acme.quickstart.model.Animal;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class AnimalCodec implements CollectibleCodec<Animal> {

    private final Codec<Document> documentCodec;

    public AnimalCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public void encode(BsonWriter writer, Animal value, EncoderContext encoderContext) {
        Document doc = new Document();
        doc.put("name", value.getName());
        doc.put("species", value.getSpecies());
        doc.put("age", value.getAge());
        documentCodec.encode(writer, doc, encoderContext);
    }

    @Override
    public Class<Animal> getEncoderClass() {
        return Animal.class;
    }

    @Override
    public Animal decode(BsonReader reader, DecoderContext decoderContext) {
        Document doc = documentCodec.decode(reader, decoderContext);
        Animal animal = new Animal();
        if (doc.getString("id") != null) {
            animal.setId(doc.getString("id"));
        }
        animal.setName(doc.getString("name"));
        animal.setSpecies(doc.getString("species"));
        animal.setAge(doc.getInteger("age"));
        return animal;
    }

    @Override
    public Animal generateIdIfAbsentFromDocument(Animal document) {
        if (!documentHasId(document)) {
            document.setId(UUID.randomUUID().toString());
        }
        return document;
    }

    @Override
    public boolean documentHasId(Animal document) {
        return document.getId() != null;
    }

    @Override
    public BsonValue getDocumentId(Animal document) {
        return new BsonString(document.getId());
    }

}