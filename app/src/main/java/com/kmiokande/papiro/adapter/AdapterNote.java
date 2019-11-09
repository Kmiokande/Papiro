package com.kmiokande.papiro.adapter;

import com.kmiokande.papiro.R;
import com.kmiokande.papiro.models.Note;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AdapterNote extends ArrayAdapter<Note> {
    private final Context context;
    private final ArrayList<Note> listNotes;

    public AdapterNote(Context context, ArrayList<Note> listaNotas) {
        super(context, R.layout.listar_notas, listaNotas);
        this.context = context;
        this.listNotes = listaNotas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha = inflater.inflate(R.layout.listar_notas, parent, false);
        TextView title = linha.findViewById(R.id.tvTitle);
        TextView content =  linha.findViewById(R.id.tvContent);
        title.setText(listNotes.get(position).getTitle());
        content.setText(listNotes.get(position).getContent());
        return linha;
    }
}
