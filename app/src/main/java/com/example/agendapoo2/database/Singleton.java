package com.example.agendapoo2.database;


import android.content.Context;

import androidx.room.Room;

public class Singleton {

    private static Singleton singleton;
    private BancoDados bancoDados;

    private Singleton(Context context){
        bancoDados = Room.databaseBuilder(context, BancoDados.class, "agenda").build();
    }

    public static synchronized Singleton getInstance(Context context){
        if (singleton == null)
            singleton = new Singleton(context);
        return singleton;
    }

    public BancoDados getBancoDados(){
        return bancoDados;
    }

}
