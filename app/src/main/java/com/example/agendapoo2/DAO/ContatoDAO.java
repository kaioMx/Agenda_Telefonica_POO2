package com.example.agendapoo2.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.agendapoo2.model.Contato;

import java.util.List;

@Dao
public interface ContatoDAO {

    @Insert
    void addContato(Contato contato);

    @Query("SELECT * FROM Contato")
    List<Contato> bucarTodosContatos();

    @Query("SELECT * FROM Contato WHERE email = :email")
    List<Contato> buscarPorEmail(String email);

    @Query("SELECT * FROM Contato WHERE telefone = :telefone")
    List<Contato> buscarPorTelefone(String telefone);

    @Query("SELECT * FROM Contato WHERE nome = :nome")
    List<Contato> buscarPorNome(String nome);

    @Query("DELETE FROM Contato WHERE email = :email")
    void excluirContato(String email);
}
