package com.dat255.alarmclock.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.dat255.alarmclock.view.AlarmActivity;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity activity;
	private View alarmTimePicker;
	private CheckBox repeatCheckBox;
	private CheckBox soundCheckBox;
	private CheckBox vibrationCheckBox;
	private Button finishButton;

	public AlarmActivityTest() {
		super("com.dat255.alarmclock.view", AlarmActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = this.getActivity();
		alarmTimePicker = activity.findViewById(com.dat255.alarmclock.R.id.alarmtimepicker);
		repeatCheckBox = (CheckBox) activity.findViewById(com.dat255.alarmclock.R.id.repeatCheckBox);
		soundCheckBox = (CheckBox) activity.findViewById(com.dat255.alarmclock.R.id.soundCheckBox);
		vibrationCheckBox = (CheckBox) activity.findViewById(com.dat255.alarmclock.R.id.vibrationCheckBox);
		finishButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.finishButton);
	}

	public void testPrecondition() {
		assertNotNull(alarmTimePicker);
		assertNotNull(repeatCheckBox);
		assertNotNull(soundCheckBox);
		assertNotNull(vibrationCheckBox);
		assertNotNull(finishButton);
	}
}
