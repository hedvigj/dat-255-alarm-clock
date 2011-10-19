package com.dat255.alarmclock.logic.alarm.properties;

import android.content.Context;

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
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	public void onAlarmTriggered(Context context);

	/**
	 * Occurs when the parent alarm is stopped
	 * 
	 * @param context
	 *            the context in which the alarm is stopped
	 */
	public void onAlarmStopped(Context context);

	/**
	 * Creates a deep clone if this alarm property
	 * 
	 * @return a new IAlarmProperty instance
	 */
	public IAlarmProperty deepClone();

}
