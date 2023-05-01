package hexlet.code;

import hexlet.code.schemes.MapSchema;
import hexlet.code.schemes.NumberSchema;
import hexlet.code.schemes.StringSchema;

public class Validator {

    public StringSchema string() throws NoSuchMethodException {
        return new StringSchema();
    }

    public NumberSchema number() throws NoSuchMethodException {
        return new NumberSchema();
    }

    public MapSchema map() throws NoSuchMethodException {
        return new MapSchema();
    }
}
