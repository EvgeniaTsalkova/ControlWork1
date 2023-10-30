package serializationStrategy;

import java.io.File;
import java.util.List;

public class ObjectSerializer<T> {
    private final SerializationStrategy<T> serializationStrategy;

    public ObjectSerializer(SerializationStrategy<T> serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
    }

    public void serializeObj(List<T> objList, File fileName) {
        serializationStrategy.serializeToFile(objList, fileName);
    }

    public List<T> deserializeObj(File fileName, Class<T> elementType) {
        return serializationStrategy.deserializeFromFile(fileName, elementType);
    }
}
