package app.todo.businesslogic;

import app.todo.model.NoteBE;
import app.todo.util.ValidationResult;

/**
 * Created by Tobias Rojahn on 17.04.2017.
 */

public class NoteBL {

    public NoteBL() {
    }

    public ValidationResult validateNote(NoteBE note) {
        //TODO Anne: Implement
        if(note.getTitle() == null){
            ValidationResult err = new ValidationResult();
            err.addError("Titel null");
        }
        return new ValidationResult();
    }
}
