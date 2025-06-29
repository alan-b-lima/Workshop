package edu.ajan.model.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class JsonHandler {

    private static final Path ROOT_FILEPATH = Path.of(".workshop");

    private static final Path CARETAKER_FILEPATH = ROOT_FILEPATH.resolve("caretaker.json");

    private static final Path SNAPSHOT_FILEPATH = ROOT_FILEPATH.resolve("snapshot");

    private static final Gson GSON = new Gson();

    private JsonHandler() {

    }

    public static Path getCaretakerFilepath() {
        return CARETAKER_FILEPATH;
    }

    public static Path getSnapshotFilepath(int id) {
        return SNAPSHOT_FILEPATH.resolve(String.format("wkshp.%08x.json", id));
    }

    private static <T> Type type() {
        return new TypeToken<T>() {}.getType();
    }

    public static <T> boolean save(T object, Path filename) {
        try {
            FileWriter writer = new FileWriter(filename.toFile());
            GSON.toJson(object, JsonHandler.<T>type(), writer);

            return true;
        } catch (IOException _) {
            return false;
        }
    }

    public static <T> T load(Path filename) {
        try {
            FileReader reader = new FileReader(filename.toFile());
            return GSON.fromJson(reader, JsonHandler.<T>type());
        } catch (IOException _) {
            return null;
        }
    }
}