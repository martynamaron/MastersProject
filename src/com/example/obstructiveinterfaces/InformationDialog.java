package com.example.obstructiveinterfaces;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class InformationDialog extends DialogFragment {
	
	 public interface InformationDialogListener {
	        public void onInformationPositiveClick(DialogFragment dialog);
	        public void onInformationNegativeClick(DialogFragment dialog);
	    }

	 InformationDialogListener infoListener;
	 
	 
	   @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        // Verify that the host activity implements the callback interface
	        try {
	            // Instantiate the NoticeDialogListener so we can send events to the host
	        	infoListener = (InformationDialogListener) activity;
	        } catch (ClassCastException e) {
	            // The activity doesn't implement the interface, throw exception
	            throw new ClassCastException(activity.toString()
	                    + " must implement NoticeDialogListener");
	        }
	    }
	
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.info_dialog_msg)
        
        
        
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // read the info box, send the message anyways
                	   
                	   infoListener.onInformationPositiveClick(InformationDialog.this);
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                	   infoListener.onInformationNegativeClick(InformationDialog.this);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
