package com.example.obstructiveinterfaces;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.obstructiveinterfaces.ObstructiveActivity.Task2;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AttachmentDialog.AttachmentDialogListener, 
ReattachingDialog.ReattachingDialogListener {

	Button attachBTN, sendBTN;
	TextView display;
	char obstructionType;
	int obstr;
	String ParticipantNO="";
	String returnedResult="";
	String sFileName = "";
	String ParticipantMetadata, reattachedSTRING;
	Vibrator v;
	final static int STATIC_INTEGER_VALUE = 1;
	protected static final String PUBLIC_STATIC_STRING_IDENTIFIER = "passing the participant number";
	private Handler handler;
	private View view;
	boolean firstAttach, reattached, infoViewed;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		handler = new Handler();
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
				
			}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		

		public PlaceholderFragment() {
			
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
						
			return rootView;
			
			}
				
	}
	
	// dialog to type in the participant number and obstruction type
	public void InitialDialog (View view){
		
		Intent intent = new Intent(this, InitialActivity.class);
		
		// the initial dialog box will be passing the values back
		startActivityForResult(intent, STATIC_INTEGER_VALUE );
		
		Button BTNinitialise = (Button) findViewById(R.id.initialise);
		BTNinitialise.setVisibility(4);
		
		
	}
	
	//listening for passing the Participant Number and Obstruction Type from InitialActivity
	// WORKS!!!
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  switch(requestCode) {
	    case (STATIC_INTEGER_VALUE) : {
	      if (resultCode == Activity.RESULT_OK) {
	        // TODO Extract the data returned from the child Activity.
	    	  returnedResult = data.getStringExtra(PUBLIC_STATIC_STRING_IDENTIFIER);
	    	  
	    	  //divide the string into the Obstruction type and Participant number
	    	  obstructionType = returnedResult.charAt(0);
	    	  
	    	  ParticipantNO = returnedResult.substring(1,4);
	    	  
	    	  TextView tv = (TextView) findViewById(R.id.textView1);
	    	  tv.setText(""+obstructionType);
	    	  
	    	  TextView tv2 = (TextView) findViewById(R.id.textView2);
	    	  tv2.setText(ParticipantNO);
	    	  
	      }
	      
	      break;
	      
	    } 
	  }
	}
	
	
	/* 
	 * WORKS!
	 * displays a dialog box when ATTACH button is clicked */
	
	public void DialogBox(View view){
		
	if (!firstAttach) {
		
		DialogFragment attachDialog = new AttachmentDialog();
		//d.setTitle("Dialog Test");
		TextView tv = new TextView(this);
		tv.setText("Success");
		//d.setContentView(tv);
		attachDialog.show(getSupportFragmentManager(), "attach box");
		
	}
	
	else {
		
		DialogFragment dialogReattach = new ReattachingDialog();
		TextView tv = new TextView(this);
		tv.setText("Success");
		//d.setContentView(tv);
		dialogReattach.show(getSupportFragmentManager(), "REattach box");
		
	}

		
	}
	

	// AttachmentDialog dialog box
	// making the attachments visible
	
	  @Override
	    public void onAttachmentPositiveClick(DialogFragment dialog) {
	        // User touched the dialog's positive button
		  
		  

		  TextView attachment = (TextView) findViewById(R.id.Attachment);
		  attachment.setVisibility(0);
		  
		  ImageView clippy = (ImageView) findViewById(R.id.AttachmentClip);
		  clippy.setVisibility(0);
		  
		  firstAttach = true;
	      
	    }
	  
	  
	  // unused - nothing happens
	  @Override
	    public void onAttachmentNegativeClick(DialogFragment dialog) {
	        // User touched the dialog's negative button
		  	      
	    }
	  
	  
	 // ReattachinDialog box
	 // reattaching the file

		@Override
		public void onReattachingPositiveClick(DialogFragment dialog) {
			
			// set the initial attachment to invisible
		TextView attachment = (TextView) findViewById(R.id.Attachment);
		attachment.setVisibility(4);
			
		TextView REattachment = (TextView) findViewById(R.id.ReattachingFile);
		REattachment.setVisibility(0);
	
		reattached = true;
		
		
			
		}
		
		
		
		@Override
		public void onReattachingNegativeClick(DialogFragment dialog) {
			// TODO Auto-generated method stub
			
		}

	    
		/* called when SEND button is pressed 
		 * New Activity! in a form of a dialog 
		 * WORKS ! */
		
	public void ActivitySendMsg(View view2){
		
		// show the obstructive window only if the attachment is incorrect!
		// when the attachment has been changed - do nothing, just send

		reattachedSTRING="The wrong attachment was sent \n";
		
		if ((!reattached) && obstructionType=='1')
		{
			createFile(reattachedSTRING);		
			//if obstruction type = 1, display the simple version
				Intent intent = new Intent(this, ObstructiveActivity.class);
				
				
				//send the participant number with the intent!
				intent.putExtra("Participant Number", ParticipantNO);
				startActivity(intent);
				
			}
						
			else if (!reattached && obstructionType=='2'){
				
				createFile(reattachedSTRING);
				//if obstruction type = 2, display the window with the time delay	
				
				Intent intent = new Intent(this, ObstructiveActivity2.class);
				
				//send the participant number with the intent!
				intent.putExtra("Participant Number", ParticipantNO);
				startActivity(intent);
				
			}
						
			else if (!reattached && obstructionType=='3'){
				
				createFile(reattachedSTRING);
				
			// if obstruction type = 3, display the window with the delay and vibration			
				Intent intent = new Intent(this, ObstructiveActivity2.class);
				
				//send the participant number with the intent!
				intent.putExtra("Participant Number", ParticipantNO);
				startActivity(intent);
					
				// make it vibrate!
				v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				v.vibrate(4000);
			}

		
		else // reattached = true
			
			{
			
			reattachedSTRING = "The  attachement has been changed and the right one sent \n";
			createFile(reattachedSTRING);
			
			
			// toast, that the message has been sent!
			
			Context context = getApplicationContext();
			SendToast newToast = new SendToast();
			
			newToast.createToast(context);
			

			
			/** end of notification code */

			
			view = view2;
			
           	// thread that will delay delivery of the reply message 
        		new Thread(new Task2()).start();   
			
		}
		

		
	}

	
	
	public void TopNOtification () {
		
		
    	/** notification code*/
    	// notification, that the message has been sent!
    	
		Bitmap largeNotificationIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		
		// setting the notification which tells the user about a message in reply to their own
		// a reply from the supervisor
		
		 final NotificationCompat.Builder posBuilder = new NotificationCompat.Builder(this)
	    .setSmallIcon(R.drawable.notification)
	    .setLargeIcon(largeNotificationIcon)
	    .setContentTitle("1 new message")
	    .setContentText("From: timothy.storer@glasgow.ac.uk")
		.setTicker("New Message");
		
		// Creates an explicit intent for an Activity in the app
		Intent resultIntent = new Intent(this, PositiveResponse.class);
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
		posBuilder.setContentIntent(resultPendingIntent);
		final NotificationManager posMNG = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		posMNG.notify(1, posBuilder.build());
		
	}
	
	
	public void Notification(View view){
		
		Intent intent = new Intent(this, PosNotification.class);
		int requestCode = 1;	
		startActivityForResult(intent, requestCode);
		
	}
	
	
	// thread that delays delivery of the reply message
	class Task2 implements Runnable {
			    @Override
		        public void run() {
			           
			         
			                try {
		                    Thread.sleep(4000);
		                    
			                } catch (InterruptedException e) {
			                    e.printStackTrace();
			                }
			                
			               handler.post(new Runnable() {
			                @Override
			                public void run() {

			                				                	
			                	Notification(view);
			                	TopNOtification();
			                	
			                 }
			               });
			            
			        }
			 
			    }
	
	// creates a test file and saves it in the 'Notes' directory 
	// with the participant's number as a title
	public void createFile(String append) {

				
		
		// participant number
		// obstruction type
		 
		ParticipantMetadata = "Participant Number: " +ParticipantNO+"\n" +
				"Obstruction Type: " +obstructionType+"\n"+
				append;
		
		sFileName = "Results"+ParticipantNO+".txt";
		
		// creating the file
		    try
		    {
		        File root = new File(Environment.getExternalStorageDirectory(), "Notes");

		        if (!root.exists()) {
		            root.mkdirs();
		        }		        
		        
		        File gpxfile = new File(root, sFileName);
		        FileWriter writer = new FileWriter(gpxfile, true);
		        writer.append(ParticipantMetadata);
		        writer.flush();
		        writer.close();
		        
		        //debugging
		        //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
		    }
		    catch(IOException e)
		    {
		         e.printStackTrace();
		    }
	}  
 

}
