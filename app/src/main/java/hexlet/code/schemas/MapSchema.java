package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addCheck(
                "required",
                value -> value instanceof Map
        );
    }

    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        super.required = true;
        addCheck(
                "sizeof",
                value -> {
                    Map map = (Map) value;
                    return map.size() == size;
                }
        );
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemes) {
        super.required = true;
        addCheck(
                "shape",
                value -> {
                    Map map = (Map) value;
                    for (Map.Entry<String, BaseSchema> entry : schemes.entrySet()) {
                        Object key = entry.getKey();
                        BaseSchema schema = entry.getValue();
                        if (!map.containsKey(key)) {
                            return false;
                        }
                        if (!schema.isValid(map.get(key))) {
                            return false;
                        }
                    }
                    return true;
                }
        );
        return this;
    }
}
