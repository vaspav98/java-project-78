package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck(
                "required",
                value -> value instanceof Integer
        );
    }

    public NumberSchema required() {
        super.required = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> (int) value > 0
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        super.required = true;
        addCheck(
                "range",
                value -> (int) value >= min && (int) value <= max
        );
        return this;
    }
}
