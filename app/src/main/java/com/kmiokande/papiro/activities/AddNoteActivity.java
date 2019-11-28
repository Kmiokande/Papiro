package com.kmiokande.papiro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kmiokande.papiro.R;
import com.kmiokande.papiro.fragment.TimePickerFragment;

public class AddNoteActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private EditText etTitle;
    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        carregarComponentes();
    }

    private void carregarComponentes() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Adicionar nota");     //Titulo para ser exibido na sua Action Bar em frente à seta

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
    }

    private void verificarConteudo() {
        if (!etTitle.getText().toString().equals("") || !etContent.getText().toString().equals("")) {
            sair();
        }
        else {
            finish();
        }
    }

	@Override
	public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
    	Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
		intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Papiro: " + etTitle.getText().toString());
    	intent.putExtra(AlarmClock.EXTRA_HOUR, hourOfDay);
    	intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
    	startActivity(intent);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_add_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            verificarConteudo();
        }
		else if (id == R.id.actionAlarm) {
			if (etTitle.getText().toString().equals("") || etContent.getText().toString().equals("")) {
				Toast.makeText(this, "Preencha os campos antes de adicionar um alarme", Toast.LENGTH_LONG).show();
			}
			else {
				DialogFragment timePicker = new TimePickerFragment();
				timePicker.show(getSupportFragmentManager(), "timePicker");
			}
			return true;
		}
        else if (id == R.id.actionSave) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sair() {
        new AlertDialog.Builder(this).setMessage(R.string.addNoteExit).setCancelable(false).setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddNoteActivity.this.finish();
            }
        }).setNegativeButton(R.string.textCancel, null).show();
    }

    @Override
    public void onBackPressed() {
        verificarConteudo();
    }
}
