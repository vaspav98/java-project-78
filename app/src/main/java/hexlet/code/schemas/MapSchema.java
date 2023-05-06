package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addCheck(
                "basicCheck",
                value -> value instanceof Map || value == null
        );
    }

    public MapSchema required() {
        addCheck(
                "required",
                value -> value != null
        );
        return this;
    }

    public MapSchema sizeof(int size) {
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
        addCheck(
                "shape",
                value -> {
                    Map map = (Map) value;
                    for (Map.Entry<String, BaseSchema> entry : schemes.entrySet()) {
                        Object key = entry.getKey();
                        BaseSchema schema = entry.getValue();
                        if (!map.containsKey(key)) {
                            return false ;
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
