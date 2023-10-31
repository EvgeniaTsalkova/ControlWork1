package storages;

import entities.Manufacturer;
import serializationStrategy.JsonSerializationStrategy;
import serializationStrategy.OOSSerializationStrategy;
import serializationStrategy.ObjectSerializer;
import serializationStrategy.SerializationStrategy;

import java.io.File;
import java.util.List;

public class ManufacturerStorage {

//    File fileName = new File("manufacturers1.json");
//    private final SerializationStrategy<Manufacturer> serializationStrategy = new JsonSerializationStrategy<>();

    File fileName = new File("manufacturers1.dat");
    private final SerializationStrategy<Manufacturer> serializationStrategy = new OOSSerializationStrategy<>();
    private final ObjectSerializer<Manufacturer> serializer = new ObjectSerializer<>(serializationStrategy);


    public List<Manufacturer> readManufacturerList() {
        return serializer.deserializeObj(fileName, Manufacturer.class);
    }

    public void writeManufacturerList(List<Manufacturer> manufacturerList) {
        serializer.serializeObj(manufacturerList, fileName);
    }
}
