package com.dat255.alarmclock.test.logic.alarm;

import java.util.Calendar;

import android.test.AndroidTestCase;

import com.dat255.alarmclock.logic.alarm.Alarm;
import com.dat255.alarmclock.view.TriggerActivity;

public class AlarmTest extends AndroidTestCase {

	private Alarm alarm;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		alarm = new Alarm(getContext(), TriggerActivity.class, 0);
	}

	public void testAlarm() {

		// Should not cast any exceptions
		@SuppressWarnings("unused")
		Alarm a = new Alarm(getContext(), TriggerActivity.class, 1);
		a = new Alarm(getContext(), TriggerActivity.class, 2);
	}

	public void testEnable() {
		// Tests if alarm is enabled
		// Uses isEnabled() in alarm which return true if enabled and false if
		// disabled
		alarm.enable();
		if (alarm.isEnabled() != true) {
			fail("alarm is not enabled");
		}
	}

	public void testDisable() {
		// Tests if alarm is enabled
		// Uses isEnabled() in alarm which return true if enabled and false if
		// disabled
		alarm.disable();
		if (alarm.isEnabled() != false) {
			fail("alarm is not enabled");
		}
	}

	public void testGetId() {
		// Alarm alarm id is 0, as implemented in setUp.
		// This tests if id and testGetId() is equal
		assertEquals(alarm.getId(), 0);
	}

	public void testSetTriggerTime() {
		// No exceptions should be casted
		alarm.setTriggerTime(1);
		alarm.setTriggerTime(199);
		alarm.setTriggerTime(50);
	}

	public void testGetTriggerTime() {
		// Tests that you get the setTriggerTime
		alarm.setTriggerTime(19);
		assertEquals(alarm.getTriggerTime(), 19);
	}

	public void testSetVisible() {
		// No exceptions should be casted
		alarm.setVisible(true);
		alarm.setVisible(false);
		alarm.setVisible(false);
		alarm.setVisible(true);
	}

	public void testGetVisible() {
		// Test that getVisible() is the same as set visibility
		alarm.setVisible(true);
		if (alarm.getVisible() != true) {
			fail("Visible not same");
		}
	}

	public void testSetProperties() {
		// TODO not implemented yet
	}

	public void testSnooze() {
		// No exceptions should be casted
		alarm.snooze(1);
		alarm.snooze(-2);
		alarm.snooze(65);

		// See that snooze time is the same as triggered time
		int m4 = 23;
		alarm.snooze(m4);
		Calendar current = Calendar.getInstance();
		current.add(Calendar.MINUTE, m4);

		alarm.snooze(m4);
		// Triggered time should be within a secund
		if (Math.abs(alarm.getTriggerTime() - current.getTimeInMillis()) > 1000) {
			fail("TriggerTime not the same as snooze input");
		}
	}

}
