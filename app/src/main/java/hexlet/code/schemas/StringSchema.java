package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;

public class StringSchema extends BaseSchema {

    void basicCheck() {
        isValid = data instanceof String || data == null;
    }

    @SneakyThrows
    public StringSchema() {
        Method method = StringSchema.class.getDeclaredMethod("basicCheck");
        restrictions.put(method, new Object());
    }

    @SneakyThrows
    public StringSchema required() {
        Method method = StringSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void requiredLogic() {
        isValid = data != null && !data.equals("");
    }

    @SneakyThrows
    public StringSchema minLength(int length) {
        Method method = StringSchema.class.getDeclaredMethod("minLengthLogic");
        restrictions.put(method, length);
        return this;
    }

    @SneakyThrows
    void minLengthLogic() {
        Method key = StringSchema.class.getDeclaredMethod("minLengthLogic");
        if (data == null) {
            isValid = false;
        } else {
            isValid = data.toString().length() >= (Integer) restrictions.get(key);
        }

    }

    @SneakyThrows
    public StringSchema contains(String substring) {
        Method method = StringSchema.class.getDeclaredMethod("containsLogic");
        restrictions.put(method, substring);
        return this;
    }

    @SneakyThrows
    void containsLogic() {
        Method key = StringSchema.class.getDeclaredMethod("containsLogic");
        String substring = (String) restrictions.get(key);
        if (data == null) {
            isValid = false;
        } else {
            isValid = data.toString().contains(substring);
        }
    }
}
