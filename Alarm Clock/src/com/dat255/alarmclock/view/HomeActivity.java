package com.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.utilities.Tools;

public class HomeActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.homescreen);

		Button overviewButton = (Button) findViewById(R.id.overviewButton);
		Tools.createOnClickLauncher(overviewButton, HomeActivity.this, OverviewActivity.class);

	}

	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
		}

		return true;
	}
}
