package com.example.obstructiveinterfaces;



import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends ActionBarActivity implements AttachmentDialog.AttachmentDialogListener, SendMessage1.SendMessageListener {

	int obstructionType;
	Button attachBTN;
	Button sendBTN;
	TextView display;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	
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
	
	/* called when SEND button is pressed 
	 * New Activity! 
	 * WORKS ! */
	
public void ActivitySendMsg(View view){
	
	
	Intent intent = new Intent(this, ObstructiveActivity.class);
		
	startActivity(intent);

}

//what to do when the YES button is clicked on the dialog Activity SEND


	
	
	/* 
	 * WORKS!
	 * displays a dialog box when ATTACH button is clicked */
	
	public void DialogBox(View view){
		
		DialogFragment d = new AttachmentDialog();
		//d.setTitle("Dialog Test");
		TextView tv = new TextView(this);
		tv.setText("Success");
		//d.setContentView(tv);
		d.show(getSupportFragmentManager(), "dialog box test");
		
		
	}
	
	
	// making the attachments visible
	  @Override
	    public void onDialogPositiveClick(DialogFragment dialog) {
	        // User touched the dialog's positive button
		  
		  TextView attachment = (TextView) findViewById(R.id.AttachmentText);
		  attachment.setVisibility(0);
		  
		  ImageView clippy = (ImageView) findViewById(R.id.AttachmentClip);
		  clippy.setVisibility(0);
	      
	    }


	
	public void SendMessage(View view){
		
		DialogFragment d = new SendMessage1();
		TextView tv = new TextView(this);
		tv.setText("Success");
		//d.setContentView(tv);
		d.show(getSupportFragmentManager(), "send message 1 test");
		
	}
	

	// making the attachments visible
	  @Override
	    public void onDialogPositiveClick2(DialogFragment dialog) {
	        // User touched the dialog's positive button
		  
		  TextView attachment = (TextView) findViewById(R.id.AttachmentText);
		  attachment.setVisibility(0);
		  
		  ImageView clippy = (ImageView) findViewById(R.id.AttachmentClip);
		  clippy.setVisibility(0);
	      
	    }

	    @Override
	    public void onDialogNegativeClick(DialogFragment dialog) {
	        // User touched the dialog's negative button
	    	//do nothing
	        
	    }




	
	



}
