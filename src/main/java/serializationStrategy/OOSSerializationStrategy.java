package serializationStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OOSSerializationStrategy<T> implements SerializationStrategy<T> {
    @Override
    public void serializeToFile(List<T> obj, File fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            objList = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objList;
    }
}
