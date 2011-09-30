package com.google.code.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.google.code.dat255.alarmclock.R;

public class AlarmClockActivity extends Activity {

	private Button overviewButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);

		// overviewButton = (Button) findViewById(R.id.overviewButton);

		// overviewButton.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(AlarmClockActivity.this,
		// OverviewActivity.class);
		// startActivity(intent);
		//
		// }
		// });
	}
}