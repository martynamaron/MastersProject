package com.example.obstructiveinterfaces;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.Toast;


public class AttachmentDialog extends DialogFragment{
	
	private int Attaching;
	private boolean [] checked = new boolean [4];
	private int choice;
	
	 public interface AttachmentDialogListener {
	        public void onAttachmentPositiveClick(ArrayList <Integer> List, int theChosenOne);
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
		
		
		final ArrayList <Integer> AttachedItems = new  ArrayList();  // Where we track the selected items
		
		
		Attaching = getArguments().getInt("Reattaching");
		// if int Attaching == 1, then it means the user is reattaching the file
		

		
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        
        
       // .setMessage("Message")
      builder.setTitle("Dissertations > Postgraduate");
        
        	
        
      builder.setIcon(R.drawable.openfolder);  //sets the title icon
        	   
        	   
       		
       		if (Attaching <=3){
       			checked[Attaching]= false;
       			
       			if (Attaching==0 || Attaching==3){
       				checked[1]=true;
       			}
       			else if(Attaching >0 && Attaching <3){
       				checked[0]=true;
       			}
       			
       			
        	   	//setting the folder multi-check list
        	   builder.setMultiChoiceItems(R.array.attachments_array, checked, new DialogInterface.OnMultiChoiceClickListener(){

        		 
        		   
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
				
        	   });
       			
       		}
       		
       		
       		else {
       			
       			
        	   	//setting the folder multi-check list
        	   builder.setMultiChoiceItems(R.array.attachments_array, null, new DialogInterface.OnMultiChoiceClickListener(){

        		 
        		   
        		// template code from Android Developers   
				@Override
				public void onClick(DialogInterface dialog, int which,
						boolean isChecked) {
					   if (isChecked) {
	                       // If the user checked the item, add it to the selected items
						   AttachedItems.add(which);
						   choice = which;
						   
	                   } else if (AttachedItems.contains(which)) {
	                       // Else, if the item is already in the array, remove it 
	                	   AttachedItems.remove(Integer.valueOf(which));
	                   }
	
				}
				
        	   });
       			
       		}
        	   

        	   
        	   
        	   builder.setPositiveButton("Attach File", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	
                		  
                	   mListener.onAttachmentPositiveClick(AttachedItems, choice);

                   }
               });
        	   builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onAttachmentNegativeClick(AttachmentDialog.this);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
