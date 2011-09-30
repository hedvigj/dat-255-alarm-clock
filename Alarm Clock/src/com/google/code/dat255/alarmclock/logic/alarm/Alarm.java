package com.google.code.dat255.alarmclock.logic.alarm;

import com.google.code.dat255.alarmclock.logic.alarm.properties.IAlarmProperty;

public class Alarm implements IAlarm {

	private long triggerTime;

	private IAlarmProperty[] properties;

	@Override
	public long getTriggerTime() {
		return triggerTime;
	}

}
