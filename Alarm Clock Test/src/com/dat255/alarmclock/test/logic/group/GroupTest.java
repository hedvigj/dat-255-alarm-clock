package com.dat255.alarmclock.test.logic.group;

import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;

import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.group.Group;

public class GroupTest extends AndroidTestCase {
	private Group group;

	/**
	 * Private class with no functionality to use in the group
	 */
	private class TestAlarm implements IAlarm {
		@Override
		public long getTriggerTime() {
			return 0;
		}

		@Override
		public long getId() {
			return 0;
		}

		@Override
		public void setTriggerTime(long triggerTime) {
		}

		@Override
		public void enable() {
		}

		@Override
		public void disable() {
		}

		@Override
		public boolean isEnabled() {
			return false;
		}

		@Override
		public void onAlarmTriggered(Context context) {
		}

		@Override
		public boolean getVisible() {
			return false;
		}

		@Override
		public void setVisible(boolean visible) {
		}

		@Override
		public void snooze(int minutes) {
		}

	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		group = new Group(1);
	}

	public void testGroup() {
		// Test that the constructor dont generate any Exceptions
		@SuppressWarnings("unused")
		Group g = new Group(2);
		g = new Group(3);
		g = new Group(4);
	}

	public void testSetName() {
		// No Exceptions should be generated from this function
		group.setName("test");
		group.setName("");
		group.setName("едц?=/!\"");

	}

	public void testGetName() {
		// Test that what is returned is what you expect it to be
		String s = "Hello";
		group.setName(s);
		assertEquals(group.getName(), s);

		s = "едц?=/!\"";
		group.setName(s);
		assertEquals(group.getName(), s);

		s = "";
		group.setName(s);
		assertEquals(group.getName(), s);

		for (int i = 0; i < 100; i++) {
			s = "test";
			group.setName(s);
			assertEquals(group.getName(), s);
		}
	}

	public void testAddAlarm() {
		// Adding an alarm should not generate any error at any time
		TestAlarm newAlarm = new TestAlarm();
		group.addAlarm(newAlarm);
		group.addAlarm(newAlarm);

		for (int i = 0; i < 100; i++) {
			newAlarm = new TestAlarm();
			group.addAlarm(newAlarm);
		}
	}

	public void testGetAlarms() {
		group = new Group(5);
		TestAlarm newAlarm = new TestAlarm();

		List<IAlarm> list = group.getAlarms();
		assertEquals(list.size(), 0);

		group.addAlarm(newAlarm);
		list = group.getAlarms();
		assertEquals(list.size(), 1);

		group.addAlarm(newAlarm);
		list = group.getAlarms();
		assertEquals(list.size(), 1);

		for (int i = 0; i < 100; i++) {
			newAlarm = new TestAlarm();
			group.addAlarm(newAlarm);
			list = group.getAlarms();
			assertEquals(list.size(), i + 2);
		}

	}

	public void testRemoveAlarm() {
		group = new Group(6);
		TestAlarm newAlarm = new TestAlarm();
		List<IAlarm> list = group.getAlarms();

		// init test
		assertEquals(group.getAlarms().size(), 0);

		// First remove should not do anything
		group.removeAlarm(newAlarm);
		assertEquals(group.getAlarms().size(), 0);

		// Add alarm
		group.addAlarm(newAlarm);
		assertEquals(group.getAlarms().size(), 1);

		// Remove it again
		group.removeAlarm(newAlarm);
		assertEquals(group.getAlarms().size(), 0);

		// Add alarm
		group.addAlarm(newAlarm);
		assertEquals(group.getAlarms().size(), 1);

		// Add another alarm
		newAlarm = new TestAlarm();
		group.addAlarm(newAlarm);
		assertEquals(group.getAlarms().size(), 2);

		// Remove one of the alarm
		group.removeAlarm(newAlarm);
		assertEquals(group.getAlarms().size(), 1);

		// Remove the last IAlarm item
		list = group.getAlarms();
		group.removeAlarm(list.get(0));
		assertEquals(group.getAlarms().size(), 0);

		// Add an alarm to be remove after a while
		TestAlarm test = new TestAlarm();
		group.addAlarm(test);
		assertEquals(group.getAlarms().size(), 1);

		for (int i = 1; i < 100; i++) {
			// Add a new alarm
			newAlarm = new TestAlarm();
			group.addAlarm(newAlarm);
			assertEquals(group.getAlarms().size(), i + 1);

			// Add another alarm
			newAlarm = new TestAlarm();
			group.addAlarm(newAlarm);
			assertEquals(group.getAlarms().size(), i + 2);

			// Remove the last alarm
			group.removeAlarm(newAlarm);
			assertEquals(group.getAlarms().size(), i + 1);

		}

		int size = group.getAlarms().size();
		group.removeAlarm(test);
		assertEquals(group.getAlarms().size(), size - 1);

		list = group.getAlarms();
		// Empty the list
		for (int i = 0; i < list.size(); i++) {
			group.removeAlarm(list.get(i));
			assertEquals(group.getAlarms().size(), list.size() - (i + 1));
		}

	}

}
