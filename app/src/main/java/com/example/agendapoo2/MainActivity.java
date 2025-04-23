package com.example.agendapoo2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private ImageButton btnAddContact;
    private ListView listViewContacts;
    private ContatoAdapter adapter;
    private ArrayList<Contato> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);
        btnAddContact = findViewById(R.id.btnAddContact);
        listViewContacts = findViewById(R.id.listViewContacts);

        listaContatos = new ArrayList<>();
        adapter = new ContatoAdapter(this, listaContatos);
        listViewContacts.setAdapter(adapter);

        btnAddContact.setOnClickListener(v -> {
            String nome = editTextSearch.getText().toString().trim();
            if (!nome.isEmpty()) {
                listaContatos.add(new Contato(nome));
                adapter.notifyDataSetChanged();
                editTextSearch.setText("");
            }
        });

        // Exemplo de contatos pré-carregados
        listaContatos.add(new Contato("Kaio Dev"));
        listaContatos.add(new Contato("Isadora Cabeçuda"));
        adapter.notifyDataSetChanged();
    }
}
