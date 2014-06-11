package com.example.obstructiveinterfaces;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class AttachmentDialog extends DialogFragment{
	

	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("FIRE ZE MISSILES");
	        builder.setTitle("FIRE");

	        builder.setPositiveButton("fire", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // FIRE ZE MISSILES!
	                   }
	               })
	               .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                   }
	               });
	        // Create the AlertDialog object and return it
	        AlertDialog dialog = builder.create();
	        return dialog;
	    }
	}
