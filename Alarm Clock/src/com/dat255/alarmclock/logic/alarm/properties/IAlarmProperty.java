package com.dat255.alarmclock.logic.alarm.properties;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public interface IAlarmProperty {

	/**
	 * Occurs when the parent alarm is set
	 * 
	 * @param triggerTime
	 *            the set trigger time of the alarm
	 */
	public void onAlarmSet(IAlarm reference);

	/**
	 * Occurs when the parent alarm is triggered
	 */
	public void onAlarmTriggered();

}
