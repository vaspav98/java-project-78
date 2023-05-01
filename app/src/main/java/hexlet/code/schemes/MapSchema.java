package hexlet.code.schemes;

import java.lang.reflect.Method;
import java.util.Map;

public class MapSchema extends BaseSchema {

    void basicCheck() {
        isValid = data instanceof Map || data == null;
    }

    public MapSchema() throws NoSuchMethodException {
        Method method = MapSchema.class.getDeclaredMethod("basicCheck");
        restrictions.put(method, new Object());
    }


    public MapSchema required() throws NoSuchMethodException {
        Method method = MapSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void requiredLogic() {
        isValid = data != null;
    }

    public MapSchema sizeof(int size) throws NoSuchMethodException {
        Method method = MapSchema.class.getDeclaredMethod("sizeofLogic");
        restrictions.put(method, size);
        return this;
    }

    void sizeofLogic() throws NoSuchMethodException {
        Method key = MapSchema.class.getDeclaredMethod("sizeofLogic");
        int size = (int) restrictions.get(key);
        if (data == null) {
            isValid = false;
        } else {
            Map map = (Map) data;
            isValid = map.size() == size;
        }
    }
}























