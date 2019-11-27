package com.kmiokande.papiro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.kmiokande.papiro.R;

public class ModifyNoteActivity extends AppCompatActivity {
    private EditText etTitleModify;
    private EditText etContentModify;
    private String titleDefault;
    private String contentDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_note_activity);
        carregarComponentes();
        carregarDados();
    }

    private void carregarDados() {
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        titleDefault = title;
        contentDefault = content;

        etTitleModify.setText(title);
        etContentModify.setText(content);
    }

    private void carregarComponentes() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Modificar nota");     //Titulo para ser exibido na sua Action Bar em frente à seta

        etTitleModify = findViewById(R.id.etTitleModify);
        etContentModify = findViewById(R.id.etContentModify);
    }

    private void verificarConteudo() {
        if (!etTitleModify.getText().toString().equals(titleDefault) || !etContentModify.getText().toString().equals(contentDefault)) {
            sair();
        }
        else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_modify_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            verificarConteudo();
        }
        else if (id == R.id.actionSaveModify) {
            return true;
        }
        else if (id == R.id.actionDeleteModify) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sair() {
        new AlertDialog.Builder(this).setMessage(R.string.modifyNoteExit).setCancelable(false).setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ModifyNoteActivity.this.finish();
            }
        }).setNegativeButton(R.string.textCancel, null).show();
    }

    @Override
    public void onBackPressed() {
        verificarConteudo();
    }
}
