package com.dat255.alarmclock.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.dat255.alarmclock.view.TriggerActivity;

public class TriggerActivityTest extends ActivityInstrumentationTestCase2<TriggerActivity> {

	private Activity activity;
	private Button ignoreButton;
	private Button snoozeButton;

	public TriggerActivityTest() {
		super("com.dat255.alarmclock.view", TriggerActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		ignoreButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.ignoreButton);
		snoozeButton = (Button) activity.findViewById(com.dat255.alarmclock.R.id.snoozeButton);
	}

	public void testPrecondition() {
		assertNotNull(snoozeButton);
		assertNotNull(ignoreButton);
	}

}
