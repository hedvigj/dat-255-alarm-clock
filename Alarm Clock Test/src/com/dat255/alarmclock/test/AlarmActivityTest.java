package com.dat255.alarmclock.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

import com.dat255.alarmclock.view.AlarmActivity;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity activity;
	private View alarmTimePicker;
	private Button overviewButton;
	private Button alarmButton;
	private Button groupButton;

	public AlarmActivityTest() {
		super("com.dat255.alarmclock.view", AlarmActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = this.getActivity();
		alarmTimePicker = activity.findViewById(com.dat255.alarmclock.R.id.alarmtimepicker);
		groupButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.groupButton);
		alarmButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.alarmButton);
		overviewButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.overviewButton);
	}

	public void testPrecondition() {
		assertNotNull(alarmTimePicker);
		assertNotNull(alarmButton);
		assertNotNull(overviewButton);
		assertNotNull(groupButton);
	}

}
