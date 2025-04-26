package com.example.agendapoo2;


import java.util.ArrayList;
import java.util.List;

public class BuscaPorTelefone implements BuscaStrategy {
    @Override
    public List<Contato> buscar(List<Contato> contatos, String termo) {
        List<Contato> resultado = new ArrayList<>();
        for (Contato contato : contatos) {
            if (contato.getTelefone().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(contato);
            }
        }
        return resultado;
    }
}