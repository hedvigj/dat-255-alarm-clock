package com.google.code.dat255.alarmclock.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class TriggerActivity extends Activity {
	private PowerManager powerManager;
	private WakeLock wakeLock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize the WakeLock that will be used to prevent the phone from
		// sleeping
		powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "AlarmClock Wake lock");
	}

	@Override
	public void onResume() {
		// Prevent the phone from sleeping
		wakeLock.acquire();
	}

	@Override
	public void onPause() {
		// Allow the phone to sleep
		wakeLock.release();
	}
}
