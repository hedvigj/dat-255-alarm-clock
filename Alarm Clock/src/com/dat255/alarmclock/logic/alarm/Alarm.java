package com.dat255.alarmclock.logic.alarm;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.dat255.alarmclock.logic.alarm.properties.IAlarmProperty;

/**
 * The standard alarm class
 */
public class Alarm implements IAlarm {

	private Context appContext;

	private long id;

	private boolean enabled;

	private long triggerTime;

	private IAlarmProperty[] properties;

	private PendingIntent pendingIntent;

	/**
	 * Initializes a new alarm with a specific id
	 * 
	 * @param appContext
	 *            the Android application context
	 * @param action
	 *            the activity class to open when the alarm is triggered
	 * @param id
	 *            the desired alarm id
	 */
	public Alarm(Context appContext, Class<? extends Activity> action, long id) {
		this.appContext = appContext;
		this.id = id;
		this.enabled = false;
		this.triggerTime = 0;

		this.initializeIntent(action);
	}

	/**
	 * Sets up this alarm's pending intent
	 */
	private void initializeIntent(Class<? extends Activity> action) {
		Intent intent = new Intent(appContext, action);

		intent.putExtra("alarmid", id);

		pendingIntent = PendingIntent.getBroadcast(appContext, 0, intent, 0);
	}

	/**
	 * Updates all properties of a change in this alarm
	 */
	private void updateAlarmProperties() {
		if (properties != null) {
			for (IAlarmProperty property : properties) {
				property.onAlarmSet(this);
			}
		}
	}

	/**
	 * Enables the alarm
	 */
	@Override
	public void enable() {
		if (!enabled) {
			// Enable
			updateAlarmProperties();

			android.app.AlarmManager manager = (android.app.AlarmManager) appContext.getSystemService(appContext.ALARM_SERVICE);

			manager.set(android.app.AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);

			enabled = true;
		}
	}

	/**
	 * Disables the alarm
	 */
	@Override
	public void disable() {
		if (enabled) {
			// Disable
			android.app.AlarmManager manager = (android.app.AlarmManager) appContext.getSystemService(appContext.ALARM_SERVICE);

			manager.cancel(pendingIntent);

			enabled = false;
		}
	}

	/**
	 * @return true if the alarm is enabled, otherwise false
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @return the id of this alarm
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * Gets the time when the alarm will be triggered
	 * 
	 * @return the time in milliseconds
	 */
	@Override
	public void setTriggerTime(long triggerTime) {
		boolean originallyEnabled = this.isEnabled();

		if (originallyEnabled) {
			this.disable();
		}

		this.triggerTime = triggerTime;

		if (originallyEnabled) {
			this.enable();
		}
	}

	/**
	 * Sets the time when the alarm should be triggered
	 * 
	 * @param triggerTime
	 *            the time in milliseconds
	 */
	@Override
	public long getTriggerTime() {
		return triggerTime;
	}

	/**
	 * Occurs when the alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	@Override
	public void onAlarmTriggered(Context context) {
		for (IAlarmProperty property : properties) {
			property.onAlarmTriggered(context);
		}
	}

	/**
	 * Sets the properties of this alarm
	 * 
	 * @param properties
	 *            the desired properties
	 */
	public void setProperties(IAlarmProperty[] properties) {
		this.properties = properties;
	}
}