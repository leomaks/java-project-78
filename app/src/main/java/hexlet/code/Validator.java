package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;

public class Validator {
    public Validator() {
    }

    public static StringSchema string() {
        return new StringSchema();
    }

    public static NumberSchema number() {
        return new NumberSchema();
    }
}
