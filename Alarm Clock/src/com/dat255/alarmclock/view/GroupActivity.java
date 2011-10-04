package com.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.utilities.Tools;

public class GroupActivity extends Activity {
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.groupscreen);

		button = (Button) findViewById(R.id.homeButton);
		Tools.createOnClickLauncher(button, GroupActivity.this, HomeActivity.class);

		button = (Button) findViewById(R.id.overviewButton);
		Tools.createOnClickLauncher(button, GroupActivity.this, OverviewActivity.class);

		button = (Button) findViewById(R.id.alarmButton);
		Tools.createOnClickLauncher(button, GroupActivity.this, AlarmActivity.class);
	}
}
