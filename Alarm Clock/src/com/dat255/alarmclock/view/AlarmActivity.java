package com.dat255.alarmclock.view;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.utilities.Tools;

public class AlarmActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.alarmscreen);

		CheckBox checkRepeat = (CheckBox) findViewById(R.id.repeatCheckBox);
		final LinearLayout linearWeekdays = (LinearLayout) findViewById(R.id.weekdays);
		linearWeekdays.setVisibility(View.GONE);

		// Make the weekdays visible if the checkRepeat checkbox is checked,
		// invisible otherwise
		checkRepeat.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					linearWeekdays.setVisibility(View.VISIBLE);
				} else {
					linearWeekdays.setVisibility(View.GONE);
				}

			}
		});

		final CheckBox checkEveryday = (CheckBox) findViewById(R.id.checkEveryday);
		final CheckBox[] checks = new CheckBox[7];

		checks[0] = (CheckBox) findViewById(R.id.checkMonday);
		checks[1] = (CheckBox) findViewById(R.id.checkTuesday);
		checks[2] = (CheckBox) findViewById(R.id.checkWednesday);
		checks[3] = (CheckBox) findViewById(R.id.checkThursday);
		checks[4] = (CheckBox) findViewById(R.id.checkFriday);
		checks[5] = (CheckBox) findViewById(R.id.checkSaturday);
		checks[6] = (CheckBox) findViewById(R.id.checkSunday);

		// Sets all weekdays to the same check status as the checkEveryday
		// CheckBox
		checkEveryday.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					for (CheckBox c : checks) {
						c.setChecked(true);
					}
				}
			}
		});

		// If any one of the weekdays is unchecked, all weekdays are no longer
		// checked and the checkEveryday checkbox will be unchecked
		for (CheckBox c : checks) {
			c.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (!isChecked) {
						checkEveryday.setChecked(false);
					}
				}
			});

		}

		Button homeButton = (Button) findViewById(R.id.homeButton);
		Tools.createOnClickLauncher(homeButton, AlarmActivity.this, HomeActivity.class);

		Button overviewButton = (Button) findViewById(R.id.overviewButton);
		Tools.createOnClickLauncher(overviewButton, AlarmActivity.this, OverviewActivity.class);

		Button groupButton = (Button) findViewById(R.id.groupButton);
		Tools.createOnClickLauncher(groupButton, AlarmActivity.this, GroupActivity.class);

		TimePicker timePicker = (TimePicker) findViewById(R.id.alarmtimepicker);

		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			}
		});

		// Handle finish button action
		Button finishButton = (Button) findViewById(R.id.finishButton);

		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Just initialize a test alarm for now
				Toast.makeText(v.getContext(), "Alarm created, should sound in 5 seconds", Toast.LENGTH_SHORT).show();

				IAlarm alarm = AlarmManager.getInstance().createAlarm(getApplicationContext(), TriggerActivity.class);

				Calendar c = Calendar.getInstance();
				c.add(Calendar.SECOND, 5);

				alarm.setTriggerTime(c.getTimeInMillis());

				alarm.enable();
			}

		});

	}
}
