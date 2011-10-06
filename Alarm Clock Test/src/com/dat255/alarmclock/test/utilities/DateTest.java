package com.dat255.alarmclock.test.utilities;

import android.test.AndroidTestCase;

import com.dat255.alarmclock.utilities.Date;

public class DateTest extends AndroidTestCase {
	private Date date;

	@Override
	public void setUp() throws Exception {
		super.setUp();

		date = new Date(getContext(), true);
	}

	public void testDateContextBoolean() {

		// Test that all different types of will complete without errors
		@SuppressWarnings("unused")
		Date test = new Date(getContext(), true);

		test = new Date(getContext(), false);

		// An NullPointerException is to be generated when context is null
		try {
			test = new Date(null, false);
			fail("No NullPointerException was generated");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	public void testDateContext() {
		@SuppressWarnings("unused")
		Date test = new Date(getContext());
		try {
			test = new Date(null);
			fail("No NullPointerException was generated");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	public void testSetHour() {
		// Make sure that no error is generated when setHour function is called
		for (int i = -100; i < 100; i++) {
			date.setHour(i);
		}
	}

	public void testGetHour() {
		// Make sure that you get a valid value
		for (int i = -100; i < 100; i++) {
			date.setHour(i);
			if (i <= 0) {
				assertEquals(date.getHour(), 0);
			} else if (i >= 23) {
				assertEquals(date.getHour(), 23);
			} else {
				assertEquals(date.getHour(), i);
			}
		}
	}

	public void testSetMinute() {
		// Make sure that no error is generated when the function is called
		for (int i = -100; i < 100; i++) {
			date.setMinute(i);
		}
	}

	public void testGetMinute() {
		// Make sure that you get a valid value
		for (int i = -100; i < 100; i++) {
			date.setMinute(i);
			if (i <= 0) {
				assertEquals(date.getMinute(), 0);
			} else if (i >= 59) {
				assertEquals(date.getMinute(), 59);
			} else {
				assertEquals(date.getMinute(), i);
			}
		}
	}

	public void testSetSecond() {
		// Make sure that no error is generated when the function is called
		for (int i = -100; i < 100; i++) {
			date.setSecond(i);
		}
	}

	public void testGetSecond() {
		// Make sure that you get a valid value
		for (int i = -100; i < 100; i++) {
			date.setSecond(i);
			if (i <= 0) {
				assertEquals(date.getSecond(), 0);
			} else if (i >= 59) {
				assertEquals(date.getSecond(), 59);
			} else {
				assertEquals(date.getSecond(), i);
			}
		}
	}

	public void testSetYear() {
		// Make sure that no error is generated when the function is called
		for (int i = -100; i < 100; i++) {
			date.setYear(i);
		}
	}

	public void testGetYear() {
		// Make sure that no error is generated when the function is called
		for (int i = -100; i < 100; i++) {
			date.setYear(i);
			assertEquals(date.getYear(), i);
		}
	}

	public void testSetMonth() {
		// Make sure that no error is generated when the function is called
		for (int i = -100; i < 100; i++) {
			date.setMonth(i);
		}
	}

	public void testGetMonth() {
		// Make sure that you get a valid value
		for (int i = -100; i < 100; i++) {
			date.setMonth(i);
			if (i <= 1) {
				assertEquals(date.getMonth(), 1);
			} else if (i >= 12) {
				assertEquals(date.getMonth(), 12);
			} else {
				assertEquals(date.getMonth(), i);
			}
		}
	}

	public void testGetMonthAsString() {
		// Get the String
		String[] monthName = getContext().getResources().getStringArray(com.dat255.alarmclock.R.array.month_array);

		// Se that correct string is returned
		for (int i = 1; i <= 12; i++) {
			date.setMonth(i);
			assertEquals(monthName[i], date.getMonthAsString());
		}
	}

	public void testSetDayOfMonth() {
		// Make sure that no error is generated when the function is called
		for (int i = -100; i < 100; i++) {
			date.setDayOfMonth(i);
		}
	}

	public void testGetDayOfMonth() {
		// Make sure that you get a valid value
		for (int i = -100; i < 100; i++) {
			date.setDayOfMonth(i);
			if (i <= 1) {
				assertEquals(date.getDayOfMonth(), 1);
			} else if (i >= 31) {
				assertEquals(date.getDayOfMonth(), 31);
			} else {
				assertEquals(date.getDayOfMonth(), i);
			}
		}
	}

	public void testSetDayOfWeek() {
		// Make sure that no error is generated when the function is called

		date.setDayOfWeek(Date.SUNDAY);
		date.setDayOfWeek(Date.MONDAY);
		date.setDayOfWeek(Date.TUESDAY);
		date.setDayOfWeek(Date.WEDNESDAY);
		date.setDayOfWeek(Date.THURSDAY);
		date.setDayOfWeek(Date.FRIDAY);
		date.setDayOfWeek(Date.SATURDAY);

	}

	public void testGetDayOfWeek() {
		// Get the String
		String[] dayName = getContext().getResources().getStringArray(com.dat255.alarmclock.R.array.week_array);

		int[] day = { Date.SUNDAY, Date.MONDAY, Date.TUESDAY, Date.WEDNESDAY, Date.THURSDAY, Date.FRIDAY, Date.SATURDAY };

		// Se that correct string is returned
		for (int i : day) {
			date.setDayOfWeek(i);
			assertEquals(dayName[i], date.getDayOfWeek());
		}
	}

	public void testSetDateToSystemDate() {

		date.setDateToSystemDate();

	}
}
