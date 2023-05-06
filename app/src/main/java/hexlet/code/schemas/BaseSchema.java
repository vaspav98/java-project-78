package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Setter
@Getter
public class BaseSchema {

    private boolean isValid = true;
    private Object data;
    private Map<String, Predicate> restrictions = new HashMap<>();

    public final boolean isValid(Object input) {
        this.data = input;
        isValid = true;
        System.out.println("input data - " + data);
        for (String restrictionName : restrictions.keySet()) {
            if (!isValid) {
                return false;
            }
            isValid = restrictions.get(restrictionName).test(data);
        }
        return isValid;
    }

    protected final boolean getValid() {
        return this.isValid;
    }

    protected final void addCheck(String name, Predicate validate) {
        restrictions.put(name, validate);
    }

    @Override
    public String toString() {
        return "BaseSchema{" +
                "isValid=" + isValid +
                ", data=" + data +
                ", restrictions=" + restrictions +
                '}';
    }
}
