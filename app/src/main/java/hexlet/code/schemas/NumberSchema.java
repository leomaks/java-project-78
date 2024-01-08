package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addValidation(p -> p == null || (p instanceof Integer));
    }

    public final NumberSchema required() {
        addNotNullValidation();
        return this;
    }

    public final NumberSchema positive() {
        addValidation(p -> (p == null) || (Integer) p > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addValidation(p -> (p == null) || (min <= (Integer) p && (Integer) p <= max));
        return this;
    }


}
