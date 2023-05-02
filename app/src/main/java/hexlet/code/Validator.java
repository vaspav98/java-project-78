package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

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
