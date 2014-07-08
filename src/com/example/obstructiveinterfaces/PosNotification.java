package com.example.obstructiveinterfaces;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class PosNotification extends Activity {

private Button cancel, readMSG;
private Handler handler;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pos_notification);
		
		handler = new Handler();
		
		cancel = (Button) findViewById(R.id.cancelBTN);
		readMSG = (Button) findViewById(R.id.readBTN);
		
		
		// clicking the CANCEL button
		cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            	
            	//close dialog
            	finish();
            }
        });
		
	
		
		// clicking the READ button
		readMSG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	openMSG(v);
            	
            	finish();
            }
        });
	}
	
	
	public void openMSG(View view){
 		
		Intent intent = new Intent(this, PositiveResponse.class);
		int requestCode = 10;	
		startActivityForResult(intent, requestCode);
    	
	}
	

}
