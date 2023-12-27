package hexlet.code.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private boolean isValid = true;


    private int minBorder = Integer.MIN_VALUE;
    private int maxBorder = Integer.MAX_VALUE;
    private Integer data;

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        minBorder = min;
        maxBorder = max;
        return this;
    }
    @Override
    public boolean isValid(Object num) {


        if (!(num == null) && !(num.getClass() == Integer.class)) {
            return  false;
        }

        var result = new NumberSchema(isRequired, isPositive, isValid, minBorder, maxBorder, (Integer) num);

        validate(result);
        return result.isValid;
    }
    private void validate(NumberSchema result) {

        if ((result.isRequired) && (result.data == null)) {
            result.isValid = false;
        }

        if ((result.data == null) && (result.isPositive) && result.isRequired) {
            result.isValid = false;
        }



        if (!(result.data == null) && result.isPositive && (result.data <= 0)) {
            result.isValid = false;
        }

        if (!(result.data == null) && (!(result.minBorder <= result.data) || !(result.data <= result.maxBorder))) {
            result.isValid = false;
        }

    }
}
