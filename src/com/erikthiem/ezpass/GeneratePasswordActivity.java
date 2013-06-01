package com.erikthiem.ezpass;

import java.util.Random;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class GeneratePasswordActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Get the digits from the intent and convert it to an integer
		Intent intent = getIntent();
		String digits_string = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		int digits_int = Integer.parseInt(digits_string);
		
		String password; // will be used to either hold the password or an error message
		
		if (digits_int < 90) {
			// Generate the password
			String possible_password_characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*";
			char[] password_array = new char[digits_int]; // used to store characters of the password
			
			Random generator = new Random(System.currentTimeMillis()); // create the random generator using the current time as a seed
			int r; // used to store random numbers
			char randomChar; // used to store randomly generated characters before they are put into the character array
			
			// Populate the array of characters of the password
			for(int i = 0; i < digits_int; i++) {
				r = generator.nextInt(possible_password_characters.length());
				randomChar = possible_password_characters.charAt(r);
				password_array[i] = randomChar;
			}
			
			// Convert the array of generated characters back to a string
			password = new String(password_array);
		} else {
			password = "The password length must be less than 90. Please go back and try again.";
		}
		
		// Create the text view to display the password
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(password);
		
		// Set the text view as the activity layout
		setContentView(textView);
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
