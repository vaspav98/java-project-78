package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    void basicCheck() {
        setValid(getData() instanceof Map || getData() == null);
    }

    @SneakyThrows
    public MapSchema() {
        Method method = MapSchema.class.getDeclaredMethod("basicCheck");
        getRestrictions().put(method, new Object());
    }

    @SneakyThrows
    public MapSchema required() {
        Method method = MapSchema.class.getDeclaredMethod("requiredLogic");
        getRestrictions().put(method, new Object());
        return this;
    }

    void requiredLogic() {
        setValid(getData() != null);
    }

    @SneakyThrows
    public MapSchema sizeof(int size) {
        Method method = MapSchema.class.getDeclaredMethod("sizeofLogic");
        getRestrictions().put(method, size);
        return this;
    }

    @SneakyThrows
    void sizeofLogic() {
        Method key = MapSchema.class.getDeclaredMethod("sizeofLogic");
        int size = (int) getRestrictions().get(key);
        if (getData() == null) {
            setValid(false);
        } else {
            Map map = (Map) getData();
            setValid(map.size() == size);
        }
    }

    @SneakyThrows
    public MapSchema shape(Map<String, BaseSchema> schemes) {
        Method method = MapSchema.class.getDeclaredMethod("shapeLogic");
        getRestrictions().put(method, schemes);
        return this;
    }

    @SneakyThrows
    void shapeLogic() {
        Method method = MapSchema.class.getDeclaredMethod("shapeLogic");
        Map<String, BaseSchema> schemes = (Map<String, BaseSchema>) getRestrictions().get(method);
        Map map = (Map) getData();

        for (Map.Entry<String, BaseSchema> schema : schemes.entrySet()) {
            Object key = schema.getKey();
            BaseSchema value = schema.getValue();
            if (map.containsKey(key)) {
                setValid(value.isValid(map.get(key)));
            }
            if (!getValid()) {
                return;
            }
        }
    }
}
