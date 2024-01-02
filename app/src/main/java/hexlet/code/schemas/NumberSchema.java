package hexlet.code.schemas;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.function.Predicate;

@NoArgsConstructor
public class NumberSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;


    private int minBorder = Integer.MIN_VALUE;
    private int maxBorder = Integer.MAX_VALUE;


    public final NumberSchema required() {
        isRequired = true;
        return this;
    }

    public final NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public final NumberSchema range(int min, int max) {
        minBorder = min;
        maxBorder = max;
        return this;
    }

    @Override
    public final boolean validateClass(Object str) {
        return (str == null) || (str.getClass() == Integer.class);
    }

    @Override
    public final ArrayList<Predicate<BaseSchema>> fillValidateList(Object input) {

        ArrayList<Predicate<BaseSchema>> predList = new ArrayList<>();
        var data = (Integer) input;


        predList.add(p -> !(isRequired && data == null));
        predList.add(p ->  !(data == null && isPositive && isRequired));

        predList.add(p -> !((data != null) && isPositive && (data <= 0)));
        predList.add(p -> !((data != null) && ((minBorder > data) || (data > maxBorder))));

        return predList;

    }


}
