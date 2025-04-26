package com.example.agendapoo2;

import java.util.List;

public interface BuscaStrategy {
    List<Contato> buscar(List<Contato> contatos, String termo);
}