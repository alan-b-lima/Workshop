package json;

public class JsonObject implements JsonSerializable {

    private StringBuilder json;

    public JsonObject() {
        json = new StringBuilder("{");
    }

    public JsonObject add(String key, JsonSerializable value) {
        json.append(Json.stringify(key)).append(":")
                .append(value.toJson()).append(",");

        return this;
    }

    public JsonObject add(String key, String value) {
        json.append(Json.stringify(key)).append(":")
                .append(Json.stringify(value)).append(",");

        return this;
    }

    public JsonObject add(String key, Number value) {
        json.append(Json.stringify(key)).append(":")
                .append(Json.stringify(value)).append(",");

        return this;
    }

    public JsonObject add(String key, Character value) {
        json.append(Json.stringify(key)).append(":")
                .append(Json.stringify(value)).append(",");

        return this;
    }

    public JsonObject add(String key, Boolean value) {
        json.append(Json.stringify(key)).append(":")
                .append(Json.stringify(value)).append(",");

        return this;
    }

    @Override
    public String toJson() {
        if (json.charAt(json.length() - 1) == '{') {
            return "{}";
        }

        return json.substring(0, json.length() - 1) + "}";
    }
}
