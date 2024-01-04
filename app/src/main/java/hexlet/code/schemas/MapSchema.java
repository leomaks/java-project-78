package hexlet.code.schemas;

import java.util.Map;


public class MapSchema extends BaseSchema {

    public MapSchema() {
        addValidation(p -> (p == null) || (p instanceof Map));
    }

    public final MapSchema required() {
        addValidation(p -> p != null);
        return this;
    }
    public final MapSchema sizeof(int n) {
        addValidation(p -> ((Map) p).size() == n);
        return this;
    }
    public final void shape(Map<String, BaseSchema> schemas) {
        addValidation(p -> validateShape(schemas,  (Map) p));
    }


    public final boolean validateShape(Map<String, BaseSchema> schemas, Map<String, Object> input) {
        return input.entrySet().stream()
                .allMatch(x -> schemas.get(x.getKey()).isValid(x.getValue()));
    }

}


