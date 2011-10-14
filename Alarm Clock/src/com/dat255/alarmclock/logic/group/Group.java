package com.dat255.alarmclock.logic.group;

import java.util.ArrayList;
import java.util.List;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public class Group implements IGroup {

	private long id;

	private String name;

	private final List<IAlarm> alarms = new ArrayList<IAlarm>();

	public Group(long id) {
		this.id = id;
	}

	/**
	 * Gets the id used to reference this entity
	 * 
	 * @return the id
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * Gets the name of the group
	 * 
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the group
	 * 
	 * @param groupName
	 *            the new name of the groups
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Enables the group and all of it's alarms
	 */
	@Override
	public void enable() {
		for (IAlarm alarm : alarms) {
			alarm.enable();
		}
	}

	/**
	 * Disables the group and all of it's alarms
	 */
	@Override
	public void disable() {
		for (IAlarm alarm : alarms) {
			alarm.disable();
		}
	}

	/**
	 * Determines whether the group is enabled or not
	 * 
	 * @return true if enabled, otherwise false
	 */
	@Override
	public boolean isEnabled() {
		for (IAlarm alarm : alarms) {
			if (alarm.isEnabled()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets the alarms of this group
	 * 
	 * @return alarms the alarms
	 */
	@Override
	public List<IAlarm> getAlarms() {
		List<IAlarm> output = new ArrayList<IAlarm>();

		for (IAlarm a : alarms) {
			output.add(a);
		}

		return output;
	}

	/**
	 * Adds a new alarm to the group
	 */
	@Override
	public void addAlarmToGroup(IAlarm newAlarm) {
		if (newAlarm != null) {
			if (!alarms.contains(newAlarm)) {
				alarms.add(newAlarm);
			}
		}
	}

	/**
	 * Removes an alarm from the group
	 * 
	 * @param alarm
	 *            the alarm to be removed
	 */
	@Override
	public void removeAlarmFromGroup(IAlarm alarm) {
		if (alarm != null) {
			alarms.remove(alarm);
		}
	}
}