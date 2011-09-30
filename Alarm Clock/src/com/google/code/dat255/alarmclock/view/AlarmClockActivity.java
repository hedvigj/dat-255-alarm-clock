package com.google.code.dat255.alarmclock.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.code.dat255.alarmclock.R;

public class AlarmClockActivity extends Activity {

	private Button overviewButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);

		overviewButton = (Button) findViewById(R.id.overviewButton);

		overviewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent i = new Intent(AlarmClockActivity.this,
				// OverviewActivity.class);
				// startActivity(i);
				Toast.makeText(v.getContext(), "hej", Toast.LENGTH_LONG).show();
			}
		});

		/*
		 * overviewButton.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // Intent intent = new
		 * Intent(AlarmClockActivity.this, // OverviewActivity.class); //
		 * startActivity(intent);
		 * 
		 * Toast.makeText(this, "Hej", Toast.LENGTH_LONG); } });
		 */
	}
}