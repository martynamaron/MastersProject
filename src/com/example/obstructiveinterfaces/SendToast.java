package com.example.obstructiveinterfaces;


import android.content.Context;
import android.widget.Toast;

// class for generating a toas confirming the message has been sent

public class SendToast {
	
	
	
	public void SendToast (){
		
		// empty constructor
		
	}

	
	public void createToast (Context c) {
		
		
    	CharSequence text = "Message sent";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(c, text, duration);
    	toast.show();
	}

}
