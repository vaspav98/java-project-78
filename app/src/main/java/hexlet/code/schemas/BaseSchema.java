package hexlet.code.schemas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseSchema {

    boolean isValid = true;
    Object data;
    Map<Method, Object> restrictions = new HashMap<>();

    public boolean isValid(Object input) throws InvocationTargetException, IllegalAccessException {
        this.data = input;
        isValid = true;
        for (Method restrictionMethod : restrictions.keySet()) {
            if (!isValid) {
                return false;
            }
            restrictionMethod.invoke(this);
        }
        return isValid;
    }
}
