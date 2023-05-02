package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {

    void basicCheck() {
        isValid = data instanceof Integer || data == null;
    }

    @SneakyThrows
    public NumberSchema() {
        Method method = NumberSchema.class.getDeclaredMethod("basicCheck");
        restrictions.put(method, new Object());
    }

    @SneakyThrows
    public NumberSchema required() {
        Method method = NumberSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void requiredLogic() {
        isValid = data != null;
    }

    @SneakyThrows
    public NumberSchema positive() {
        Method method = NumberSchema.class.getDeclaredMethod("positiveLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void positiveLogic() {
        isValid = data == null || (int) data > 0;
    }

    @SneakyThrows
    public NumberSchema range(int min, int max) {
        Method method = NumberSchema.class.getDeclaredMethod("rangeLogic");
        List<Integer> range = new ArrayList<>(List.of(min, max));
        restrictions.put(method, range);
        return this;
    }

    @SneakyThrows
    void rangeLogic() {
        Method key = NumberSchema.class.getDeclaredMethod("rangeLogic");
        List<Integer> range = (List<Integer>) restrictions.get(key);
        int min = range.get(0);
        int max = range.get(1);
        if (data == null) {
            isValid = false;
        } else {
            isValid = (int) data >= min && (int) data <= max;
        }
    }
}
