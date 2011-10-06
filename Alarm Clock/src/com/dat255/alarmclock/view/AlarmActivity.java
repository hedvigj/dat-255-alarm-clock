package com.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
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
