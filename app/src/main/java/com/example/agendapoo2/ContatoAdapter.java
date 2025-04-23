package com.example.agendapoo2;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContatoAdapter extends ArrayAdapter<Contato> {

    public ContatoAdapter(Context context, List<Contato> contatos) {
        super(context, 0, contatos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contato, parent, false);
        }

        TextView txtNome = convertView.findViewById(R.id.txtNomeContato);
        ImageButton btnEditar = convertView.findViewById(R.id.btnEditar);
        ImageButton btnExcluir = convertView.findViewById(R.id.btnExcluir);

        txtNome.setText(contato.getNome());

        btnEditar.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Editar: " + contato.getNome(), Toast.LENGTH_SHORT).show();
        });

        btnExcluir.setOnClickListener(v -> {
            remove(contato); // Remove da lista
            notifyDataSetChanged(); // Atualiza ListView
        });

        return convertView;
    }
}

