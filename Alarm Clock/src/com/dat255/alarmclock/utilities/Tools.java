package com.dat255.alarmclock.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class Tools {

	/**
	 * Sets the OnClickListener of a Button to launch a new Activity of the
	 * user's selection.
	 * 
	 * @param view
	 *            the Button to set the OnClickListener for
	 * @param context
	 *            the current context
	 * @param nextActivityClass
	 *            the class of the Activity to launch upon click
	 */
	public static void createOnClickLauncher(View view, final Context context, final Class<? extends Activity> nextActivityClass) {

		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, nextActivityClass);
				context.startActivity(i);
			}
		});
	}
}
