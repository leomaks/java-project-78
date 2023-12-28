package hexlet.code.schemas;
import lombok.NoArgsConstructor;


@NoArgsConstructor

public class StringSchema extends BaseSchema {
    private boolean isRequired = false;
    private String substring = "";
    private boolean isValid = true;


    private int minLength = 0;
    private String data;



    public final StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public final StringSchema minLength(int min) {
        this.minLength = min;
        return this;
    }

    public final StringSchema contains(String string) {
        this.substring = string;
        return this;
    }

    @Override
    public final boolean isValid(Object str) {

        if (!(str == null) && !(str.getClass() == String.class)) {
            return  false;
        }

        data = (String) str;

        validate();
        return isValid;
    }

    private void validate() {

        isValid = true;
        validateNotNull();
        validateSubstring();
        validateMinLength();

    }

    private void validateNotNull() {
        if ((isRequired) && (data == null || data == "")) {
            isValid = false;
        }
    }

    private void validateSubstring() {

        if ((data == null) && (!substring.equals(""))) {
            isValid = false;
        }

        if (!(data == null) && !data.contains(substring)) {
            isValid = false;
        }
    }

    private void validateMinLength() {
        if (!(data == null) && (data.length() < minLength)) {
            isValid = false;
        }
    }

}
