package com.example.obstructiveinterfaces;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class AttachmentDialog extends DialogFragment{
	
	
	 public interface AttachmentDialogListener {
	        public void onAttachmentPositiveClick(ArrayList<Integer> arrayList);
	        public void onAttachmentNegativeClick(DialogFragment dialog);
	    }

	 AttachmentDialogListener mListener;
	 
	   @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        // Verify that the host activity implements the callback interface
	        try {
	            // Instantiate the NoticeDialogListener so we can send events to the host
	            mListener = (AttachmentDialogListener) activity;
	        } catch (ClassCastException e) {
	            // The activity doesn't implement the interface, throw exception
	            throw new ClassCastException(activity.toString()
	                    + " must implement NoticeDialogListener");
	        }
	    }

	

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
		
		final ArrayList  AttachedItems = new ArrayList();  // Where we track the selected items
		String choice ="";
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       // .setMessage("Message")
        builder.setTitle("Dissertations > Postgraduate")
        
        	
        
        	   .setIcon(R.drawable.openfolder)  //sets the title icon
        	   
        	   	//setting the folder multi-check list
        	   .setMultiChoiceItems(R.array.attachments_array, null, new DialogInterface.OnMultiChoiceClickListener(){

        		 
        		   
        		// template code from Android Developers   
				@Override
				public void onClick(DialogInterface dialog, int which,
						boolean isChecked) {
					   if (isChecked) {
	                       // If the user checked the item, add it to the selected items
						   AttachedItems.add(which);
						   
	                   } else if (AttachedItems.contains(which)) {
	                       // Else, if the item is already in the array, remove it 
	                	   AttachedItems.remove(Integer.valueOf(which));
	                   }

					
				}
				

				    
        		   
        	   })
               .setPositiveButton("Attach File", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onAttachmentPositiveClick(AttachedItems);

                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onAttachmentNegativeClick(AttachmentDialog.this);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
