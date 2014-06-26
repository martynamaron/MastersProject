package com.example.obstructiveinterfaces;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

// dialog for typing in initial information
// participant number and type of obstruction
public class InitialDialog extends DialogFragment{
	
	
	 public interface InitialDialogListener {
	        public void InitialDialogClick(DialogFragment dialog);
	    }

	 InitialDialogListener initialistener;
	 
	   @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        // Verify that the host activity implements the callback interface
	        try {
	            // Instantiate the NoticeDialogListener so we can send events to the host
	        	initialistener = (InitialDialogListener) activity;
	        } catch (ClassCastException e) {
	            // The activity doesn't implement the interface, throw exception
	            throw new ClassCastException(activity.toString()
	                    + " must implement NoticeDialogListener");
	        }
	    }

	

	@Override
   public Dialog onCreateDialog(Bundle savedInstanceState) {
       // Use the Builder class for convenient dialog construction


		// Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

		
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
     
       //set custom layout of the dialog box
       builder.setView(inflater.inflate(R.layout.initial_dialog, null))

       
       		  .setTitle("Metadata")
       		  
       		  
              .setPositiveButton("Continue to App", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  initialistener.InitialDialogClick(InitialDialog.this);

                  }
              });
     
       // Create the InitialDialog object and return it
       return builder.create();
   }
}
