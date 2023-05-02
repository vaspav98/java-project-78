package hexlet.code.schemas;

import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class NumberSchema extends BaseSchema {

    void basicCheck() {
        setValid(getData() instanceof Integer || getData() == null);
    }

    @SneakyThrows
    public NumberSchema() {
        Method method = NumberSchema.class.getDeclaredMethod("basicCheck");
        getRestrictions().put(method, new Object());
    }

    @SneakyThrows
    public NumberSchema required() {
        Method method = NumberSchema.class.getDeclaredMethod("requiredLogic");
        getRestrictions().put(method, new Object());
        return this;
    }

    void requiredLogic() {
        setValid(getData() != null);
    }

    @SneakyThrows
    public NumberSchema positive() {
        Method method = NumberSchema.class.getDeclaredMethod("positiveLogic");
        getRestrictions().put(method, new Object());
        return this;
    }

    void positiveLogic() {
        setValid(getData() == null || (int) getData() > 0);
    }

    @SneakyThrows
    public NumberSchema range(int min, int max) {
        Method method = NumberSchema.class.getDeclaredMethod("rangeLogic");
        List<Integer> range = new ArrayList<>(List.of(min, max));
        getRestrictions().put(method, range);
        return this;
    }

    @SneakyThrows
    void rangeLogic() {
        Method key = NumberSchema.class.getDeclaredMethod("rangeLogic");
        List<Integer> range = (List<Integer>) getRestrictions().get(key);
        int min = range.get(0);
        int max = range.get(1);
        if (getData() == null) {
            setValid(false);
        } else {
            setValid((int) getData() >= min && (int) getData() <= max);
        }
    }
}
