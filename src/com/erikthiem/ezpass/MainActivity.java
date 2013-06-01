package com.erikthiem.ezpass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void generatePassword(View view) {
		Intent intent = new Intent(this, GeneratePasswordActivity.class);
		EditText editText = (EditText) findViewById(R.id.digits);
		
		// gets the number of digits that the user entered and
		// saved it to a string
		String digits = editText.getText().toString();
		
		// this stops the program from continuing (and throwing an error)
		// if the user has not entered anything
		if (digits.length() > 0) {
			intent.putExtra(EXTRA_MESSAGE, digits);
			startActivity(intent);
		}
		
	}

}
