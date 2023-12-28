package hexlet.code.schemas;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private boolean isValid = true;


    private int minBorder = Integer.MIN_VALUE;
    private int maxBorder = Integer.MAX_VALUE;
    private Integer data;

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
    public boolean isValid(Object num) {

        if (!(num == null) && !(num.getClass() == Integer.class)) {
            return  false;
        }

        data = (Integer) num;

        validate();
        return isValid;
    }

    private void validate() {
        isValid = true;
        validateNotNull();
        validatePositive();
        validateRange();
    }
    private void validateNotNull() {
        if ((isRequired) && (data == null)) {
            isValid = false;
        }
    }

    private void validatePositive() {
        if ((data == null) && (isPositive) && isRequired) {
            isValid = false;
        }

        if (!(data == null) && isPositive && (data <= 0)) {
            isValid = false;
        }
    }

    private void validateRange() {
        if (!(data == null) && (!(minBorder <= data) || !(data <= maxBorder))) {
            isValid = false;
        }
    }

}
