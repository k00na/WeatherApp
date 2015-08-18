package com.example.k00na_.weatherapp.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.k00na_.weatherapp.R;

/**
 * Created by k00na_ on 18.8.2015.
 */
public class AlertDialogFragment extends DialogFragment {

    /*
        Konstruktor bo sprejel boolean za argument. Če bo true, bo nastavil Stringe za AlertDialog na ustrezne vrednosti
        in isto velja v obratnem primeru.

     */




    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Something went wrong...")
                .setMessage(context.getString(R.string.msgDialog))
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null);
        
        AlertDialog dialog = builder.create();

        return dialog;
    }
}
