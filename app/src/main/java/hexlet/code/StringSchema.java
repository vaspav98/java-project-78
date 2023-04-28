package hexlet.code;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class StringSchema {
    private boolean isValid = true;
    private Object data;
    private Map<Method, Object> restrictions = new HashMap<>();


    public StringSchema required() throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    private void requiredLogic() {
        isValid = data != null && !data.equals("");
    }

    public StringSchema minLength(int length) throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("minLengthLogic");
        restrictions.put(method, length);
        return this;
    }

    private void minLengthLogic() throws NoSuchMethodException {
        Method key = StringSchema.class.getDeclaredMethod("minLengthLogic");
        isValid = data.toString().length() >= (Integer) restrictions.get(key);
    }

    public StringSchema contains(String substring) throws NoSuchMethodException {
        Method method = StringSchema.class.getDeclaredMethod("containsLogic");
        restrictions.put(method, substring);
        return this;
    }

    private void containsLogic() throws NoSuchMethodException {
        Method key = StringSchema.class.getDeclaredMethod("containsLogic");
        String substring = (String) restrictions.get(key);
        if (data == null) {
            isValid = false;
        } else {
            isValid = data.toString().contains(substring);
        }
    }

    public boolean isValid(Object input) throws InvocationTargetException, IllegalAccessException {
        this.data = input;
        isValid = true;
        if (!(this.data instanceof String) && data != null) {
            return false;
        }
        for (Method restrictionMethod : restrictions.keySet()) {
            if (!isValid) {
                return false;
            }
            restrictionMethod.invoke(this);
        }
        return isValid;
    }
}























