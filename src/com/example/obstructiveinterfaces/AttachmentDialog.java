package com.example.obstructiveinterfaces;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class AttachmentDialog extends DialogFragment{
	
	
	 public interface AttachmentDialogListener {
	        public void onDialogPositiveClick(DialogFragment dialog);
	        public void onDialogNegativeClick(DialogFragment dialog);
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
		
		final ArrayList  mSelectedItems = new ArrayList();  // Where we track the selected items
				
				
		
		//retrieves the biology.pdf pic from the typedArray - the first item of that array
				Resources res = getResources();
				TypedArray folders = res.obtainTypedArray(R.array.folders_array);
				Drawable drawable = folders.getDrawable(0);
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       // .setMessage("Message")
        builder.setTitle("Science > Exams > 2014")
        
        	
        
        	   //.setIcon(drawable)  //sets the title icon
        	   
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
               .setPositiveButton("Attach File", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onDialogPositiveClick(AttachmentDialog.this);

                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onDialogNegativeClick(AttachmentDialog.this);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
