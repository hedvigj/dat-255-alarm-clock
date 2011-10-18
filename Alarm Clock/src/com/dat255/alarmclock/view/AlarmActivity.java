package com.dat255.alarmclock.view;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
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
import com.dat255.alarmclock.logic.group.GroupManager;

public class AlarmActivity extends Activity implements OnClickListener {

	private boolean editMode;
	private long alarmId;
	private long groupId;

	private TimePicker timePicker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.alarmscreen);

		Button finish = (Button) findViewById(R.id.finishButton);
		finish.setOnClickListener(this);

		// Handle the time picker
		timePicker = (TimePicker) findViewById(R.id.alarmtimepicker);

		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			}
		});

		// Handle repeat
		final CheckBox repeat = (CheckBox) findViewById(R.id.repeatCheckBox);

		final LinearLayout linearWeekdays = (LinearLayout) findViewById(R.id.weekdays);

		linearWeekdays.setVisibility(View.GONE);

		// Make the weekdays visible if the checkRepeat checkbox is checked,
		// invisible otherwise
		repeat.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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

		final CheckBox[] repeatWeekdays = new CheckBox[7];
		repeatWeekdays[0] = (CheckBox) findViewById(R.id.checkMonday);
		repeatWeekdays[1] = (CheckBox) findViewById(R.id.checkTuesday);
		repeatWeekdays[2] = (CheckBox) findViewById(R.id.checkWednesday);
		repeatWeekdays[3] = (CheckBox) findViewById(R.id.checkThursday);
		repeatWeekdays[4] = (CheckBox) findViewById(R.id.checkFriday);
		repeatWeekdays[5] = (CheckBox) findViewById(R.id.checkSaturday);
		repeatWeekdays[6] = (CheckBox) findViewById(R.id.checkSunday);

		// Sets all weekdays to the same check status as the checkEveryday
		// CheckBox
		checkEveryday.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					for (CheckBox c : repeatWeekdays) {
						c.setChecked(true);
					}
				}
			}
		});

		// If any one of the weekdays is unchecked, all weekdays are no longer
		// checked and the checkEveryday checkbox will be unchecked
		for (CheckBox c : repeatWeekdays) {
			c.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (!isChecked) {
						checkEveryday.setChecked(false);
					}
				}
			});

		}
	}

	@Override
	public void onStart() {
		super.onStart();

		// Get intent data
		editMode = getIntent().getBooleanExtra("alarmedit", false);

		alarmId = getIntent().getLongExtra("alarmid", 0);

		groupId = getIntent().getLongExtra("groupid", 0);

		// If in edit mode, adjust the views to match the alarm
		updateViews();
	}

	private void setAlarm() {
		IAlarm alarm;

		if (!editMode) {
			// Create a new alarm instance
			alarm = AlarmManager.getInstance().createAlarm(getApplicationContext(), TriggerActivity.class);

			GroupManager.getInstance().findGroupById(groupId).addAlarmToGroup(alarm);
		} else {
			// Get the alarm instance
			alarm = AlarmManager.getInstance().findAlarmById(alarmId);
		}

		// Set trigger time
		Calendar time = Calendar.getInstance();

		long currentMilliseconds = time.getTimeInMillis();

		time.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
		time.set(Calendar.MINUTE, timePicker.getCurrentMinute());
		time.set(Calendar.SECOND, 0);

		if (time.getTimeInMillis() < currentMilliseconds) {
			time.add(Calendar.HOUR_OF_DAY, 24);
		}

		alarm.setTriggerTime(time.getTimeInMillis());

		// Enable the alarm
		alarm.enable();

		int hoursUntilTriggered = (int) ((time.getTimeInMillis() - currentMilliseconds) / (1000 * 3600));

		Toast.makeText(
				this,
				"Alarm set to sound in " + hoursUntilTriggered + " hours and "
						+ (((time.getTimeInMillis() - currentMilliseconds) / (1000 * 60)) - hoursUntilTriggered * 60) + " minutes.",
				Toast.LENGTH_SHORT).show();
	}

	private void updateViews() {
		if (editMode) {
			IAlarm alarm = AlarmManager.getInstance().findAlarmById(alarmId);

			Calendar alarmTime = Calendar.getInstance();
			alarmTime.setTimeInMillis(alarm.getTriggerTime());

			timePicker.setCurrentHour(alarmTime.get(Calendar.HOUR_OF_DAY));
			timePicker.setCurrentMinute(alarmTime.get(Calendar.MINUTE));
		}
	}

	@Override
	public void onClick(View v) {
		setAlarm();
		Intent i = new Intent(this, GroupActivity.class);
		startActivity(i);
	}
}
