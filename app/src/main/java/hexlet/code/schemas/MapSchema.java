package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.Map;


@NoArgsConstructor
public class MapSchema extends BaseSchema {


    public final MapSchema required() {
        addValidation(p -> p.data != null);
        return this;
    }
    public final MapSchema sizeof(int n) {
        addValidation(p -> ((Map) p.data).size() == n);
        return this;
    }
    public final void shape(Map<String, BaseSchema> schemas) {
        addValidation(p -> validateShape(schemas));
    }

    @Override
    public final boolean validateClass(Object input) {
        return (input == null) || (input instanceof Map);
    }


    public final boolean validateShape(Map<String, BaseSchema> schemas) {

        return ((Map<String, Object>) this.data).entrySet().stream()
                .allMatch(x -> schemas.get(x.getKey()).isValid(x.getValue()));
    }

}


