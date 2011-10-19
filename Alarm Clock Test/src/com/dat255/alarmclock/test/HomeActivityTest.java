package com.dat255.alarmclock.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

import com.dat255.alarmclock.view.HomeActivity;

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {

	private Activity activity;
	private Button overviewButton;
	private View currentTime;

	public HomeActivityTest() {
		super("com.dat255.alarmclock.view", HomeActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		overviewButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.overviewButton);
		currentTime = activity.findViewById(com.dat255.alarmclock.R.id.current_time);
	}

	public void testPrecondition() {
		assertNotNull(overviewButton);
		assertNotNull(currentTime);
	}

}
