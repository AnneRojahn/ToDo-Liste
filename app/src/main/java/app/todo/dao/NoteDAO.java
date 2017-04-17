package app.todo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import app.todo.model.NoteBE;
import app.todo.model.StatusEnum;

/**
 * Created by 1234 on 17.04.2017.
 */
public class NoteDAO {

    private static final String TABLE_NAME = "NOTE";
    private static final String COLUMN_NAME_ID = "_id";
    private static final String COLUMN_NAME_TITLE = "TITLE";
    private static final String COLUMN_NAME_TEXT = "TEXT";
    private static final String COLUMN_NAME_DUE_DATE = "DUE_DATE";
    private static final String COLUMN_NAME_STATUS = "STATUS";

    private static final String[] ALL_COLUMNS = {COLUMN_NAME_ID, COLUMN_NAME_TITLE, COLUMN_NAME_TEXT, COLUMN_NAME_DUE_DATE, COLUMN_NAME_STATUS};

    static final String SQL_CREATE_TABLE_NOTE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME_TITLE + " TEXT,"
                    + COLUMN_NAME_TEXT + " TEXT,"
                    + COLUMN_NAME_DUE_DATE + " TEXT,"
                    + COLUMN_NAME_STATUS + " TEXT)";

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
        values.put(COLUMN_NAME_TITLE, note.getTitle());
        values.put(COLUMN_NAME_DUE_DATE, note.getDueDate() == null ? null : note.getDueDate().toString());
        values.put(COLUMN_NAME_STATUS, note.getStatus() == null ? null : note.getStatus().getPersistenceString());
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
            note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE)));
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DUE_DATE));
            if(dateString == null || dateString.isEmpty()) {
                note.setDueDate(null);
            } else {
                note.setDueDate(Date.valueOf(dateString));
            }
            String statusString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STATUS));
            note.setStatus(StatusEnum.parseFromPersistenceString(statusString));
            result.add(note);
        }
        cursor.close();
        return result;
    }

    public void closeConnection() {
        databaseHelper.close();
    }

}
