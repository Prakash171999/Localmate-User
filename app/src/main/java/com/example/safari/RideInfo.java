package com.example.safari;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import static android.content.Context.MODE_PRIVATE;

public class RideInfo extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Getting the users data from SharedPreferences SessionManager class
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SessionManager.SHARED_PREF_NAME , Context.MODE_PRIVATE);
        String Distance = sharedPreferences.getString( SessionManager.KEY_DISTANCE, "total_distance");
        String Price = sharedPreferences.getString( SessionManager.KEY_PRICE, "total_price");

        builder.setTitle("Ride Details")
               .setMessage("\nTOTAL DISTANCE => " +""+ Distance + " meters" +"\n\n" + "TOTAL RIDE COST=> "+ " Rs. "+Price)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
        return builder.create();
    }
}



