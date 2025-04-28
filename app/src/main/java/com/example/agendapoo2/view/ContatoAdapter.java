package com.example.agendapoo2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.agendapoo2.R;
import com.example.agendapoo2.model.Contato;

import java.util.List;

public class ContatoAdapter extends ArrayAdapter<Contato> {

    public ContatoAdapter(Context context, List<Contato> contato) {
        super(context, 0, contato);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contato, parent, false);
        }

        TextView txtNome = convertView.findViewById(R.id.txtNomeContato);

        ImageButton btnExcluir = convertView.findViewById(R.id.btnExcluir);

        txtNome.setText(contato.getNome());

        btnExcluir.setOnClickListener(v -> {
            remove(contato); // Remove da lista
            notifyDataSetChanged(); // Atualiza ListView
        });

        return convertView;
    }
}

