package hexlet.code.schemas;



public class StringSchema extends BaseSchema {
    public StringSchema() {
        addValidation(p -> p == null || p instanceof String);
    }

    public final StringSchema required() {
        addValidation(p -> (p != null) && (p != ""));
        return this;
    }

    public final StringSchema minLength(int min) {
        addValidation(p -> (p == null) || ((String) p).length() >= min);
        return this;
    }

    public final StringSchema contains(String substring) {
        addValidation(p -> (p == null) || ((String) p).contains(substring));
        return this;
    }



}
