package com.dat255.alarmclock.logic.alarm;

import android.content.Context;

public class AlarmFactory {

	public static IAlarm get(Context appContext, long id) {
		return new Alarm(appContext, id);
	}
}
