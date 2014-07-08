package com.example.obstructiveinterfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NegNotification extends Activity {

Button NEGcancel, NEGreadMSG;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neg_notification);
		
		
		//getActionBar().setIcon(R.drawable.newmessage);
		
		NEGcancel = (Button) findViewById(R.id.NEGcancelBTN);
		NEGreadMSG = (Button) findViewById(R.id.NEGreadBTN);
		
		
		// clicking the CANCEL button
		NEGcancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            	//close dialog
            	finish();
            }
        });
		
		
		// clicking the READ button
		NEGreadMSG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            	//open message
                
            	openMSG(v);
            	
            	finish();
            }
        });
	}
	
	
	public void openMSG(View view){
 		
		Intent intent = new Intent(this, NegativeResponse.class);
		int requestCode = 5;	
		startActivityForResult(intent, requestCode);
    	
	}
}
