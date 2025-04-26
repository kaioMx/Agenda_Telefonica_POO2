package com.example.agendapoo2;

public class ContatoFactory {

    public static Contato criarContato(String nome, String telefone, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            nome = "Contato Sem Nome";
        }
        if (telefone == null) {
            telefone = "";
        }
        if (email == null) {
            email = "";
        }
        return new Contato(nome, telefone, email);
    }
}
