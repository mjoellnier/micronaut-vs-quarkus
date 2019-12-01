package org.acme.quickstart.model.codec;

import org.acme.quickstart.model.Animal;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class AnimalCodecProvider implements CodecProvider {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Animal.class) {
            return (Codec<T>) new AnimalCodec();
        }
        return null;
    }

}