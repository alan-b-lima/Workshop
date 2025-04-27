package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Json {

    private static final Gson GSON = new Gson();

    private Json() {

    }

    public static String stringify(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T parse(String json) {
        TypeToken<T> type = new TypeToken<>() {};
        return GSON.fromJson(json, type);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
