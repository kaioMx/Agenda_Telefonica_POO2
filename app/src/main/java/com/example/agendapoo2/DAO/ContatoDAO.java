package com.example.agendapoo2.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agendapoo2.model.Contato;

import java.util.List;

@Dao
public interface ContatoDAO {

    @Insert
    void addContato(Contato contato);

    @Update
    void atualizarContato(Contato contato);

    @Delete
    void excluirContato(Contato contato);

    @Query("SELECT * FROM Contato WHERE id = :id")
    Contato buscarPorId(int id);

    @Query("SELECT * FROM Contato")
    List<Contato> bucarTodosContatos();

    @Query("SELECT * FROM Contato WHERE email = :email")
    List<Contato> buscarPorEmail(String email);

    @Query("SELECT * FROM Contato WHERE telefone = :telefone")
    List<Contato> buscarPorTelefone(String telefone);

    @Query("SELECT * FROM Contato WHERE nome = :nome")
    List<Contato> buscarPorNome(String nome);

}
