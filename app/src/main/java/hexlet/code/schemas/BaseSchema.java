package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class BaseSchema {

    private boolean isValid = true;
    private Object data;
    private Map<Method, Object> restrictions = new HashMap<>();

    @SneakyThrows
    public final boolean isValid(Object input) {
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

    public final boolean getValid() {
        return this.isValid;
    }
}
