package tiepdvph30311.fpoly.lab8_android2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiepdvph30311.fpoly.lab8_android2.Database.MySQLiteHelper;
import tiepdvph30311.fpoly.lab8_android2.Model.Note;

public class NotesDAO {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CONTENT, MySQLiteHelper.COLUMN_TIME };

    public NotesDAO(Context context) {
        dbHelper = new MySQLiteHelper(context.getApplicationContext());
    }

    public void open() throws SQLException {
        if (database == null || !database.isOpen()) {
            database = dbHelper.getWritableDatabase();
        }
    }

    public void close() {
        if (database != null && database.isOpen()) {
            dbHelper.close();
        }
    }

    public Note createNote(String content, String time) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CONTENT, content);
        values.put(MySQLiteHelper.COLUMN_TIME, time);
        long insertId = database.insert(MySQLiteHelper.TABLE_NOTES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Note newNote = cursorToNote(cursor);
        cursor.close();
        return newNote;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return notes;
    }
    public void deleteNote(long noteId) {
        database.delete(MySQLiteHelper.TABLE_NOTES, MySQLiteHelper.COLUMN_ID + " = " + noteId, null);
    }
    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(0));
        note.setContent(cursor.getString(1));
        note.setTime(cursor.getString(2));
        return note;
    }
}
