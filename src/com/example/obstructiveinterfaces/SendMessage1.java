package com.example.obstructiveinterfaces;

import java.util.ArrayList;

import com.example.obstructiveinterfaces.AttachmentDialog.AttachmentDialogListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class SendMessage1 extends DialogFragment{
	
	 public interface SendMessageListener {
	        public void onDialogPositiveClick2(DialogFragment dialog);
	        public void onDialogNegativeClick(DialogFragment dialog);
			
	    }
	 
	 SendMessageListener SMlistener;
	 
	   @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        // Verify that the host activity implements the callback interface
	        try {
	            // Instantiate the NoticeDialogListener so we can send events to the host
	        	SMlistener = (SendMessageListener) activity;
	        } catch (ClassCastException e) {
	            // The activity doesn't implement the interface, throw exception
	            throw new ClassCastException(activity.toString()
	                    + " must implement NoticeDialogListener");
	        }
	    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
		final ArrayList  mSelectedItems = new ArrayList();  // Where we track the selected items

		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("")
        	   .setTitle("Are you sure you want to send this email?")
        	   .setIcon(R.drawable.more_info)
        	   
        	

               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   SMlistener.onDialogPositiveClick2(SendMessage1.this);

                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   SMlistener.onDialogNegativeClick(SendMessage1.this);
                   }
               });
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
