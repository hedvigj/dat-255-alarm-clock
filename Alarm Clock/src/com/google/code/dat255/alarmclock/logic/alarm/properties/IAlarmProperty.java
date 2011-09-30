package com.google.code.dat255.alarmclock.logic.alarm.properties;

public interface IAlarmProperty {

	/**
	 * Occurs when the parent alarm is set
	 * 
	 * @param triggerTime
	 *            the set trigger time of the alarm
	 */
	public void onAlarmSet(long triggerTime);

	/**
	 * Occurs when the parent alarm is triggered
	 */
	public void onAlarmTriggered();

}
