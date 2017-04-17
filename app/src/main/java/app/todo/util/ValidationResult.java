package app.todo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Rojahn on 17.04.2017.
 */

public class ValidationResult {

    private List<ValidationMessage> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addError(String errorMessage) {
        errors.add(new ValidationMessage(errorMessage));
    }

    public List<ValidationMessage> getErrors() {
        return errors;
    }
}
