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
import com.dat255.alarmclock.utilities.Tools;

public class TriggerActivity extends Activity implements OnClickListener {
	private PowerManager powerManager;
	private WakeLock wakeLock;
	private Button snoozeButton;
	private Button ignoreButton;
	private IAlarm alarm = null;
	private long alarmId;

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
		if (Sound.isSoundOn() == false) {
			Sound.soundStart(this);
		}

		// Get the id of the alarm that triggered this screen
		alarmId = getIntent().getLongExtra("alarmid", 0);

		alarm = AlarmManager.getInstance().findAlarmById(alarmId);

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
			Sound.soundStop(this);
			Toast.makeText(this, "Alarm will ring again in 5 min", Toast.LENGTH_LONG).show();
			alarm.disable();
			break;
		case R.id.ignoreButton:
			Sound.soundStop(this);

			Tools.createOnClickLauncher(ignoreButton, TriggerActivity.this, HomeActivity.class);
		}

	}
}
