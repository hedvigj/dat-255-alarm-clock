package com.google.code.dat255.alarmclock.viewhandlers;

import com.google.code.dat255.alarmclock.utilities.Date;

public interface IHomeHandler {

	/**
	 * Get the current date of the system formated
	 * 
	 * @return the current date of the system formated
	 */
	public Date getCurrentDate();

	/**
	 * Change the view to the overview screen
	 */
	public void onOverviewClick();

}
