package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {

    private boolean isValid = true;
    private Map<String, Predicate> restrictions = new LinkedHashMap<>();
    protected boolean required;

    public final boolean isValid(Object data) {
        if (data == null || data.equals("")) {
            return !required;
        }
        isValid = true;
        for (String restrictionName : restrictions.keySet()) {
            if (!isValid) {
                return false;
            }
            isValid = restrictions.get(restrictionName).test(data);
        }
        return isValid;
    }

    protected final void addCheck(String name, Predicate validate) {
        restrictions.put(name, validate);
    }
}
