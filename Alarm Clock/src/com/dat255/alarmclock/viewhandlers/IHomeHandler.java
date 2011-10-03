package com.dat255.alarmclock.viewhandlers;

import com.dat255.alarmclock.utilities.Date;

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
