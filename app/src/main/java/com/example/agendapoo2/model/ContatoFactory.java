package com.example.agendapoo2.model;

import android.util.Patterns;

import com.example.agendapoo2.database.BancoDados;

import java.util.concurrent.Executors;

public class ContatoFactory {

    public enum ResultadoCriacao {
        NOME_INVALIDO,
        TELEFONE_INVALIDO,
        EMAIL_INVALIDO,
        SUCESSO
    }

    public static ResultadoCriacao criarContato(String nome, String telefone, String email, BancoDados bd, Runnable callback) {

        if (isNullOrEmpty(nome) || isNullOrEmpty(telefone) || isNullOrEmpty(email)){
            return ResultadoCriacao.NOME_INVALIDO;
        } else if (validaTelefone(telefone)) {
            return ResultadoCriacao.TELEFONE_INVALIDO;
        } else if (!validaEmail(email)) {
            return ResultadoCriacao.EMAIL_INVALIDO;
        } else {
            Contato novoContato = new Contato(nome, telefone, email);

            Executors.newSingleThreadExecutor().execute(() -> {
                bd.contatoDAO().addContato(novoContato);

                //verifica se terminou de salvar o contato no banco de dados pra dps executar o que tem pra executar
                if (callback != null) {
                    callback.run();
                }

            });

            return ResultadoCriacao.SUCESSO;

        }

    }

    private static boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

    private static boolean validaTelefone(String telefone){
        //(##)9####-####
        int qtd = telefone.length();
        return qtd != 14 || telefone.charAt(4) != '9';
    }

    private static boolean validaEmail(String email){
        //o patterns verifica se o email é no formato exemplo@dominio.com
        //é uma classe que valida padrões comuns tipo o email
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
