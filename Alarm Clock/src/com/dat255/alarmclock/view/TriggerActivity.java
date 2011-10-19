package com.dat255.alarmclock.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;

public class TriggerActivity extends Activity implements OnClickListener {
	private PowerManager powerManager;
	private WakeLock wakeLock;
	private Button snoozeButton;
	private Button ignoreButton;

	private IAlarm alarm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.triggerscreen);

		snoozeButton = (Button) findViewById(R.id.snoozeButton);
		snoozeButton.setOnClickListener(this);
		ignoreButton = (Button) findViewById(R.id.ignoreButton);
		ignoreButton.setOnClickListener(this);

		// Initialize the WakeLock that will be used to prevent the phone from
		// sleeping
		powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "AlarmClock Wake lock");
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();

		// Get the id of the alarm that triggered this screen
		long alarmId = getIntent().getLongExtra("alarmid", 0);

		alarm = AlarmManager.getInstance().findAlarmById(alarmId);

		// Trigger the alarm
		alarm.onAlarmTriggered(this);

		// Is the alarm not visible to the user?
		if (alarm.getVisible() == false) {
			// The alarm is a result of the user wanting to snooze, and thus
			// temporary
			AlarmManager.getInstance().removeAlarm(alarm);
		}

		// Show debug text
		Toast.makeText(this, "Alarm triggered: " + alarmId, Toast.LENGTH_LONG).show();

		// Prevent the phone from sleeping
		wakeLock.acquire();
	}

	@Override
	public void onPause() {
		super.onPause();
		// Allow the phone to sleep
		wakeLock.release();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.snoozeButton:
			// Create an invisible snooze alarm
			IAlarm snoozeAlarm = AlarmManager.getInstance().createAlarm(getApplicationContext(), TriggerActivity.class);

			snoozeAlarm.setProperties(alarm.getProperties());

			snoozeAlarm.setVisible(false);

			snoozeAlarm.snooze(1);

			snoozeAlarm.enable();

			// Notify the user
			Toast.makeText(this, getString(R.string.snooze_message) + " " + 1 + " " + getString(R.string.minutes), Toast.LENGTH_LONG)
					.show();

			break;
		case R.id.ignoreButton:
			// Do nothing at the moment, see below

			break;
		}

		// Notify the alarm of the stop
		alarm.onAlarmStopped(this);

		// Close the activity for now
		finish();
	}

	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(TriggerActivity.this, R.string.not_available, Toast.LENGTH_SHORT).show();
		}

		return true;
	}

}