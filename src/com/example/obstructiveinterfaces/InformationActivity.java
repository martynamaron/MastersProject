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
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class InformationActivity extends Activity {
	
	private View view;
	private Handler handler;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		handler = new Handler();
		
				
		Button infoYES = (Button) findViewById(R.id.info_yes);
		Button infoNO = (Button) findViewById(R.id.info_no);
		
		  	
		// what happens when you click YES button
		infoYES.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				// toast, that the message has been sent!
				
				Context context = getApplicationContext();
				SendToast newToast = new SendToast();
				
				newToast.createToast(context);
            	
            	finish(); // close the dialog box

              	 
              	 view =v;
              	 
              	 
              	// thread that will delay delivery of the reply message 
           		new Thread(new Task2()).start();  
            	
            	
			}
		});
		
		// what happens when you click NO button
		infoNO.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

            	finish(); // close the dialog box
            	
            					
			}
		});
		
	}

	
	public void Notification(View view){
		
		Intent intent = new Intent(this, NegNotification.class);
		int requestCode = 1;	
		startActivityForResult(intent, requestCode);
		
	}
	
	
	public void TopNotification(){
		
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
		
		
    	
     	 // negative notification because the user
     	 // sent the email with the incorrect attachment
     	 negMNG.notify(1, negBuilder.build());
		
	}
	
	// thread that delays delivery of the reply message
	class Task2 implements Runnable {
			    @Override
		        public void run() {
			           
			         
			                try {
		                    Thread.sleep(5000);
		                    
			                } catch (InterruptedException e) {
			                    e.printStackTrace();
			                }
			                
			               handler.post(new Runnable() {
			                @Override
			                public void run() {
			                	Notification(view);
			                	TopNotification();
			                	
			                 }
			               });
			            
			        }
			 
			    }

	
}
