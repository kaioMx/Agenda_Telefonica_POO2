package com.example.agendapoo2.model;

import com.example.agendapoo2.database.BancoDados;

import java.util.concurrent.Executors;

public class ContatoFactory {

    public static void criarContato(String nome, String telefone, String email, BancoDados bd, Runnable callback) {

        Contato novoContato = new Contato(nome, telefone, email);

        Executors.newSingleThreadExecutor().execute(() -> {
            bd.contatoDAO().addContato(novoContato);

            //verifica se terminou de salvar o contato no banco de dados pra dps executar o que tem pra executar
            if (callback != null) {
                callback.run();
            }

        });

    }
}
