package com.example.obstructiveinterfaces;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ObstructiveActivity extends Activity {
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obstructive);
		
		Button yesBTN = (Button) findViewById(R.id.BTNobstruction_message_yes);
		Button noBTN = (Button) findViewById(R.id.BTNobstruction_message_no);
		Button whyBTN = (Button) findViewById(R.id.BTN_why);
		
		//yesBTN.setEnabled(false);

		// if the user proceeds to send the message
		yesBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Context context = getApplicationContext();
            	CharSequence text = "Message sent";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            	
            	finish(); // close the dialog box
            	//System.exit(0); // close the app, with a delay!!!
            }
        });
		
		// if the message is not sent
		noBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	finish(); // close the dialog box
            }
        });

		whyBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Context context = getApplicationContext();
            	CharSequence text = "User clicked WHY?!";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            }
        });
	}
}

// declared in the Manifest:
//android:theme="@android:style/Theme.Holo.Dialog"
//which makes this whole window activity just a dialog!