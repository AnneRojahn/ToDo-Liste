package app.todo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import app.todo.model.NoteBE;

/**
 * Created by 1234 on 17.04.2017.
 */
public class NoteDAO {

    private static final String TABLE_NAME = "NOTE";
    private static final String COLUMN_NAME_ID = "_id";
    private static final String COLUMN_NAME_TEXT = "TEXT";

    private static final String[] ALL_COLUMNS = {COLUMN_NAME_ID, COLUMN_NAME_TEXT};

    static final String SQL_CREATE_TABLE_NOTE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME_TEXT + " TEXT)";
    static final String SQL_DELETE_TABLE_NOTE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public NoteDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void save(NoteBE note) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TEXT, note.getText());
        database.insert(TABLE_NAME, null, values);
    }

    public void delete(NoteBE note) {
        String selection = COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {String.valueOf(note.getId())};
        database.delete(TABLE_NAME, selection, selectionArgs);
    }

    public List<NoteBE> loadAll() {
        String sortOrder = COLUMN_NAME_ID + " ASC";
        Cursor cursor = database.query(TABLE_NAME, ALL_COLUMNS, null, null, null, null, sortOrder);
        List<NoteBE> result = getEntitiesFromCursor(cursor);
        return result;
    }

    @NonNull
    private List<NoteBE> getEntitiesFromCursor(Cursor cursor) {
        List<NoteBE> result = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            NoteBE note = new NoteBE();
            note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ID)));
            note.setText(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TEXT)));
            result.add(note);
        }
        cursor.close();
        return result;
    }

    public void closeConnection() {
        databaseHelper.close();
    }

}
