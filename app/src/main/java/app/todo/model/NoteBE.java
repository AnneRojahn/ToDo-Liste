package app.todo.model;

import java.sql.Date;

/**
 * Created by 1234 on 17.04.2017.
 */
public class NoteBE {

    private int id;

    private String text;

    private String title;

    private Date dueDate;

    private StatusEnum status;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
