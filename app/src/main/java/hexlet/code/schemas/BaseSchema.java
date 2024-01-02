package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private ArrayList<Predicate<BaseSchema>> validateList = new ArrayList<>();
    public abstract boolean validateClass(Object str);


    public boolean isValid(Object input) {


        if (!validateClass(input)) {
            return  false;
        }

        validateList = fillValidateList(input);
        return validateList.stream().allMatch(p -> checkCondition(p));
    }


    public abstract ArrayList<Predicate<BaseSchema>> fillValidateList(Object input);

    private boolean checkCondition(Predicate<BaseSchema> pred) {
        return pred.test(this);
    }


}


