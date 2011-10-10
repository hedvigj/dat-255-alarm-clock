package com.dat255.alarmclock.view;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.Button;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;
import com.dat255.alarmclock.utilities.Tools;

public class HomeActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.homescreen);

		AnalogClock currentTime = (AnalogClock) findViewById(R.id.current_time);
		Tools.createOnClickLauncher(currentTime, HomeActivity.this, AlarmActivity.class);

		Button overviewButton = (Button) findViewById(R.id.overviewButton);
		Tools.createOnClickLauncher(overviewButton, HomeActivity.this, OverviewActivity.class);

		// Setup some test alarms! Remove in the future!
		IGroup group = GroupManager.getInstance().createGroup();

		group.setName("Skola-gruppen");

		for (int i = 0; i < 2; i++) {
			IAlarm alarm = AlarmManager.getInstance().createAlarm(getApplicationContext(), TriggerActivity.class);

			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, i + 1);
			alarm.setTriggerTime(c.getTimeInMillis());

			alarm.enable();

			group.addAlarm(alarm);
		}

		IGroup group2 = GroupManager.getInstance().createGroup();

		group2.setName("Jobb-gruppen");
	}
}
