package hexlet.code.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

        isValid = true;

        if (!(num == null) && !(num.getClass() == Integer.class)) {
            return  false;
        }

        data = (Integer) num;

        validate();
        return isValid;
    }
    private void validate() {

        isValid = true;

        if ((isRequired) && (data == null)) {
            isValid = false;
        }

        if ((data == null) && (isPositive) && isRequired) {
            isValid = false;
        }



        if (!(data == null) && isPositive && (data <= 0)) {
            isValid = false;
        }

        if (!(data == null) && (!(minBorder <= data) || !(data <= maxBorder))) {
            isValid = false;
        }

    }
}
