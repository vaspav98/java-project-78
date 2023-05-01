package hexlet.code.schemes;

import java.lang.reflect.Method;

public class StringSchema extends BaseSchema {

    void basicCheck() {
        isValid = data instanceof String || data == null;
    }

    public StringSchema() throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("basicCheck");
        restrictions.put(method, new Object());
    }


    public StringSchema required() throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void requiredLogic() {
        isValid = data != null && !data.equals("");
    }

    public StringSchema minLength(int length) throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("minLengthLogic");
        restrictions.put(method, length);
        return this;
    }

    void minLengthLogic() throws NoSuchMethodException {
        Method key = StringSchema.class.getDeclaredMethod("minLengthLogic");
        if (data == null) {
            isValid = false;
        } else {
            isValid = data.toString().length() >= (Integer) restrictions.get(key);
        }

    }

    public StringSchema contains(String substring) throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("containsLogic");
        restrictions.put(method, substring);
        return this;
    }

    void containsLogic() throws NoSuchMethodException {
        Method key = StringSchema.class.getDeclaredMethod("containsLogic");
        String substring = (String) restrictions.get(key);
        if (data == null) {
            isValid = false;
        } else {
            isValid = data.toString().contains(substring);
        }
    }
}
