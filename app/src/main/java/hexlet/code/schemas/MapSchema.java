package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.Map;

public class MapSchema extends BaseSchema {

    void basicCheck() {
        isValid = data instanceof Map || data == null;
    }

    @SneakyThrows
    public MapSchema() {
        Method method = MapSchema.class.getDeclaredMethod("basicCheck");
        restrictions.put(method, new Object());
    }

    @SneakyThrows
    public MapSchema required() {
        Method method = MapSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void requiredLogic() {
        isValid = data != null;
    }

    @SneakyThrows
    public MapSchema sizeof(int size) {
        Method method = MapSchema.class.getDeclaredMethod("sizeofLogic");
        restrictions.put(method, size);
        return this;
    }

    @SneakyThrows
    void sizeofLogic() {
        Method key = MapSchema.class.getDeclaredMethod("sizeofLogic");
        int size = (int) restrictions.get(key);
        if (data == null) {
            isValid = false;
        } else {
            Map map = (Map) data;
            isValid = map.size() == size;
        }
    }

    @SneakyThrows
    public MapSchema shape(Map<String, BaseSchema> schemes) {
        Method method = MapSchema.class.getDeclaredMethod("shapeLogic");
        restrictions.put(method, schemes);
        return this;
    }

    @SneakyThrows
    void shapeLogic() {
        Method method = MapSchema.class.getDeclaredMethod("shapeLogic");
        Map<String, BaseSchema> schemes = (Map<String, BaseSchema>) restrictions.get(method);
        Map map = (Map) data;

        for (Map.Entry<String, BaseSchema> schema : schemes.entrySet()) {
            Object key = schema.getKey();
            BaseSchema value = schema.getValue();
            if (map.containsKey(key)) {
                isValid = value.isValid(map.get(key));
            }
            if (!isValid) {
                return;
            }
        }
    }
}
