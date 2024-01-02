package hexlet.code.schemas;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.function.Predicate;


@NoArgsConstructor

public class StringSchema extends BaseSchema {
    private boolean isRequired = false;
    private String substring = "";
    private int minLength = 0;


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
    public boolean validateClass(Object str) {
        return  (str == null) || (str.getClass() == String.class);
    }


    @Override
    public ArrayList<Predicate<BaseSchema>> fillValidateList(Object input) {
        ArrayList<Predicate<BaseSchema>> predList = new ArrayList<>();
        var data = (String) input;

        predList.add(p -> !(isRequired && (data == null || data == "")));
        predList.add(p -> !(data == null && !substring.equals("")));
        predList.add(p -> !((data != null) && !data.contains(substring)));
        predList.add(p -> !((data != null) && (data.length() < minLength)));

        return predList;
    }

}
