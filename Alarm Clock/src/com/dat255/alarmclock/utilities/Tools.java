package com.dat255.alarmclock.utilities;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.dat255.alarmclock.logic.common.IEntity;

public class Tools {

	/**
	 * Gets a unique available id from a map of entities. Tries to reuse old ids
	 * as long as they are not occupied.
	 * 
	 * @param map
	 *            the map
	 * @return a unique id
	 */
	public static long getUniqueMapId(Map<Long, ? extends IEntity> map) {
		// Bad performance here!

		// Return an id that does not currently exist in the map
		Collection<Long> keys = map.keySet();

		if (!keys.isEmpty()) {
			Long max = Collections.max(keys);

			for (long i = 0; i <= max; i++) {
				if (!keys.contains(i)) {
					return i;
				}
			}

			return max + 1;
		}

		return 0;
	}

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
	 * 
	 * @return the success of the operation, true if nothing unexpected occurs
	 */
	public static boolean createOnClickLauncher(View view, final Context context, final Class<? extends Activity> nextActivityClass) {

		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, nextActivityClass);
				context.startActivity(i);
			}
		});

		return true;
	}
}
