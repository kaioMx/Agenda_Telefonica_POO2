package com.example.agendapoo2.model;

import java.util.List;

public interface BuscaStrategy {
    List<Contato> buscar(List<Contato> contato, String termo);
}