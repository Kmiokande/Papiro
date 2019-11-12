package com.kmiokande.papiro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.kmiokande.papiro.R;
import com.kmiokande.papiro.adapter.AdapterNote;
import com.kmiokande.papiro.models.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Adapter
        AdapterNote adapter = new AdapterNote(this, gerarNotas());

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note noteSelected = (Note) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, ModifyNoteActivity.class);
                intent.putExtra("title", noteSelected.getTitle());
                intent.putExtra("content", noteSelected.getContent());
                startActivity(intent);
            }
        });

    }

    private ArrayList<Note> gerarNotas() {
        ArrayList<Note> items = new ArrayList<Note>();
        items.add(new Note("Nome do gato", "Jupi\nFarofa\nFubá\nZezim"));
        items.add(new Note("Nome do filme", "As tranças do rei careca"));
        items.add(new Note("Meu endereço", "Rua dos anzois"));
        items.add(new Note("Izzi", "Pesadão"));
        items.add(new Note("Senha", "123456789"));
        items.add(new Note("Valor que estou devendo na biqueira", "3755,00"));
        items.add(new Note("Serial do windows", "WDFG-123R-GGFR-34TF"));
        items.add(new Note("Quilometragem da proxima revisão", "1145.00"));
        items.add(new Note("Ultima nota", "Finalizado"));

        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.actionAdd) {
            Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.actionShare) {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT, getString(R.string.infoShare));
            share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            share.setType("text/plain");
            startActivity(Intent.createChooser(share, getString(R.string.share)));
            return true;
        }
        else if (id == R.id.actionSobre) {
            menssagemSobre();
            return true;
        }
        else if (id == R.id.actionSair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void menssagemSobre() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(getString(R.string.infoAbout));
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, getString(R.string.textOk), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //alertDialog.setIcon(R.drawable.ic_copyright_black_24dp);
        alertDialog.show();
    }

    public void sair() {
        new AlertDialog.Builder(this).setMessage("Deseja realmente sair?").setCancelable(false).setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.finish();
            }
        }).setNegativeButton(R.string.textCancel, null).show();
    }

    @Override
    public void onBackPressed() {
        sair();
    }
}
