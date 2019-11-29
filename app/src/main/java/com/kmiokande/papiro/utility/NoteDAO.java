package com.kmiokande.papiro.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.kmiokande.papiro.data.DBHelper;
import com.kmiokande.papiro.models.Note;

import java.util.ArrayList;

public class NoteDAO {
	private DBHelper dbHelper;
	private final Context context;
	private SQLiteDatabase dbInstance = null;

	public NoteDAO(Context context) {
		dbHelper = new DBHelper(context);
		this.context = context;
	}

	public void salvarNota(Note note) throws SQLException {
		openDB();
		dbInstance.insert("notes", null, note.getContentValues());
		Toast.makeText(context, "Nota criada com sucesso!", Toast.LENGTH_SHORT).show();
		closeDB();
	}

	public ArrayList<Note> obterNotas() {
		ArrayList<Note> notes = new ArrayList<>();
		dbInstance = dbHelper.getWritableDatabase();
		Cursor cursor = dbInstance.rawQuery("SELECT * FROM notes", null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Note n = new Note();
			n.setId(cursor.getInt(0));
			n.setTitle(cursor.getString(1));
			n.setContent(cursor.getString(2));
			notes.add(n);
			cursor.moveToNext();
		}
		cursor.close();
		return notes;
	}

	public void alterar(Integer id, String title, String content) throws SQLException {
		ContentValues valores;
		openDB();

		valores = new ContentValues();
		valores.put("title", title);
		valores.put("content", content);
		dbInstance.update("notes", valores, "_id = " + id, null);
		Toast.makeText(context, "Nota modificada com sucesso!", Toast.LENGTH_SHORT).show();
		closeDB();
	}

	public void deletar(Integer id) throws SQLException {
		openDB();
		dbInstance.delete("notes", "_id = " +id, null);
		Toast.makeText(context, "Nota excluída com sucesso!", Toast.LENGTH_SHORT).show();
		closeDB();
	}

	private void openDB() throws SQLException {
		if (this.dbInstance == null) {
			this.dbInstance = dbHelper.getWritableDatabase();
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
