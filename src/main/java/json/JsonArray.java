package json;

public class JsonArray implements JsonSerializable {

    private StringBuilder json;

    public JsonArray() {
        json = new StringBuilder("[");
    }

    public JsonArray add(JsonSerializable element) {
        json.append(element.toJson()).append(",");

        return this;
    }
    
    public JsonArray add(JsonSerializable[] elements) {
        for (JsonSerializable element : elements) {
            json.append(element.toJson()).append(",");
        }

        return this;
    }

    @Override
    public String toJson() {
        if (json.charAt(json.length() - 1) == '[') {
            return "[]";
        }

        return json.substring(0, json.length() - 1) + "]";
    }
}
