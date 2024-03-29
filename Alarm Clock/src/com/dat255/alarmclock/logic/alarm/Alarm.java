package com.dat255.alarmclock.logic.alarm;

import java.util.Calendar;

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

	private boolean visible;

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
		this.visible = true;
		this.triggerTime = 0;

		this.initializeIntent(action);
	}

	/**
	 * Sets up this alarm's pending intent
	 */
	private void initializeIntent(Class<? extends Activity> action) {
		Intent intent = new Intent(appContext, action);

		intent.putExtra("alarmid", id);

		pendingIntent = PendingIntent.getActivity(appContext, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
	}

	/**
	 * Validates the trigger time of the alarm. The trigger time is only
	 * considered valid when the alarm is enabled.
	 */
	private void validateTriggerTime() {
		// If the trigger time is in the past, set it to one day in the future
		Calendar current = Calendar.getInstance();
		Calendar value = Calendar.getInstance();

		value.setTimeInMillis(triggerTime);

		if (value.before(current)) {
			value.set(Calendar.DAY_OF_WEEK, current.get(Calendar.DAY_OF_WEEK) + 1);
		}

		triggerTime = value.getTimeInMillis();
	}

	/**
	 * Occurs when the alarm is set
	 */
	private void onAlarmSet() {
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
			// Validate the trigger time
			validateTriggerTime();

			// Notify all properties
			onAlarmSet();

			// Register the alarm
			android.app.AlarmManager manager = (android.app.AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);

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
			// Unregister the alarm
			android.app.AlarmManager manager = (android.app.AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);

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
	 * Gets whether or not this alarm is visible to the user
	 * 
	 * @return true if visible, otherwise false
	 */
	@Override
	public boolean getVisible() {
		return visible;
	}

	/**
	 * Sets whether or not this alarm is visible to the user
	 * 
	 * @param visible
	 *            true if visible, otherwise false
	 */
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Occurs when the alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	@Override
	public void onAlarmTriggered(Context context) {
		disable();

		if (properties != null) {
			for (IAlarmProperty property : properties) {
				property.onAlarmTriggered(context);
			}
		}
	}

	/**
	 * Occurs when the alarm is stopped
	 * 
	 * @param context
	 *            the context in which the alarm is stopped
	 */
	@Override
	public void onAlarmStopped(Context context) {
		if (properties != null) {
			for (IAlarmProperty property : properties) {
				property.onAlarmStopped(context);
			}
		}
	}

	/**
	 * Gets a deep clone of all alarm properties
	 * 
	 * @return an array of IAlarmProperty instances
	 */
	@Override
	public IAlarmProperty[] getProperties() {
		if (properties != null) {
			IAlarmProperty[] output = new IAlarmProperty[properties.length];

			for (int i = 0; i < properties.length; i++) {
				output[i] = properties[i].deepClone();
			}

			return output;
		}

		return null;
	}

	/**
	 * Sets the properties of this alarm
	 * 
	 * @param properties
	 *            the desired properties
	 */
	@Override
	public void setProperties(IAlarmProperty[] properties) {
		this.properties = properties;
	}

	/**
	 * Sets the trigger time of the alarm a number of minutes into the future.
	 * Remember that this call changes the trigger time of the alarm and thus
	 * should not be used when the user expects his/her input time to apply.
	 * Instead, create a new alarm and call this method on the new instance.
	 * 
	 * @param minutes
	 *            the number of minutes to snooze
	 */
	@Override
	public void snooze(int minutes) {
		// Get current time
		Calendar current = Calendar.getInstance();

		// Add the number of snooze minutes, allow no negative values
		current.add(Calendar.MINUTE, Math.abs(minutes));

		// Set the alarm trigger time
		setTriggerTime(current.getTimeInMillis());
	}

	/**
	 * Adds a number of milliseconds to the set trigger time.
	 * 
	 * @param milliseconds
	 *            the number of milliseconds to add
	 */
	@Override
	public void addTriggerTime(int milliseconds) {
		// Get current trigger time
		Calendar current = Calendar.getInstance();

		// Start with the current trigger time
		current.setTimeInMillis(triggerTime);

		// Add the number of milliseconds, allow no negative values
		current.add(Calendar.MILLISECOND, Math.abs(milliseconds));

		// Set the alarm trigger time
		setTriggerTime(current.getTimeInMillis());
	}
}