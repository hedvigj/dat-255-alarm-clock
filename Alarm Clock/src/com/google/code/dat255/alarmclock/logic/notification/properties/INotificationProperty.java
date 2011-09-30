package com.google.code.dat255.alarmclock.logic.notification.properties;

public interface INotificationProperty {
	/**
	 * Occurs when the parent notification is set
	 * 
	 * @param triggerTime
	 *            the set trigger time of the notification
	 */
	public void onNotificationSet(long triggerTime);

	/**
	 * Occurs when the parent notification is triggered
	 */
	public void onNotificationTriggered();
}
