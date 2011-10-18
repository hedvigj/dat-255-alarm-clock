package com.dat255.alarmclock.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.utilities.Sound;

public class TriggerActivity extends Activity implements OnClickListener {
	private PowerManager powerManager;
	private WakeLock wakeLock;
	private Button snoozeButton;
	private Button ignoreButton;

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
		// Start alarm sound if not already started
		if (Sound.getInstance().isSoundOn() == false) {
			Sound.getInstance().soundLoopStart(this);
		}

		// Get the id of the alarm that triggered this screen
		long alarmId = getIntent().getLongExtra("alarmid", 0);

		IAlarm alarm = AlarmManager.getInstance().findAlarmById(alarmId);

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
			Sound.getInstance().soundStop();

			// Create an invisible snooze alarm
			IAlarm snoozeAlarm = AlarmManager.getInstance().createAlarm(getApplicationContext(), TriggerActivity.class);

			snoozeAlarm.setVisible(false);

			snoozeAlarm.snooze(1);

			snoozeAlarm.enable();

			// Notify the user
			Toast.makeText(this, "Alarm will sound again in 1 min", Toast.LENGTH_LONG).show();

			break;
		case R.id.ignoreButton:
			Sound.getInstance().soundStop();

			break;
		}

		// Close the activity for now
		finish();
	}
}