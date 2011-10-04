package com.dat255.alarmclock.viewhandlers;

import java.util.Calendar;

import android.content.Context;

import com.dat255.alarmclock.utilities.Date;

public class HomeHandler implements IHomeHandler {

	@Override
	public Date getCurrentDate(Context context) {
		return new Date(Calendar.getInstance(), context);
	}

	@Override
	public void onOverviewClick() {
		// As of now, nothing happends
	}

}
