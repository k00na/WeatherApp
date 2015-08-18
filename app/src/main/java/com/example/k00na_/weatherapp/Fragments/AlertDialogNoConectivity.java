package com.example.k00na_.weatherapp.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.k00na_.weatherapp.R;

/**
 * Created by k00na_ on 18.8.2015.
 */
public class AlertDialogNoConectivity extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Error!")
                .setMessage("Connection to internet is not available")
                .setPositiveButton(R.string.OK, null)
                .setNegativeButton(R.string.CANCEL, null);

        AlertDialog dialog = builder.create();


        return dialog;
    }
}
