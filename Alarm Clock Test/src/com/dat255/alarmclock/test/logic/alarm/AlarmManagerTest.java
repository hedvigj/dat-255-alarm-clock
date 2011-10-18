package com.dat255.alarmclock.test.logic.alarm;

import android.test.AndroidTestCase;

import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.view.TriggerActivity;

public class AlarmManagerTest extends AndroidTestCase {

	private AlarmManager instance;

	@Override
	public void setUp() {
		instance = AlarmManager.getInstance();
	}

	/**
	 * Test if getInstance() return null and fails if so.
	 */
	public void testGetInstance() {
		if (instance == null) {
			fail("instance = null");
		}
	}

	/**
	 * Created alarm and id should not be null. If so the test fails
	 */

	public void testCreateAlarm() {

		IAlarm alarm = instance.createAlarm(getContext(), TriggerActivity.class);

		if (alarm == null) {
			fail("Alarm is null");
		}
		Long id = alarm.getId();
		if (id == null) {
			fail("Created alarm did not get an Id");
		}
	}

	/**
	 * Tests if an alarm is removed
	 */

	public void testRemoveAlarm() {

		IAlarm alarm = instance.createAlarm(getContext(), TriggerActivity.class);

		instance.removeAlarm(alarm);

		if (instance.findAlarmById(alarm.getId()) != null) {
			fail("Alarm not removed");
		}
	}

	/**
	 * Test if created alarm is return when you search by its Id
	 */

	public void testFindAlarmById() {

		IAlarm a1 = instance.createAlarm(getContext(), TriggerActivity.class);
		IAlarm a2 = instance.createAlarm(getContext(), TriggerActivity.class);

		if (instance.findAlarmById(a1.getId()) != a1) {
			fail("Not same");
		}

		if (instance.findAlarmById(a2.getId()) != a2) {
			fail("Not same");
		}
	}

	/**
	 * Should not generate any errors
	 */

	public void testGetAlarms() {

		// TODO

		instance.getAlarms();

	}
}
