package com.example.agendapoo2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.view.View;


public class ContatoDetalheActivity extends AppCompatActivity {

    private EditText tvNome;
    private EditText tvTelefone;
    private EditText tvEmail;
    private ImageButton bntEditarDeta;
    private Button bntSalvar;
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

        bntEditarDeta.setOnClickListener(v -> {
            tvNome.setEnabled(true);
            tvTelefone.setEnabled(true);
            tvEmail.setEnabled(true);
            bntSalvar.setVisibility(View.VISIBLE);

        });
        bntSalvar.setOnClickListener(v -> {
            tvNome.setEnabled(false);
            tvTelefone.setEnabled(false);
            tvEmail.setEnabled(false);
            bntSalvar.setVisibility(View.GONE);
        });

    }
}