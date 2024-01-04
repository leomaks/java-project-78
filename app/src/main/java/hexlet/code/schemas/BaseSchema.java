package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private ArrayList<Predicate<BaseSchema>> validations = new ArrayList<>();
    public abstract boolean validateClass(Object str);
    public Object data;


    public final boolean isValid(Object input) {

        data = input;

        if (!validateClass(input)) {
            return  false;
        }

        return validations.stream().allMatch(p -> p.test(this));
    }


    public final void addValidation(Predicate<BaseSchema> pred) {
        validations.add(pred);
    }

}


