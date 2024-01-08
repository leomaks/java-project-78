package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private ArrayList<Predicate<Object>> validations = new ArrayList<>();

    public final boolean isValid(Object input) {
        return validations.stream().allMatch(p -> p.test(input));
    }

    public final void addValidation(Predicate<Object> pred) {
        validations.add(pred);
    }

    public final void addNotNullValidation() {
        addValidation(p -> p != null);
    }

}


