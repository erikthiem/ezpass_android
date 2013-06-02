package com.erikthiem.ezpass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.erikthiem.ezpass.DIGITS";

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
		Intent generatePassword = new Intent(this, GeneratePasswordActivity.class);
		EditText digits_edittext = (EditText) findViewById(R.id.digits);
		
		// gets the number of digits that the user entered and
		// saved it to a string
		String digits = digits_edittext.getText().toString();
		
		// this stops the program from continuing (and throwing an error)
		// if the user has not entered anything
		if (digits.length() > 0) {
			generatePassword.putExtra(EXTRA_MESSAGE, digits);
			startActivity(generatePassword);
		}
	}
	
	public void increaseDigitsByOne(View view) {
		// get the current value of the digits
		EditText digits_edittext = (EditText) findViewById(R.id.digits);
		int current_digits = Integer.parseInt(digits_edittext.getText().toString());
		int desired_digits;
		
		// increase the value of the digits only if doing so will not put the digits over the maximum allowed digits
		if (current_digits < 89) {
			desired_digits = current_digits + 1;
		} else {
			desired_digits = current_digits;
		}
		
		// set the new digits
		digits_edittext.setText(Integer.toString(desired_digits));
		
	}
	
	public void decreaseDigitsByOne(View view) {
		EditText digits_edittext = (EditText) findViewById(R.id.digits);
		int current_digits = Integer.parseInt(digits_edittext.getText().toString());
		int desired_digits;
		
		// decrease the value of the digits only if doing so will not put the digits under 1
		if (current_digits > 1) {
			desired_digits = current_digits - 1;
		} else {
			desired_digits = current_digits;
		}
		
		// set the new digits
		digits_edittext.setText(Integer.toString(desired_digits));
	}

}
