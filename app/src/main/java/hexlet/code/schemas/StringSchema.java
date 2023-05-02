package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;

public final class StringSchema extends BaseSchema {

    void basicCheck() {
        setValid(getData() instanceof String || getData() == null);
    }

    @SneakyThrows
    public StringSchema() {
        Method method = StringSchema.class.getDeclaredMethod("basicCheck");
        getRestrictions().put(method, new Object());
    }

    @SneakyThrows
    public StringSchema required() {
        Method method = StringSchema.class.getDeclaredMethod("requiredLogic");
        getRestrictions().put(method, new Object());
        return this;
    }

    void requiredLogic() {
        setValid(getData() != null && !getData().equals(""));
    }

    @SneakyThrows
    public StringSchema minLength(int length) {
        Method method = StringSchema.class.getDeclaredMethod("minLengthLogic");
        getRestrictions().put(method, length);
        return this;
    }

    @SneakyThrows
    void minLengthLogic() {
        Method key = StringSchema.class.getDeclaredMethod("minLengthLogic");
        if (getData() == null) {
            setValid(false);
        } else {
            setValid(getData().toString().length() >= (Integer) getRestrictions().get(key));
        }

    }

    @SneakyThrows
    public StringSchema contains(String substring) {
        Method method = StringSchema.class.getDeclaredMethod("containsLogic");
        getRestrictions().put(method, substring);
        return this;
    }

    @SneakyThrows
    void containsLogic() {
        Method key = StringSchema.class.getDeclaredMethod("containsLogic");
        String substring = (String) getRestrictions().get(key);
        if (getData() == null) {
            setValid(false);
        } else {
            setValid(getData().toString().contains(substring));
        }
    }
}
