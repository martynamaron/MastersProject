package com.example.obstructiveinterfaces;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class AttachmentDialog extends DialogFragment{
	

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
		final ArrayList  mSelectedItems = new ArrayList();  // Where we track the selected items

		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       // .setMessage("Message")
        builder.setTitle("Science > Exams > 2014")
        	   .setIcon(R.drawable.open_folder)
        	   
        	   	//setting the folder multi-check list
        	   .setMultiChoiceItems(R.array.folders_array, null, new DialogInterface.OnMultiChoiceClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which,
						boolean isChecked) {
					   if (isChecked) {
	                       // If the user checked the item, add it to the selected items
	                       mSelectedItems.add(which);
	                   } else if (mSelectedItems.contains(which)) {
	                       // Else, if the item is already in the array, remove it 
	                       mSelectedItems.remove(Integer.valueOf(which));
	                   }

					
				}
        		   
        	   })
               .setPositiveButton("fire", new DialogInterface.OnClickListener() {
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
        return builder.create();
    }
}
