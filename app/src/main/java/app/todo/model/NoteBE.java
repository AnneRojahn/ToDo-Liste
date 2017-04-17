package app.todo.model;

/**
 * Created by 1234 on 17.04.2017.
 */
public class NoteBE {

    private String text;

    public NoteBE(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
