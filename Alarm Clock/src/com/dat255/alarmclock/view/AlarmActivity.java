package com.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.utilities.Tools;

public class AlarmActivity extends Activity {
	private Button button;
	private TimePicker timePicker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.alarmscreen);

		button = (Button) findViewById(R.id.homeButton);
		Tools.createOnClickLauncher(button, AlarmActivity.this, HomeActivity.class);

		button = (Button) findViewById(R.id.overviewButton);
		Tools.createOnClickLauncher(button, AlarmActivity.this, OverviewActivity.class);

		button = (Button) findViewById(R.id.groupButton);
		Tools.createOnClickLauncher(button, AlarmActivity.this, GroupActivity.class);

		timePicker = (TimePicker) findViewById(R.id.alarmtimepicker);

		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			}
		});

	}
}
