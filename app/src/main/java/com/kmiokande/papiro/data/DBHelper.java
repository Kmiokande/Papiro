package com.kmiokande.papiro.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.kmiokande.papiro.models.Note;

public class DBHelper extends SQLiteOpenHelper {
    private Note note;
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private SQLiteDatabase dbInstance = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notes(_ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, content TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(sqLiteDatabase);
    }

    public void salvarNota(Note note) throws SQLException {
        openDB();

        dbInstance.insert("notes", null, note.getContentValues());
        Toast.makeText(context, "Nota criada com sucesso!", Toast.LENGTH_SHORT).show();

        closeDB();
    }

    private void openDB() throws SQLException {
        if (this.dbInstance == null) {
            this.dbInstance = this.getWritableDatabase();
        }
    }

    private void closeDB() throws SQLException {
        if (this.dbInstance != null) {
            if (this.dbInstance.isOpen()) {
                this.dbInstance.close();
            }
        }
    }
}
