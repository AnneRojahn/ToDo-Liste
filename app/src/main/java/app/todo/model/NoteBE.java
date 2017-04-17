package app.todo.model;

/**
 * Created by 1234 on 17.04.2017.
 */
public class NoteBE {

    private int id;

    private String text;

    public NoteBE() {
    }

    public NoteBE(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
