package com.dat255.alarmclock.logic.notification;

public interface INotification {
	/**
	 * Gets the time when the notification will be triggered
	 * 
	 * @return the time in milliseconds
	 */
	public long getTriggerTime();
}
