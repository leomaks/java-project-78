package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;

@NoArgsConstructor
public class MapSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean isRequiredShape = false;

    private int size = 0;

    private Map<String, BaseSchema> schemas;

    public final MapSchema required() {
        this.isRequired = true;
        return this;
    }
    public final MapSchema sizeof(int n) {
        this.size = n;
        return this;
    }
    public final void shape(Map<String, BaseSchema> validateSchema) {
        schemas = validateSchema;
        isRequiredShape = true;
    }

    @Override
    public boolean validateClass(Object input) {
        return (input == null) || (input instanceof Map);
    }


    public boolean validateShape(Map<String, Object> data) {
        return data.entrySet().stream().allMatch(x -> schemas.get(x.getKey()).isValid(x.getValue()));
    }
    @Override
    public ArrayList<Predicate<BaseSchema>> fillValidateList(Object input) {

        ArrayList<Predicate<BaseSchema>> predList = new ArrayList<>();

        Map<String, Object> data = (Map) input;

        predList.add(p -> !(isRequired && data == null));
        predList.add(p -> !(((size != 0) && (data.size() != size))));

        if (isRequiredShape) {
            predList.add(p -> validateShape(data));
        }
        return predList;
    }



}


