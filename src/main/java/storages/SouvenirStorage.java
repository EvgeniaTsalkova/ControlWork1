package storages;

import entities.Souvenir;
import serializationStrategy.JsonSerializationStrategy;
import serializationStrategy.OOSSerializationStrategy;
import serializationStrategy.ObjectSerializer;
import serializationStrategy.SerializationStrategy;

import java.io.File;
import java.util.List;

public class SouvenirStorage {

//    File fileName = new File("souvenirs1.json");
//    private final SerializationStrategy<Souvenir> serializationStrategy = new JsonSerializationStrategy<>();

    File fileName = new File("souvenirs1.dat");
    private final SerializationStrategy<Souvenir> serializationStrategy = new OOSSerializationStrategy<>();
    private final ObjectSerializer<Souvenir> serializer = new ObjectSerializer<>(serializationStrategy);

    public List<Souvenir> readSouvenirList() {
        return serializer.deserializeObj(fileName, Souvenir.class);
    }

    public void writeSouvenirList(List<Souvenir> souvenirList) {
        serializer.serializeObj(souvenirList, fileName);
    }
}
