package serializationStrategy;

import java.io.File;
import java.util.List;

public interface SerializationStrategy<T> {

    void serializeToFile(List<T> obj, File fileName);
    List<T> deserializeFromFile(File fileName, Class<T> elementType);
}
