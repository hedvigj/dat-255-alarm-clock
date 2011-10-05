package com.dat255.alarmclock.utilities;

import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;

import com.dat255.alarmclock.R;

/**
 * 
 * Date is a container for date specific values such as time
 * 
 * @author Sickan
 * 
 */
public class Date {
	private int hour;
	private int minute;
	private int second;

	private int year;
	private int month;
	private int dayOfMonth;
	private int dayOfWeek;

	public static final int SUNDAY = 0;
	public static final int MONDAY = 1;
	public static final int TUESDAY = 2;
	public static final int WEDNESDAY = 3;
	public static final int THURSDAY = 4;
	public static final int FRIDAY = 5;
	public static final int SATURDAY = 6;

	public final String[] MONTH_TO_STRING;
	public final String[] WEEKDAY_STRING;

	/**
	 * Set the date object with the date from an Calendar object
	 * 
	 * @param setCurrentTime
	 *            is if set the time of the date to current time
	 * @param context
	 *            is context of the application
	 * 
	 */
	public Date(Context context, boolean setCurrentTime) {
		this(context);
		if (setCurrentTime) {
			setDateToSystemDate();
		}
	}

	/**
	 * An empty date object
	 */
	public Date(Context context) {
		if (context == null) {
			throw new NullPointerException("Context was empty");
		}

		Resources res = context.getResources();
		this.MONTH_TO_STRING = res.getStringArray(R.array.month_array);
		this.WEEKDAY_STRING = res.getStringArray(R.array.week_array);
	}

	public void setDateToSystemDate() {
		Calendar c = Calendar.getInstance();
		setHour(c.get(Calendar.HOUR_OF_DAY));
		setMinute(c.get(Calendar.MINUTE));
		setSecond(c.get(Calendar.SECOND));

		setYear(c.get(Calendar.YEAR));
		setMonth(c.get(Calendar.MONTH) + 1);
		setDayOfMonth(c.get(Calendar.DAY_OF_MONTH));

		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			setDayOfWeek(Date.SUNDAY);
			break;
		case Calendar.MONDAY:
			setDayOfWeek(Date.MONDAY);
			break;
		case Calendar.TUESDAY:
			setDayOfWeek(Date.TUESDAY);
			break;
		case Calendar.WEDNESDAY:
			setDayOfWeek(Date.WEDNESDAY);
			break;
		case Calendar.THURSDAY:
			setDayOfWeek(Date.THURSDAY);
			break;
		case Calendar.FRIDAY:
			setDayOfWeek(Date.FRIDAY);
			break;
		case Calendar.SATURDAY:
			setDayOfWeek(Date.SATURDAY);
			break;
		}
	}

	/**
	 * Get the set hour in the interval 0-23
	 * 
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Set the hour in the interval 0-23
	 * if > 23 auto change to 23
	 * if < 0 auto change to 0
	 * 
	 * @param hour
	 */
	public void setHour(int hour) {
		if (hour < 0) {
			this.hour = 0;
			return;
		} else if (hour > 23) {
			this.hour = 23;
		} else {
			this.hour = hour;
		}
	}

	/**
	 * Get the set minute in the interval 0-59
	 * 
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Set the minute in the interval 0-59
	 * if > 59 auto change to 59
	 * if < 0 auto change to 0
	 * 
	 * @param minute
	 */
	public void setMinute(int minute) {
		if (minute < 0) {
			this.minute = 0;
			return;
		} else if (minute > 59) {
			this.minute = 59;
		} else {
			this.minute = minute;
		}
	}

	/**
	 * Get the set second in the interval 00-59
	 * 
	 * @return second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Set the second in the interval 0-59
	 * if > 59 auto change to 59
	 * if < 0 auto change to 0
	 * 
	 * @param second
	 */
	public void setSecond(int second) {
		if (second < 0) {
			this.second = 0;
			return;
		} else if (second > 59) {
			this.second = 59;
		} else {
			this.second = second;
		}
	}

	/**
	 * Get the set year
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year
	 * 
	 * @param year
	 */
	public void setYear(int year) {

		this.year = year;
	}

	/**
	 * Get the set month
	 * 
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Get the set month as a string
	 * 
	 * @return month
	 */
	public String getMonthAsString() {
		return MONTH_TO_STRING[getMonth()];

	}

	/**
	 * Set the month in the interval 1-12
	 * if > 12 auto change to 12
	 * if < 1 auto change to 1
	 * 
	 * @param month
	 */
	public void setMonth(int month) {
		if (month < 1) {
			this.month = 1;
		} else if (month > 12) {
			this.month = 12;
		} else {
			this.month = month;
		}
	}

	/**
	 * Get the set day of month
	 * 
	 * @return day of month
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	/**
	 * Set the day of month in the interval 1-31
	 * if > 31 auto change to 31
	 * if < 1 auto change to 1
	 * 
	 * @param dayOfMonth
	 */
	public void setDayOfMonth(int dayOfMonth) {
		if (dayOfMonth < 1) {
			this.dayOfMonth = 1;
			return;
		} else if (dayOfMonth > 31) {
			this.dayOfMonth = 31;
		} else {
			this.dayOfMonth = dayOfMonth;
		}
	}

	/**
	 * Get the set day of week
	 * 
	 * @return day of week
	 */
	public String getDayOfWeek() {
		return WEEKDAY_STRING[dayOfWeek];
	}

	/**
	 * Set the day of week. See Date.SUNDAY to Date.SATURDAY
	 * 
	 * @param dayOfWee
	 */
	public void setDayOfWeek(int dayOfWeek) {
		if (dayOfWeek < 0) {
			this.dayOfWeek = 0;
			return;
		} else if (dayOfWeek > 6) {
			this.dayOfWeek = 6;
		} else {
			this.dayOfWeek = dayOfWeek;
		}
	}

}
