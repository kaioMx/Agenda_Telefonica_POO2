package com.example.agendapoo2.model;


import java.util.ArrayList;
import java.util.List;

public class BuscaPorEmail implements BuscaStrategy {
    @Override
    public List<Contato> buscar(List<Contato> contato, String termo) {
        List<Contato> resultado = new ArrayList<>();
        for (Contato contatos : contato) {
            if (contatos.getEmail().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(contatos);
            }
        }
        return resultado;
    }
}