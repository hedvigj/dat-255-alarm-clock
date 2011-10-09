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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<IAlarm> getAlarms() {
		List<IAlarm> alarmsClone = new ArrayList<IAlarm>();

		for (IAlarm a : alarms) {
			alarmsClone.add(a);
		}

		return alarmsClone;
	}

	@Override
	public void addAlarm(IAlarm newAlarm) {
		if (newAlarm != null) {
			if (!alarms.contains(newAlarm)) {
				alarms.add(newAlarm);
			}
		}
	}

	@Override
	public void removeAlarm(IAlarm alarm) {
		if (alarm != null) {
			alarms.remove(alarm);
		}
	}

}
