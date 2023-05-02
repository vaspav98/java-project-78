package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseSchema {

    boolean isValid = true;
    Object data;
    Map<Method, Object> restrictions = new HashMap<>();

    @SneakyThrows
    public boolean isValid(Object input) {
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
