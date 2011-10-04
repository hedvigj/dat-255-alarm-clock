package com.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.utilities.Tools;

public class OverviewActivity extends Activity {
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overviewscreen);

		button = (Button) findViewById(R.id.homeButton);
		Tools.createOnClickLauncher(button, OverviewActivity.this, HomeActivity.class);

		button = (Button) findViewById(R.id.alarmButton);
		Tools.createOnClickLauncher(button, OverviewActivity.this, AlarmActivity.class);

		button = (Button) findViewById(R.id.groupButton);
		Tools.createOnClickLauncher(button, OverviewActivity.this, GroupActivity.class);

	}
}
