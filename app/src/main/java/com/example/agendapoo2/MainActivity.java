package com.example.agendapoo2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Intent; // Para abrir nova tela
import android.os.Bundle; // Para o onCreate
import android.view.View; // Para interações de View
import android.widget.AdapterView; // Para o clique na lista
import android.widget.ArrayAdapter; // Para o adapter (se ainda usar)
import android.widget.EditText; // Campo de pesquisa
import android.widget.ImageButton; // Botão adicionar
import android.widget.ListView; // Lista de contatos
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity; // Activity padrão moderna
import java.util.ArrayList; // P

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private ImageButton btnAddContact;
    private ImageButton btnBuscarContact; // novo botão de buscar
    private ListView listViewContacts;
    private ContatoAdapter adapter;
    private ArrayList<Contato> listaContatosCompleta; // TODOS os contatos
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



        listViewContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contato contatoSelecionado = adapter.getItem(position);
            if (contatoSelecionado != null) {
                Intent intent = new Intent(MainActivity.this, ContatoDetalheActivity.class);
                intent.putExtra("nome", contatoSelecionado.getNome());
                intent.putExtra("telefone", contatoSelecionado.getTelefone());
                intent.putExtra("email", contatoSelecionado.getEmail());
                startActivity(intent);
            }
        });

        btnAddContact.setOnClickListener(v -> {
            String nome = editTextSearch.getText().toString().trim();
            if (!nome.isEmpty()) {
                listaContatosCompleta.add(ContatoFactory.criarContato(nome, "", ""));
                listaContatosFiltrada.clear();
                listaContatosFiltrada.addAll(listaContatosCompleta);
                adapter.notifyDataSetChanged();
                editTextSearch.setText("");
            }
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

        // Exemplo de contatos pré-carregados
        listaContatosCompleta.add(ContatoFactory.criarContato("Kaio Dev", "34999999999", "kaio@email.com"));
        listaContatosCompleta.add(ContatoFactory.criarContato("Isadora Cabeçuda", "3488888888", "isadora@email.com"));
        listaContatosFiltrada.addAll(listaContatosCompleta); // precisa preencher a lista visível
        adapter.notifyDataSetChanged();

        adapter.notifyDataSetChanged();

        // Estratégia inicial: buscar por nome
        buscaStrategy = new BuscarPorNome();
    }
}

