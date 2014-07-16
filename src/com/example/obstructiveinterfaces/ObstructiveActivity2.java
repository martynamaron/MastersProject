package com.example.obstructiveinterfaces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NotificationCompat;


public class ObstructiveActivity2 extends Activity {
	
private boolean  InfoViewed;

private Handler handler;
private Button yesBTN, noBTN, whyBTN;
private String PartNUMBER;
private View view;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obstructive_activity2);
		
		yesBTN = (Button) findViewById(R.id.BTNobstruction_message_yes2);
		noBTN = (Button) findViewById(R.id.BTNobstruction_message_no2);
		whyBTN = (Button) findViewById(R.id.BTN_why2);
		
		// button initially set to false
		yesBTN.setEnabled(false);
		
		//read the participant number
		Bundle bundle = getIntent().getExtras();
		PartNUMBER = bundle.getString("Participant Number");
		
		handler = new Handler();
		
		


		

		// if the user proceeds to send the message
		yesBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	// toast, that the message has been sent!
    			
    			Context context = getApplicationContext();
    			SendToast newToast = new SendToast();
    			
    			newToast.createToast(context);
            	
            	
    			finish(); // close the dialog box
            	//System.exit(0); // close the app, with a delay!!!
            	

            	 
            	 view =v;
            	 
            	 
            	// thread that will delay delivery of the reply message 
         		new Thread(new Task2()).start();
            	 
            	
            }
        });
		
		// if the message is not sent
		noBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	finish(); // close the dialog box
            	
            	// pass it to the main activity!
            }
        });

		// clicking the WHY button
		whyBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	// starts a new activity in a form of a dialog box
            	ActivityInfoBox(v);
            	
            	InfoViewed = true;
            	
            	saveInFile();
            	
            	finish();
            }
        });
		
		// starts a thread which enables the YES button after 5 seconds
		new Thread(new Task()).start();

	}
	
	public void ActivityInfoBox(View view){
		
		
		Intent intent = new Intent(this, InformationActivity.class);
		int requestCode = 1;	
		startActivityForResult(intent, requestCode);

	}
	
	
	public void TopNotification(){
		
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
		
    	
   	 
   	 // negative notification because the user
   	 // sent the email with the incorrect attachment
   	 negMNG.notify(1, negBuilder.build());
		
		/** end of
		 * negative notification
		 */
	}
	

	
	public void saveInFile(){
		
		String sFileName = "Results"+PartNUMBER+".txt";
		try {
			
			File root = new File(Environment.getExternalStorageDirectory(), "Notes");
			
		
			File gpxfile = new File(root, sFileName);
			FileWriter writer = new FileWriter(gpxfile, true);
			writer.append("The information dialog was opened  \n");
			writer.flush();
	        writer.close();

	        //debugging
	        //Toast.makeText(this, "INFO Saved", Toast.LENGTH_SHORT).show();
			
		}
		
	    catch(IOException e)
	    {
	         e.printStackTrace();
	    }

		
	}
	
	public void Notification(View view){
		
		Intent intent = new Intent(this, NegNotification.class);
		int requestCode = 1;	
		startActivityForResult(intent, requestCode);
		
	}
	
	
	// the delay thread defined an vibration

	class Task implements Runnable {
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
			                	yesBTN.setEnabled(true);
			                	
			                	
			                 }
			               });
			            
			        }
			 
			    }

	// end of delay thread
	
	
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

// declared in the Manifest:
//android:theme="@android:style/Theme.Holo.Dialog"
//which makes this whole window activity just a dialog!