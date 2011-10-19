package com.dat255.alarmclock.view;

import java.util.ArrayList;
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
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.alarm.properties.IAlarmProperty;
import com.dat255.alarmclock.logic.alarm.properties.RepeatProperty;
import com.dat255.alarmclock.logic.alarm.properties.SoundProperty;
import com.dat255.alarmclock.logic.alarm.properties.VibrationProperty;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.utilities.Tools;

public class AlarmActivity extends Activity implements OnClickListener {

	private boolean editMode;
	private long alarmId;
	private long groupId;

	private TimePicker timePicker;
	private RadioButton[] repeatWeekdays;

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

		repeatWeekdays = new RadioButton[7];

		repeatWeekdays[0] = (RadioButton) findViewById(R.id.radioSunday);
		repeatWeekdays[1] = (RadioButton) findViewById(R.id.radioMonday);
		repeatWeekdays[2] = (RadioButton) findViewById(R.id.radioTuesday);
		repeatWeekdays[3] = (RadioButton) findViewById(R.id.radioWednesday);
		repeatWeekdays[4] = (RadioButton) findViewById(R.id.radioThursday);
		repeatWeekdays[5] = (RadioButton) findViewById(R.id.radioFriday);
		repeatWeekdays[6] = (RadioButton) findViewById(R.id.radioSaturday);

	}

	@Override
	public void onStart() {
		super.onStart();

		// Get intent data
		editMode = getIntent().getBooleanExtra("alarmedit", false);

		alarmId = getIntent().getLongExtra("alarmid", 0);

		groupId = getIntent().getLongExtra("groupid", 0);

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

		time.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
		time.set(Calendar.MINUTE, timePicker.getCurrentMinute());
		time.set(Calendar.SECOND, 0);

		alarm.setTriggerTime(time.getTimeInMillis());

		// Set alarm properties
		ArrayList<IAlarmProperty> properties = new ArrayList<IAlarmProperty>();

		// Set the repeat setting
		// Do something like this: properties.add(new
		// RepeatProperty(Calendar.WEDNESDAY));

		// Set the vibration and sound properties according to their respective
		// checkboxes
		CheckBox soundCheck = (CheckBox) findViewById(R.id.soundCheckBox);
		CheckBox vibrationCheck = (CheckBox) findViewById(R.id.vibrationCheckBox);

		if (soundCheck.isChecked()) {
			properties.add(new SoundProperty());
		}
		if (vibrationCheck.isChecked()) {
			properties.add(new VibrationProperty());
		}

		final int[] weekdays = { Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY,
				Calendar.FRIDAY, Calendar.SATURDAY };
		for (int i = 0; i < 7; i++) {
			if (repeatWeekdays[i].isChecked()) {
				properties.add(new RepeatProperty(weekdays[i]));
			}

		}

		IAlarmProperty[] array = new IAlarmProperty[properties.size()];
		properties.toArray(array);
		alarm.setProperties(array);

		// Enable the alarm
		alarm.enable();

		// Notify the user on when the alarm will sound
		Tools.showAlarmCountdownToast(this, alarm);
	}

	@Override
	public void onClick(View v) {
		setAlarm();

		// Close the activity for now
		finish();
	}
}
