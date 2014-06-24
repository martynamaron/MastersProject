package com.example.obstructiveinterfaces;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;

public class ObstructiveActivity extends Activity {
	
boolean firstSend;
boolean InfoViewed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obstructive);
		
		Button yesBTN = (Button) findViewById(R.id.BTNobstruction_message_yes);
		Button noBTN = (Button) findViewById(R.id.BTNobstruction_message_no);
		Button whyBTN = (Button) findViewById(R.id.BTN_why);
		
		
		/** negative notification  
		 * 
		 * user receives negative feedback
		 */
		
Bitmap largeNotificationIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		
		// setting the notification which tells the user about a message in reply to their own
		// a reply from the supervisor
		
		 final NotificationCompat.Builder negBuilder = new NotificationCompat.Builder(this)
	    .setSmallIcon(R.drawable.notification)
	    .setLargeIcon(largeNotificationIcon)
	    .setContentTitle("1 new message")
	    .setContentText("From: timothy.storer@glasgow.ac.uk")
		.setTicker("New Message");
		
		// Creates an explicit intent for an Activity in the app
		Intent resultIntent = new Intent(this, NegativeResponse.class);
		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		negBuilder.setContentIntent(resultPendingIntent);
		final NotificationManager negMNG = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		/** end of
		 * negative notification
		 */



		
		
		//yesBTN.setEnabled(false);

		// if the user proceeds to send the message
		yesBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

    			// toast, that the message has been sent!
    			
    			Context context = getApplicationContext();
    			SendToast newToast = new SendToast();
    			
    			newToast.createToast(context);
            	
            	finish(); // close the dialog box
            	//System.exit(0); // close the app, with a delay!!!
            	
            	 firstSend = true;
            	 
            	 // negative notification because the user
            	 // sent the email with the incorrect attachment
            	 negMNG.notify(1, negBuilder.build());
            }
        });
		
		// if the message is not sent
		noBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	finish(); // close the dialog box
            	firstSend = false;
            	// pass it to the main activity!
            }
        });

		// clicking the WHY button
		whyBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Context context = getApplicationContext();
            	CharSequence text = "User clicked WHY?!";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            	
            	// starts a new activity in a form of a dialog box
            	ActivityInfoBox(v);
            	
            	InfoViewed = true;
            	
            	finish();
            }
        });
		
		

	}
	
	public void ActivityInfoBox(View view){
		
		
		Intent intent = new Intent(this, InformationActivity.class);
		int requestCode = 1;	
		startActivityForResult(intent, requestCode);

	}
	

	

}

// declared in the Manifest:
//android:theme="@android:style/Theme.Holo.Dialog"
//which makes this whole window activity just a dialog!