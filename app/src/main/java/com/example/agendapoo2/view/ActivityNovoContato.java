package com.example.agendapoo2.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendapoo2.model.ContatoFactory;
import com.example.agendapoo2.R;
import com.example.agendapoo2.database.BancoDados;
import com.example.agendapoo2.database.Singleton;

public class ActivityNovoContato extends AppCompatActivity {

    private EditText tvNome;
    private EditText tvTelefone;
    private EditText tvEmail;
    private Button bntSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        tvNome = findViewById(R.id.tvNome);
        tvTelefone = findViewById(R.id.tvTelefone);
        tvEmail = findViewById(R.id.tvEmail);
        bntSalvar = findViewById(R.id.bntSalvar);

        bntSalvar.setOnClickListener(v ->{
            String nome = tvNome.getText().toString().trim();
            String telefone = tvTelefone.getText().toString().trim();
            String email = tvEmail.getText().toString().trim();

            //Log.i("DEBUG: ", nome + " " + telefone + " " + email);
            BancoDados bd = Singleton.getInstance(getApplicationContext()).getBancoDados();
            ContatoFactory.ResultadoCriacao resultado = ContatoFactory.criarContato(nome, telefone, email, bd, () -> {
                runOnUiThread(() -> {
                    MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_contato_adicionado));
                    finish();
                });
            });

            switch (resultado) {
                case NOME_INVALIDO:
                    MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_dialog_contato_vazio));
                    break;
                case TELEFONE_INVALIDO:
                    MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_dialog_contato_telefone));
                    break;
                case EMAIL_INVALIDO:
                    MensagemBuilder.mostrarAlerta(this, getString(R.string.alert_dialog_contato_email));
                    break;
                case SUCESSO:
                    break;
            }

        });
    }

}
