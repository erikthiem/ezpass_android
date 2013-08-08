package com.erikthiem.ezpassV2;
import android.app.Application;

public class GlobalState extends Application {
	
	// The variables that hold the checkboxes states
	
	private boolean mainactivity_lowercase_selected;
	private boolean mainactivity_uppercase_selected;
	private boolean mainactivity_numbers_selected;
	private boolean mainactivity_special_selected;
	private int password_digits;
	
	
	// Set initial values
	
	public void onCreate()
	{
		this.mainactivity_lowercase_selected = true;
		this.mainactivity_uppercase_selected = true;
		this.mainactivity_numbers_selected = true;
		this.mainactivity_special_selected = true;
		this.password_digits = 8;
	}
	
	
	
	// Getters
	
	public boolean getLowercaseCheckboxValue()
	{
		return this.mainactivity_lowercase_selected;
	}
	
	public boolean getUppercaseCheckboxValue()
	{
		return this.mainactivity_uppercase_selected;
	}
	
	public boolean getNumbersCheckboxValue()
	{
		return this.mainactivity_numbers_selected;
	}
	
	public boolean getSpecialCheckboxValue()
	{
		return this.mainactivity_special_selected;
	}
	
	public int getPasswordDigits()
	{
		return this.password_digits;
	}
	
	
	
	// Setters
	
	public void setLowercaseCheckboxValue(boolean value)
	{
		this.mainactivity_lowercase_selected = value;
	}
	
	public void setUppercaseCheckboxValue(boolean value)
	{
		this.mainactivity_uppercase_selected = value;
	}
	
	public void setNumbersCheckboxValue(boolean value)
	{
		this.mainactivity_numbers_selected = value;
	}
	
	public void setSpecialCheckboxValue(boolean value)
	{
		this.mainactivity_special_selected = value;
	}
	
	public void setPasswordDigits(int digits)
	{
		this.password_digits = digits;
	}
	
}