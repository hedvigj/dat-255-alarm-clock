package com.dat255.alarmclock.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.dat255.alarmclock.view.AlarmActivity;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity activity;
	private View view;

	public AlarmActivityTest() {
		super("com.dat255.alarmclock.view", AlarmActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = this.getActivity();
		view = activity.findViewById(com.dat255.alarmclock.R.layout.alarmscreen);
	}

}
