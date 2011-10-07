package com.dat255.alarmclock.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.dat255.alarmclock.view.OverviewActivity;

public class OverviewActivityTest extends ActivityInstrumentationTestCase2<OverviewActivity> {

	private Activity activity;
	private Button overviewButton;
	private Button alarmButton;
	private Button groupButton;

	public OverviewActivityTest() {
		super("com.dat255.alarmclock.view", OverviewActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		groupButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.groupButton);
		alarmButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.alarmButton);
		overviewButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.overviewButton);
	}

	public void testPrecondition() {
		assertNotNull(alarmButton);
		assertNotNull(overviewButton);
		assertNotNull(groupButton);
	}
}