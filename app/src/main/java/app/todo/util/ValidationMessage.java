package app.todo.util;

/**
 * Created by Tobias Rojahn on 17.04.2017.
 */

public class ValidationMessage {

    private final String errorText;

    public ValidationMessage(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }

}
