package com.dat255.alarmclock.logic.alarm.properties;

import android.content.Context;
import android.os.Vibrator;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public class VibrationProperty implements IAlarmProperty {

	private Vibrator vibrator;

	/**
	 * Occurs when the parent alarm is set
	 * 
	 * @param triggerTime
	 *            the set trigger time of the alarm
	 */
	@Override
	public void onAlarmSet(IAlarm reference) {
		// None
	}

	/**
	 * Occurs when the parent alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	@Override
	public void onAlarmTriggered(Context context) {
		if (vibrator == null) {
			vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

			// Cancel any previous vibration
			vibrator.cancel();

			// Start vibrating
			long[] pattern = { 250, 500 };

			vibrator.vibrate(pattern, 0);
		}
	}

	/**
	 * Occurs when the parent alarm is stopped
	 * 
	 * @param context
	 *            the context in which the alarm is stopped
	 */
	@Override
	public void onAlarmStopped(Context context) {
		if (vibrator != null) {
			// Cancel the vibration
			vibrator.cancel();

			// Reset the reference
			vibrator = null;
		}
	}

	/**
	 * Creates a deep clone if this alarm property
	 * 
	 * @return a new IAlarmProperty instance
	 */
	@Override
	public IAlarmProperty deepClone() {
		return new VibrationProperty();
	}

}
