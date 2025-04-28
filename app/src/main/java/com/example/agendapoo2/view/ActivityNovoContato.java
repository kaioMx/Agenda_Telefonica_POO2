package com.example.agendapoo2.view;

import android.os.Bundle;
import android.util.Patterns;
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

            if (isNullOrEmpty(nome) || isNullOrEmpty(telefone) || isNullOrEmpty(email)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_dialog_contato_vazio))
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            } else if (validaTelefone(telefone)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_dialog_contato_telefone))
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            } else if (!validaEmail(email)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_dialog_contato_email))
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
            else {
                //Log.i("DEBUG: ", nome + " " + telefone + " " + email);
                BancoDados bd = Singleton.getInstance(getApplicationContext()).getBancoDados();
                ContatoFactory.criarContato(nome, telefone, email, bd, () -> {
                    runOnUiThread(() -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(getString(R.string.alert_contato_adicionado))
                                .setPositiveButton("OK", (dialog, which) -> {
                                    dialog.dismiss();
                                    finish();
                                })
                                .show();
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
