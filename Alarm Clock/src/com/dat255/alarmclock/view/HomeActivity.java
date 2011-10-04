package com.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.Button;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.utilities.Tools;

public class HomeActivity extends Activity {
	private Button button;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);

		AnalogClock currentTime = (AnalogClock) findViewById(R.id.current_time);
		Tools.createOnClickLauncher(currentTime, HomeActivity.this, AlarmActivity.class);

		button = (Button) findViewById(R.id.overviewButton);
		Tools.createOnClickLauncher(button, HomeActivity.this, OverviewActivity.class);

		button = (Button) findViewById(R.id.alarmButton);
		Tools.createOnClickLauncher(button, HomeActivity.this, AlarmActivity.class);

		button = (Button) findViewById(R.id.groupButton);
		Tools.createOnClickLauncher(button, HomeActivity.this, GroupActivity.class);
	}
}
