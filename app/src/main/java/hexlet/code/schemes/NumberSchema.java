package hexlet.code.schemes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {

    void basicCheck() {
        isValid = data instanceof Integer || data == null;
    }

    public NumberSchema() throws NoSuchMethodException {
        Method method = NumberSchema.class.getDeclaredMethod("basicCheck");
        restrictions.put(method, new Object());
    }


    public NumberSchema required() throws NoSuchMethodException {
        Method method = NumberSchema.class.getDeclaredMethod("requiredLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void requiredLogic() {
        isValid = data != null;
    }

    public NumberSchema positive() throws NoSuchMethodException {
        Method method = NumberSchema.class.getDeclaredMethod("positiveLogic");
        restrictions.put(method, new Object());
        return this;
    }

    void positiveLogic() {
        isValid = data == null || (int) data > 0;
    }

    public NumberSchema range(int min, int max) throws NoSuchMethodException {
        Method method = NumberSchema.class.getDeclaredMethod("rangeLogic");
        List<Integer> range = new ArrayList<>(List.of(min, max));
        restrictions.put(method, range);
        return this;
    }

    void rangeLogic() throws NoSuchMethodException {
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























