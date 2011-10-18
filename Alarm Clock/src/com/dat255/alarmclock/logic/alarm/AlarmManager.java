package com.dat255.alarmclock.logic.alarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;

import com.dat255.alarmclock.utilities.Tools;

/**
 * The alarm manager class handles all existing alarms
 */
public class AlarmManager {

	private static AlarmManager instance;

	private final Map<Long, IAlarm> map = new HashMap<Long, IAlarm>();

	private AlarmManager() {
	}

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
	 * Removes all alarms and frees up all occupied alarm ids
	 */
	public void clear() {
		for (IAlarm alarm : map.values()) {
			alarm.disable();
		}

		map.clear();
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
		IAlarm alarm = AlarmFactory.get(appContext, action, Tools.getUniqueMapId(map));

		map.put(alarm.getId(), alarm);

		return alarm;
	}

	/**
	 * Removes a specific alarm and frees up the corresponding alarm id
	 * 
	 * @param alarm
	 *            the alarm to be removed
	 */
	public void removeAlarm(IAlarm alarm) {
		if (alarm != null) {
			alarm.disable();

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

	/**
	 * Gets all alarm instances in this alarm manager
	 * 
	 * @return all alarms
	 */
	public List<IAlarm> getAlarms() {
		List<IAlarm> output = new ArrayList<IAlarm>();

		for (IAlarm alarm : map.values()) {
			output.add(alarm);
		}

		return output;
	}
}
