package com.erikthiem.ezpassV2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	public final static String DIGITS = "com.erikthiem.ezpass.DIGITS";
	public final static String LOWERCASE = "com.erikthiem.ezpass.LOWERCASE";
	public final static String UPPERCASE = "com.erikthiem.ezpass.UPPERCASE";
	public final static String NUMBERS = "com.erikthiem.ezpass.NUMBERS";
	public final static String SPECIAL = "com.erikthiem.ezpass.SPECIAL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GlobalState state = ((GlobalState) getApplicationContext());
		
		// Set the checkboxes
		CheckBox lowercase_letters = (CheckBox) findViewById(R.id.checkBox_lowercase);
		CheckBox uppercase_letters = (CheckBox) findViewById(R.id.checkBox_uppercase);
		CheckBox numbers = (CheckBox) findViewById(R.id.checkBox_numbers);
		CheckBox special_characters = (CheckBox) findViewById(R.id.checkBox_special);
		EditText digits = (EditText) findViewById(R.id.digits);
		
		lowercase_letters.setChecked(state.getLowercaseCheckboxValue());
		uppercase_letters.setChecked(state.getUppercaseCheckboxValue());
		numbers.setChecked(state.getNumbersCheckboxValue());
		special_characters.setChecked(state.getSpecialCheckboxValue());
		digits.setText(Integer.toString(state.getPasswordDigits()));
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
		
		// gets the checkbox options that the user checked
		CheckBox lowercase_letters = (CheckBox) findViewById(R.id.checkBox_lowercase);
		CheckBox uppercase_letters = (CheckBox) findViewById(R.id.checkBox_uppercase);
		CheckBox numbers = (CheckBox) findViewById(R.id.checkBox_numbers);
		CheckBox special_characters = (CheckBox) findViewById(R.id.checkBox_special);
		
		// set booleans that coorespond to the checkboxes
		boolean lowercase_letters_checked = lowercase_letters.isChecked();
		boolean uppercase_letters_checked = uppercase_letters.isChecked();
		boolean numbers_checked = numbers.isChecked();
		boolean special_characters_checked = special_characters.isChecked();
		
		// save the checkbox booleans and the number of digits to the global state
		GlobalState state = ((GlobalState) getApplicationContext());
		state.setLowercaseCheckboxValue(lowercase_letters_checked);
		state.setUppercaseCheckboxValue(uppercase_letters_checked);
		state.setNumbersCheckboxValue(numbers_checked);
		state.setSpecialCheckboxValue(special_characters_checked);
		state.setPasswordDigits(Integer.parseInt(digits));
		
		// this stops the program from continuing (and throwing an error)
		// if the user has not entered anything
		if (digits.length() > 0) {
			generatePassword.putExtra(DIGITS, digits);
			generatePassword.putExtra(LOWERCASE, lowercase_letters_checked);
			generatePassword.putExtra(UPPERCASE, uppercase_letters_checked);
			generatePassword.putExtra(NUMBERS, numbers_checked);
			generatePassword.putExtra(SPECIAL, special_characters_checked);
			startActivity(generatePassword);
		}
	}
	
	public void increaseDigitsByOne(View view) {
		
		// get the current value of the digits
		EditText digits_edittext = (EditText) findViewById(R.id.digits);
		int current_digits = Integer.parseInt(digits_edittext.getText().toString());
		int desired_digits;
		
		// increase the value of the digits only if doing so will not put the digits over the maximum allowed digits
		if (current_digits < 100) {
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
