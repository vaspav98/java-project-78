package hexlet.code;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseSchema {

    protected boolean isValid = true;
    protected Object data;
    protected Map<Method, Object> restrictions = new HashMap<>();

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
