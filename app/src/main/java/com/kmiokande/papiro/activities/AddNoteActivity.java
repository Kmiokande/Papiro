package com.kmiokande.papiro.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.kmiokande.papiro.R;
import com.kmiokande.papiro.fragment.TimePickerFragment;
import com.kmiokande.papiro.utility.AlertReciver;

import java.text.DateFormat;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private EditText etTitle;
    private EditText etContent;
    private TextView tvAlarmAdd;

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
		tvAlarmAdd = findViewById(R.id.tvAlarmAdd);
    }

    private void verificarConteudo() {
        if (!etTitle.getText().toString().equals("") || !etContent.getText().toString().equals("")) {
            sair();
        }
        else {
            finish();
        }
    }


	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hourOfDay);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		updateTimeText(c);
		startAlarm(c);
	}

	private void updateTimeText(Calendar c) {
    	String timeText = "Alarme adicionado: ";
    	timeText += DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
    	tvAlarmAdd.setText(timeText);
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	private void startAlarm(Calendar c) {
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, AlertReciver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

		if (c.before(Calendar.getInstance())) {
			c.add(Calendar.DATE, 1);
		}

		assert alarmManager != null;
		alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
	}

//	private void cancelAlarm() {
//		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//		Intent intent = new Intent(this, AlertReciver.class);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//		alarmManager.cancel(pendingIntent);
//	}

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
			DialogFragment timePicker = new TimePickerFragment();
			timePicker.show(getSupportFragmentManager(), "timePicker");
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
