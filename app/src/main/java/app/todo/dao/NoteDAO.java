package app.todo.dao;

import java.util.ArrayList;
import java.util.List;

import app.todo.model.NoteBE;

/**
 * Created by 1234 on 17.04.2017.
 */
public class NoteDAO {

    public void save(NoteBE note) {

    }

    public List<NoteBE> loadAll() {
        List<NoteBE> dummy = new ArrayList<>();
        dummy.add(new NoteBE("Test Note 1"));
        dummy.add(new NoteBE("Test Note 2"));
        dummy.add(new NoteBE("Test Note 3"));
        return dummy;
    }

}
