package com.example.obstructiveinterfaces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InitialActivity extends Activity {
	
	protected static final String PUBLIC_STATIC_STRING_IDENTIFIER = "passing the participant number";
	private Button CONTinitialBTN ;
	private String concatenatedResult="";
	private String ParticipantNumber="";
	private String ObstructionType="";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);

		final EditText obtr_type = (EditText) findViewById(R.id.A_input_obsr);
		obtr_type.setText(" ");
		
		final EditText number_part = (EditText) findViewById(R.id.A_input_partnum);
		number_part.setText(" ");
		
		CONTinitialBTN = (Button) findViewById(R.id.initial_continueBTN);	
	
	
	CONTinitialBTN.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
       	
			ObstructionType = obtr_type.getText().toString().trim();
			ParticipantNumber = number_part.getText().toString().trim();
						
			concatenatedResult = ObstructionType+ParticipantNumber;
		
			// passing the Participant Number back to main activity
			Intent resultIntent = new Intent();
			resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, concatenatedResult);
			setResult(Activity.RESULT_OK, resultIntent);
			
			
			
        	finish(); // close the dialog box

		}
	});
	
	}

	
}
