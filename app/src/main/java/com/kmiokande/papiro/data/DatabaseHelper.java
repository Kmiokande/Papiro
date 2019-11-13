package com.kmiokande.papiro.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kmiokande.papiro.utility.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT NOT NULL";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_NOTES = "CREATE TABLE "
            + Constants.NoteEntry.TABLE_NAME + " (" +
            Constants.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Constants.NoteEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
            Constants.NoteEntry.COLUMN_NAME_CONTENT + TEXT_TYPE + " )";

    private static final String SQL_DELETE_NOTES =
            "DROP TABLE IF EXISTS " + Constants.NoteEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_NOTES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
