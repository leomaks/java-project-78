package hexlet.code.schemas;

public abstract class BaseSchema {
    private boolean isValid = true;
    public final boolean isValid(Object str) {
        return isValid;
    }

}


