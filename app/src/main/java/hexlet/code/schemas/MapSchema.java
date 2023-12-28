package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.Map;

@NoArgsConstructor
public class MapSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean isValid = true;
    private int size = 0;
    private Map data;

    public MapSchema required() {
        this.isRequired = true;
        return this;
    }
    public MapSchema sizeof(int n) {
        this.size = n;
        return this;
    }
    @Override

    public boolean isValid(Object num) {

        if (!(num == null) && !(num instanceof Map)) {
            return  false;
        }

        data = (Map) num;

        validate();
        return isValid;
    }

    private void validate() {
        isValid = true;
        validateNotNull();
        validateSize();

    }
    private void validateNotNull() {
        if ((isRequired) && (data == null)) {
            isValid = false;
        }
    }

    private void validateSize() {
        if ((size != 0) && (data.size() != size)) {
            isValid = false;
        }
    }

}
