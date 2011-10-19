package com.dat255.alarmclock.logic.alarm.properties;

import android.content.Context;
import android.os.Vibrator;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public class VibrationProperty implements IAlarmProperty {

	private Vibrator vibrator;

	@Override
	public void onAlarmSet(IAlarm reference) {
		// None
	}

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

	@Override
	public void onAlarmStopped(Context context) {
		if (vibrator != null) {
			// Cancel the vibration
			vibrator.cancel();

			// Reset the reference
			vibrator = null;
		}
	}

}
