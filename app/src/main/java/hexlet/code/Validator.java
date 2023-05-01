package hexlet.code;

public class Validator {

    public StringSchema string() throws NoSuchMethodException {
        return new StringSchema();
    }

    public NumberSchema number() throws NoSuchMethodException {
        return new NumberSchema();
    }



}
