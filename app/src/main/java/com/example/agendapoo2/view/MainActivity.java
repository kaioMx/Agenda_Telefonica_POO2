package com.example.agendapoo2.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Intent; // Para abrir nova tela

import androidx.appcompat.app.AppCompatActivity; // Activity padrão moderna
import java.util.ArrayList; // P

import com.example.agendapoo2.model.BuscaStrategy;
import com.example.agendapoo2.model.BuscarPorNome;
import com.example.agendapoo2.R;
import com.example.agendapoo2.database.BancoDados;
import com.example.agendapoo2.database.Singleton;
import com.example.agendapoo2.model.Contato;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private ImageButton btnAddContact;
    private ImageButton btnBuscarContact; // novo botão de buscar
    private ListView listViewContacts;
    private ContatoAdapter adapter;
    private ArrayList<Contato> listaContatosCompleta;
    private ArrayList<Contato> listaContatosFiltrada; // Lista para exibir no ListView
    private BuscaStrategy buscaStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);
        btnAddContact = findViewById(R.id.btnAddContact);
        btnBuscarContact = findViewById(R.id.btnBuscarContact); // NOVO botão para buscar
        listViewContacts = findViewById(R.id.listViewContacts);

        listaContatosCompleta = new ArrayList<>();
        listaContatosFiltrada = new ArrayList<>();
        adapter = new ContatoAdapter(this, listaContatosFiltrada);
        listViewContacts.setAdapter(adapter);

        Executors.newSingleThreadExecutor().execute(() -> {
            BancoDados bd = Singleton.getInstance(getApplicationContext()).getBancoDados();
            List<Contato> contatos = bd.contatoDAO().bucarTodosContatos();

            runOnUiThread(() -> {
                listaContatosCompleta.clear();
                listaContatosCompleta.addAll(contatos);

                listaContatosFiltrada.clear();
                listaContatosFiltrada.addAll(contatos);

                adapter.notifyDataSetChanged();

                // Estratégia inicial: buscar por nome
                buscaStrategy = new BuscarPorNome();
            });
        });

        listViewContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contato contatoSelecionado = adapter.getItem(position);
            if (contatoSelecionado != null) {
                Intent intent = new Intent(MainActivity.this, ContatoDetalheActivity.class);
                intent.putExtra("id", contatoSelecionado.getId());
                intent.putExtra("nome", contatoSelecionado.getNome());
                intent.putExtra("telefone", contatoSelecionado.getTelefone());
                intent.putExtra("email", contatoSelecionado.getEmail());
                startActivity(intent);
            }
        });

        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityNovoContato.class);
            startActivity(intent);
        });

        // Botão buscar contatos
        btnBuscarContact.setOnClickListener(v -> {
            String termo = editTextSearch.getText().toString().trim();

            if (!termo.isEmpty() && buscaStrategy != null) {
                List<Contato> resultados = buscaStrategy.buscar(listaContatosCompleta, termo);
                listaContatosFiltrada.clear();
                listaContatosFiltrada.addAll(resultados);
            } else {
                listaContatosFiltrada.clear();
                listaContatosFiltrada.addAll(listaContatosCompleta);
            }
            adapter.notifyDataSetChanged();
        });

    }
}

