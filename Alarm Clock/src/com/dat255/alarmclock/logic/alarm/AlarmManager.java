package com.dat255.alarmclock.logic.alarm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;

/**
 * The alarm manager class handles all existing alarms
 */
public class AlarmManager {

	private static AlarmManager instance;

	private final Map<Long, IAlarm> map = new HashMap<Long, IAlarm>();

	/**
	 * @return the single instance of the alarm manager
	 */
	public static AlarmManager getInstance() {
		if (instance == null) {
			instance = new AlarmManager();
		}

		return instance;
	}

	/**
	 * @return a unique alarm id
	 */
	private long getUniqueId() {
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
	 * Creates a new alarm. Always use this method for instantiating alarm
	 * objects.
	 * 
	 * @param appContext
	 *            the Android application context
	 * @param action
	 *            the activity class to open when the alarm is triggered
	 * @return a reference to the newly created alarm
	 */
	public IAlarm createAlarm(Context appContext, Class<? extends Activity> action) {
		IAlarm alarm = AlarmFactory.get(appContext, action, getUniqueId());

		map.put(alarm.getId(), alarm);

		return alarm;
	}

	/**
	 * Removes a specific alarm and frees up the corresponding alarm id
	 * 
	 * @param alarm
	 */
	public void removeAlarm(IAlarm alarm) {
		if (alarm != null) {
			map.remove(alarm.getId());
		}
	}

	/**
	 * Gets a specific alarm by it's id
	 * 
	 * @param id
	 *            the id of the existing alarm
	 * @return the reference to the desired alarm
	 */
	public IAlarm findAlarmById(long id) {
		return map.get(id);
	}
}
