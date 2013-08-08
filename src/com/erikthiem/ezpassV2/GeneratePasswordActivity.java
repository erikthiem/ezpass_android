package com.erikthiem.ezpassV2;

import java.util.Random;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GeneratePasswordActivity extends Activity {
	
	private String generated_password;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		generateAndDisplayPassword();
		
	}
	
	public void generateAndDisplayPassword()
	{
		
		setContentView(R.layout.activity_generate_password);
		
		// Get the digits from the intent and convert it to an integer
		Intent intent = getIntent();
		String digits_string = intent.getStringExtra(MainActivity.DIGITS);
		int digits_int = Integer.parseInt(digits_string);
		
		// Get the checkmark booleans from the intent
		boolean include_lowercase = intent.getBooleanExtra(MainActivity.LOWERCASE, true);
		boolean include_uppercase = intent.getBooleanExtra(MainActivity.UPPERCASE, true);
		boolean include_numbers = intent.getBooleanExtra(MainActivity.NUMBERS, true);
		boolean include_special = intent.getBooleanExtra(MainActivity.SPECIAL, true);

		String error = "";
		
		if (!include_lowercase && !include_uppercase && !include_numbers && !include_special)
		{
			error = "You must select at least one password constraint checkbox. Please go back and try again.";
		}
		
		else
		{
			
			if (digits_int <= 100 && digits_int > 0) {
				
				// Generate the password based on which checkboxes were checked
				int max_unique_characters = 71;
				StringBuilder possible_password_characters = new StringBuilder(max_unique_characters);
				
				if (include_lowercase)
				{
					possible_password_characters.append("abcdefghijklmnopqrstuvwxyz");
				}
				if (include_uppercase)
				{
					possible_password_characters.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
				}
				if (include_numbers)
				{
					possible_password_characters.append("0123456789");
				}
				if (include_special)
				{
					possible_password_characters.append("~!@#$%^&*");
				}
				
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
				this.generated_password = new String(password_array);
			} else {
				// This is used here as an error message
				error = "The password length must be between 1 and 100 digits. Please go back and try again.";
			}
		
		}
		
		
		// Set the text view to display the password
		TextView textView = (TextView)findViewById(R.id.generatedPasswordText);
		textView.setTextSize(32);
		if (error.isEmpty())
		{
			textView.setText(this.generated_password);
		} else {
			textView.setText(error);
		}
		
		// Set the text view as the activity layout
		//setContentView(textView);
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void copyPasswordToClipboard()
	{
		// Copy the password differently based on what API level the user is using
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		
		if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
		     android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
		        ClipData clip = ClipData.newPlainText("label", this.generated_password);
		        clipboard.setPrimaryClip(clip); 
		} else{
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
		    clipboard.setText(this.generated_password);
		}
	    
	    Toast.makeText(getApplicationContext(), "Password copied to clipboard", Toast.LENGTH_SHORT).show();

	}
	
	public void onClick(View view)
	{
	    switch (view.getId())
	    {
	        case R.id.returnToStart:
	        	finish();
	        	break;
	        	
	        case R.id.reGenerate:
	        	generateAndDisplayPassword();
	        	break;
	        	
	        case R.id.copyToClipboard:
	        	copyPasswordToClipboard();
	        	break;
	    }

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
