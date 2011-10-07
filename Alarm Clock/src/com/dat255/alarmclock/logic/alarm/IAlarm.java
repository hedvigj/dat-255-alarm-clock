package com.dat255.alarmclock.logic.alarm;

import android.content.Context;

public interface IAlarm {

	/**
	 * @return the id of this alarm
	 */
	public long getId();

	/**
	 * Gets the time when the alarm will be triggered
	 * 
	 * @return the time in milliseconds
	 */
	public long getTriggerTime();

	/**
	 * Sets the time when the alarm should be triggered
	 * 
	 * @param triggerTime
	 *            the time in milliseconds
	 */
	public void setTriggerTime(long triggerTime);

	/**
	 * Occurs when the alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	public void onAlarmTriggered(Context context);

}
