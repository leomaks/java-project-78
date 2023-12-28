package hexlet.code.schemas;

public abstract class BaseSchema {
    private boolean isValid = true;
    public abstract boolean isValid(Object str);

}


