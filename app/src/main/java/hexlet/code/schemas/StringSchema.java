package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck(
                "basicCheck",
                value -> value instanceof String || value == null
        );
    }

    public StringSchema required() {
        addCheck(
                "required",
                value -> value != null && !value.equals("")
        );
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(
                "minLength",
                value -> value != null && value.toString().length() >= length
        );
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(
                "contains",
                value -> value != null && value.toString().contains(substring)
        );
        return this;
    }
}
