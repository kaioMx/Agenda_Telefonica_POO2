package com.example.agendapoo2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.agendapoo2.DAO.ContatoDAO;
import com.example.agendapoo2.model.Contato;

@Database(entities = {Contato.class}, version = 1)
public abstract class BancoDados extends RoomDatabase {
    public abstract ContatoDAO contatoDAO();
}
