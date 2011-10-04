package com.dat255.alarmclock.logic.group;

import java.util.ArrayList;
import java.util.List;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public class Group implements IGroup {
	private String groupName;
	private final List<IAlarm> alarms;

	public Group(String groupName) {
		setName(groupName);
		alarms = new ArrayList<IAlarm>();
	}

	@Override
	public String getName() {
		return groupName;
	}

	@Override
	public void setName(String groupName) {
		this.groupName = groupName;

	}

	@Override
	public List<IAlarm> getAlarms() {
		List<IAlarm> alarmsClone = new ArrayList<IAlarm>();
		for (IAlarm a : alarms) {
			alarms.add(a);
		}
		return alarmsClone;
	}

	@Override
	public void addAlarm(IAlarm newAlarm) {
		if (newAlarm != null) {
			alarms.add(newAlarm);
		}
	}

	@Override
	public void removeAlarm(IAlarm alarm) {
		if (alarm != null) {
			if (alarms.size() > 0) {
				alarms.remove(alarm);
			}
		}
	}

}
