package com.google.code.dat255.alarmclock.viewhandlers;

import java.util.Calendar;

import com.google.code.dat255.alarmclock.utilities.Date;

public class HomeHandler implements IHomeHandler {

	@Override
	public Date getCurrentDate() {
		return new Date(Calendar.getInstance());
	}

	@Override
	public void onOverviewClick() {
		// As of now, nothing happends
	}

}
