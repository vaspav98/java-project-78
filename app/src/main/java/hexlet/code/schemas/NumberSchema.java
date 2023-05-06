package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck(
                "basicCheck",
                value -> value instanceof Integer || value == null

        );
    }

    public NumberSchema required() {
        addCheck(
                "required",
                value -> value != null
        );
        return this;
    }

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> value == null || (int) value > 0
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(
                "range",
                value -> value != null && (int) value >= min && (int) value <= max
        );
        return this;
    }
}
