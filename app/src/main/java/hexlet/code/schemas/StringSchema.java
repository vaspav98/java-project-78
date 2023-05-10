package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck(
                "required",
                value -> value instanceof String
        );
    }

    public StringSchema required() {
        super.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        super.required = true;
        addCheck(
                "minLength",
                value -> value.toString().length() >= length
        );
        return this;
    }

    public StringSchema contains(String substring) {
        super.required = true;
        addCheck(
                "contains",
                value -> value.toString().contains(substring)
        );
        return this;
    }
}
