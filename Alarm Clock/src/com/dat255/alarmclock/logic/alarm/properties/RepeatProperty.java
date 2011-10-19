package com.dat255.alarmclock.logic.alarm.properties;

import java.util.Calendar;

import android.content.Context;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public class RepeatProperty implements IAlarmProperty {

	private int repeatOnWeekday;

	private IAlarm setAlarm;

	public RepeatProperty(int repeatOnWeekday) {
		this.repeatOnWeekday = repeatOnWeekday;
	}

	/**
	 * Occurs when the parent alarm is set
	 * 
	 * @param triggerTime
	 *            the set trigger time of the alarm
	 */
	@Override
	public void onAlarmSet(IAlarm reference) {
		// Only postpone the alarm trigger time if the alarm is visible to the
		// user (snooze alarms should not be affected, for example)
		if (reference.getVisible()) {

			// Get a calendar instance to work with
			Calendar time = Calendar.getInstance();

			time.setTimeInMillis(reference.getTriggerTime());

			// Is the time not set to the correct weekday?
			int currentWeekday = time.get(Calendar.DAY_OF_WEEK);

			if (currentWeekday != repeatOnWeekday) {
				// What do we need to add to get to the correct weekday?
				// Example: Tuesday (3) to Friday (6); 6-3=3
				int delta = repeatOnWeekday - currentWeekday;

				// Wrap the delta value
				// Example: Friday (6) to Sunday (1); 1-6=-5; wrap(-5)=-5+7=2
				if (delta < 0) {
					delta += 7;
				}

				// Set the time to the next correct day in the future
				time.add(Calendar.DAY_OF_WEEK, delta);
			}

			reference.setTriggerTime(time.getTimeInMillis());
		}

		// Save the alarm reference
		this.setAlarm = reference;
	}

	/**
	 * Occurs when the parent alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	@Override
	public void onAlarmTriggered(Context context) {
		// None
	}

	/**
	 * Occurs when the parent alarm is stopped
	 * 
	 * @param context
	 *            the context in which the alarm is stopped
	 */
	@Override
	public void onAlarmStopped(Context context) {
		// The alarm was stopped, re-enable it and make it sound in a week
		setAlarm.enable();
	}

	/**
	 * Creates a deep clone if this alarm property
	 * 
	 * @return a new IAlarmProperty instance
	 */
	@Override
	public IAlarmProperty deepClone() {
		return new RepeatProperty(repeatOnWeekday);
	}
}