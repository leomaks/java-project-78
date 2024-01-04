package hexlet.code.schemas;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class StringSchema extends BaseSchema {


    public final StringSchema required() {
        addValidation(p -> (p.data != null) && (p.data != ""));
        return this;
    }

    public final StringSchema minLength(int min) {
        addValidation(v -> (v == null) || ((String) v.data).length() >= min);
        return this;
    }

    public final StringSchema contains(String substring) {
        addValidation(p -> (p == null) || ((String) p.data).contains(substring));
        return this;
    }

    @Override
    public final boolean validateClass(Object str) {
        return  (str == null) || (str.getClass() == String.class);
    }


}
