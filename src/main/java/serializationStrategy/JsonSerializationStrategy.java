package serializationStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializationStrategy<T> implements SerializationStrategy<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void serializeToFile(List<T> obj, File fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            objectMapper.writeValue(fos, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> deserializeFromFile(File fileName, Class<T> elementType) {
        List<T> objList = new ArrayList<>();
        if (fileName.length() == 0) {
            return objList;
        }
        try {
            objList = objectMapper.readValue(fileName,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return objList;
    }
}
