package com.dat255.alarmclock.logic.group;

import java.util.List;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public interface IGroup {

	/**
	 * Get the name of the group
	 * 
	 * @return group name
	 */
	public String getName();

	/**
	 * Set the name of the group
	 * 
	 * @param groupName
	 *            is the new name of the groups
	 */
	public void setName(String groupName);

	/**
	 * Get the alarms that the group contains
	 * 
	 * @return alarms
	 */
	public List<IAlarm> getAlarms();

	/**
	 * Add a new alarm to the group
	 */
	public void addAlarm(IAlarm newAlarm);

	/**
	 * Remove alarm from group
	 * 
	 * @param alarm
	 *            is the alarm intended to be removed
	 */
	public void removeAlarm(IAlarm alarm);

}
