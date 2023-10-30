package storages;

import entities.Souvenir;
import serializationStrategy.JsonSerializationStrategy;
import serializationStrategy.OOSSerializationStrategy;
import serializationStrategy.ObjectSerializer;
import serializationStrategy.SerializationStrategy;

import java.io.File;
import java.util.List;

public class SouvenirStorage {

  //      File fileName = new File("souvenirs.json");
        File fileName = new File("souvenirs1.dat");

//        private final SerializationStrategy<Souvenir> jsonStrategy = new JsonSerializationStrategy<>();
//        private final ObjectSerializer<Souvenir> serializer = new ObjectSerializer<>(jsonStrategy);
        private final SerializationStrategy<Souvenir> oosStrategy = new OOSSerializationStrategy<>();
        private final ObjectSerializer<Souvenir> serializer = new ObjectSerializer<>(oosStrategy);

        public List<Souvenir> readSouvenirList(){
            return serializer.deserializeObj(fileName, Souvenir.class);
        }

        public void writeSouvenirList(List<Souvenir> souvenirList) {
                serializer.serializeObj(souvenirList, fileName);
        }
}
