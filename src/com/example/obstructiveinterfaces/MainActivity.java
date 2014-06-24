package com.example.obstructiveinterfaces;



import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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
ReattachingDialog.ReattachingDialogListener, 
/*SendMessage1.SendMessageListener,*/ InformationDialog.InformationDialogListener {

	int obstructionType;
	Button attachBTN;
	Button sendBTN;
	TextView display;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	boolean firstAttach, reattach;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
	
		reattach = true;
			
		}
		
		
		
		@Override
		public void onReattachingNegativeClick(DialogFragment dialog) {
			// TODO Auto-generated method stub
			
		}
	  
	  

/** this code instantiates a SendMessage1 dialog box
 * it is not used at the moment
 */
	
//	public void SendMessage(View view){
//		
//		DialogFragment d = new SendMessage1();
//		TextView tv = new TextView(this);
//		tv.setText("Success");
//		//d.setContentView(tv);
//		d.show(getSupportFragmentManager(), "send message 1 test");
//		
//	}
//	
//
//	// clicking the Obstructive Send box buttons
//	  @Override
//	    public void onSendPositiveClick(DialogFragment dialog) {
//	        // User touched the dialog's positive button
//		  
//		 
//	      
//	    }
//
//	    @Override
//	    public void onSendNegativeClick(DialogFragment dialog) {
//	        // User touched the dialog's negative button
//	    	//do nothing
//	    	
//	    	
//	        
//	    }
	    
/** end of unused code */
	    
	  
	    
		/* called when SEND button is pressed 
		 * New Activity! in a form of a dialog 
		 * WORKS ! */
		
	public void ActivitySendMsg(View view){
		
		// show the obstructive window only if the attachment is incorrect!
		// when the attachment has been changed - do nothing, just send
		
		
		if (!reattach){
			
			Intent intent = new Intent(this, ObstructiveActivity.class);
			
			startActivity(intent);
		}
		
		else // reattach = true
		{
			// toast, that the message has been sent!
			
			Context context = getApplicationContext();
			SendToast newToast = new SendToast();
			
			newToast.createToast(context);
			
			
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
		


	}

	//what to do when the YES button is clicked on the dialog Activity SEND

	
	    // instantiates the informationDialog
	    
		public void InfoDialog(View view){
			
			DialogFragment infD = new InformationDialog();
			TextView tv = new TextView(this);
			tv.setText("Success");
			//d.setContentView(tv);
			infD.show(getSupportFragmentManager(), "info dialog test");
			
		}

		 // clicking the Information box buttons

			@Override
			public void onInformationPositiveClick(DialogFragment dialog) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void onInformationNegativeClick(DialogFragment dialog) {
				// TODO Auto-generated method stub
				
			}
	
	




}
