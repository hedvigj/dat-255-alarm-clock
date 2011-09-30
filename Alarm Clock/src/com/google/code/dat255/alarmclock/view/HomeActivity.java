package com.google.code.dat255.alarmclock.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.code.dat255.alarmclock.R;

public class HomeActivity extends Activity {
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
				Intent i = new Intent(HomeActivity.this, OverviewActivity.class);
				startActivity(i);
			}
		});
	}
}
