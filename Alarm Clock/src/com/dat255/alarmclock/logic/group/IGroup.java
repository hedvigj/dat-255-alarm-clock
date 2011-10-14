package com.dat255.alarmclock.logic.group;

import java.util.List;

import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.common.IEntity;

public interface IGroup extends IEntity {

	/**
	 * Gets the name of the group
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Sets the name of the group
	 * 
	 * @param groupName
	 *            the new name of the groups
	 */
	public void setName(String groupName);

	/**
	 * Enables the group and all of it's alarms
	 */
	public void enable();

	/**
	 * Disables the group and all of it's alarms
	 */
	public void disable();

	/**
	 * Determines whether the group is enabled or not
	 * 
	 * @return true if enabled, otherwise false
	 */
	public boolean isEnabled();

	/**
	 * Gets the alarms of this group
	 * 
	 * @return alarms the alarms
	 */
	public List<IAlarm> getAlarms();

	/**
	 * Adds a new alarm to the group
	 */
	public void addAlarmToGroup(IAlarm newAlarm);

	/**
	 * Removes an alarm from the group
	 * 
	 * @param alarm
	 *            the alarm to be removed
	 */
	public void removeAlarmFromGroup(IAlarm alarm);

}
