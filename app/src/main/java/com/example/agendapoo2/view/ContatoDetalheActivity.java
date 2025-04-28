package com.example.agendapoo2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ListView;

import com.example.agendapoo2.R;
import com.example.agendapoo2.database.BancoDados;
import com.example.agendapoo2.database.Singleton;
import com.example.agendapoo2.model.Contato;
import com.example.agendapoo2.model.ContatoFactory;

import java.util.concurrent.Executors;


public class ContatoDetalheActivity extends AppCompatActivity {

    private EditText tvNome;
    private EditText tvTelefone;
    private EditText tvEmail;
    private ImageButton bntEditarDeta;
    private Button bntSalvar;
    private ImageButton btnLigar;
    private ContatoAdapter adapter;
    private Contato contatoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_detalhe); // Usa seu layout XML

        // Pega as referências dos TextViews
        tvNome = findViewById(R.id.tvNome);
        tvTelefone = findViewById(R.id.tvTelefone);
        tvEmail = findViewById(R.id.tvEmail);
        ImageButton btnVoltar = findViewById(R.id.bntVoltar);
        bntEditarDeta = findViewById(R.id.bntEditarDet);
        bntSalvar = findViewById(R.id.bntSalvar);
        //HABILITAR ISSO DPS DE MUDAR O IMAGEVIEW PRA UM IMAGEBUTTON
        //btnLigar = findViewById(R.id.imageView8);

        // Recebe os dados da Intent
        String nome = getIntent().getStringExtra("nome");
        String telefone = getIntent().getStringExtra("telefone");
        String email = getIntent().getStringExtra("email");

        // Preenche os TextViews
        tvNome.setText(nome != null ? nome : "Nome não informado");
        tvTelefone.setText(telefone != null ? telefone : "Telefone não informado");
        tvEmail.setText(email != null ? email : "Email não informado");
        bntSalvar.setVisibility(View.GONE);
        btnVoltar.setOnClickListener(v -> {
            finish();
        });

        tvNome.setEnabled(false);
        tvTelefone.setEnabled(false);
        tvEmail.setEnabled(false);

        //pega o id que vem da tela anterior (main) caso não ache retorna -1
        int id = getIntent().getIntExtra("id", -1);

        if (id != -1) {
            Executors.newSingleThreadExecutor().execute(() -> {
                BancoDados bd = Singleton.getInstance(getApplicationContext()).getBancoDados();
                contatoSelecionado = bd.contatoDAO().buscarPorId(id);

                runOnUiThread(() -> {
                    if (contatoSelecionado != null) {
                        tvNome.setText(contatoSelecionado.getNome());
                        tvTelefone.setText(contatoSelecionado.getTelefone());
                        tvEmail.setText(contatoSelecionado.getEmail());
                    }
                });
            });
        }

        bntEditarDeta.setOnClickListener(v -> {
            tvNome.setEnabled(true);
            tvTelefone.setEnabled(true);
            tvEmail.setEnabled(true);
            bntSalvar.setVisibility(View.VISIBLE);

        });

        bntSalvar.setOnClickListener(v -> {

            String novoNome = tvNome.getText().toString().trim();
            String novoTelefone = tvTelefone.getText().toString().trim();
            String novoEmail = tvEmail.getText().toString().trim();

            contatoSelecionado.setNome(novoNome);
            contatoSelecionado.setTelefone(novoTelefone);
            contatoSelecionado.setEmail(novoEmail);

            if (isNullOrEmpty(novoNome) || isNullOrEmpty(novoTelefone) || isNullOrEmpty(novoEmail)){
                MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_dialog_contato_vazio));
            } else if (validaTelefone(novoTelefone)) {
                MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_dialog_contato_telefone));
            } else if (!validaEmail(novoEmail)) {
                MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_dialog_contato_email));
            } else {

                Executors.newSingleThreadExecutor().execute(() -> {
                    BancoDados bd = Singleton.getInstance(getApplicationContext()).getBancoDados();
                    bd.contatoDAO().atualizarContato(contatoSelecionado);

                    runOnUiThread(() -> {
                        tvNome.setEnabled(false);
                        tvTelefone.setEnabled(false);
                        tvEmail.setEnabled(false);
                        bntSalvar.setVisibility(View.GONE);

                        MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_contato_atualizado));
                    });

                });

            }

        });

    }

    private boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

    private boolean validaTelefone(String telefone){
        //(##)9####-####
        int qtd = telefone.length();
        return qtd != 14 || telefone.charAt(4) != '9';
    }

    private boolean validaEmail(String email){
        //o patterns verifica se o email é no formato exemplo@dominio.com
        //é uma classe que valida padrões comuns tipo o email
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}