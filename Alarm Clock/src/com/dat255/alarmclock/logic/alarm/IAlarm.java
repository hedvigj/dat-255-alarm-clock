package com.dat255.alarmclock.logic.alarm;

import android.content.Context;

import com.dat255.alarmclock.logic.common.IEntity;

public interface IAlarm extends IEntity {

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
	 * Gets whether or not this alarm is visible to the user
	 * 
	 * @return true if visible, otherwise false
	 */
	public boolean getVisible();

	/**
	 * Sets whether or not this alarm is visible to the user
	 * 
	 * @param visible
	 *            true if visible, otherwise false
	 */
	public void setVisible(boolean visible);

	/**
	 * Enables the alarm
	 */
	public void enable();

	/**
	 * Disables the alarm
	 */
	public void disable();

	/**
	 * @return true if the alarm is enabled, otherwise false
	 */
	public boolean isEnabled();

	/**
	 * Occurs when the alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	public void onAlarmTriggered(Context context);

	/**
	 * Occurs when the alarm is stopped
	 * 
	 * @param context
	 *            the context in which the alarm is stopped
	 */
	public void onAlarmStopped(Context context);

	/**
	 * Sets the trigger time of the alarm a number of minutes into the future.
	 * Remember that this call changes the trigger time of the alarm and thus
	 * should not be used when the user expects his/her input time to apply.
	 * Instead, create a new alarm and call this method on the new instance.
	 * 
	 * @param minutes
	 *            the number of minutes to snooze
	 */
	public void snooze(int minutes);

	/**
	 * Adds a number of milliseconds to the set trigger time.
	 * 
	 * @param milliseconds
	 *            the number of milliseconds to add
	 */
	public void addTriggerTime(int milliseconds);
}