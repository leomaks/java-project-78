package hexlet.code.schemas;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberSchema extends BaseSchema {


    public final NumberSchema required() {
        addValidation(p -> p.data != null);
        return this;
    }

    public final NumberSchema positive() {
        addValidation(p -> (data == null) || (Integer) p.data > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addValidation(p -> (data == null) || (min <= (Integer) p.data && (Integer) p.data <= max));
        return this;
    }

    @Override
    public final boolean validateClass(Object str) {
        return (str == null) || (str.getClass() == Integer.class);
    }


}
