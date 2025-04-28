package com.example.agendapoo2.view;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class MensagemBuilder {

    public static void mostrarAlerta(Context context, String mensagem) {
        new AlertDialog.Builder(context)
                .setMessage(mensagem)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

}
