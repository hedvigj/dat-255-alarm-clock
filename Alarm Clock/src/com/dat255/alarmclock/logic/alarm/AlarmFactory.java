package com.dat255.alarmclock.logic.alarm;

import android.app.Activity;
import android.content.Context;

public class AlarmFactory {

	/**
	 * Initializes a new alarm with a specific id
	 * 
	 * @param appContext
	 *            the Android application context
	 * @param action
	 *            the activity class to open when the alarm is triggered
	 * @param id
	 *            the desired alarm id
	 * @return a new IAlarm instance
	 */
	public static IAlarm get(Context appContext, Class<? extends Activity> action, long id) {
		return new Alarm(appContext, action, id);
	}
}
