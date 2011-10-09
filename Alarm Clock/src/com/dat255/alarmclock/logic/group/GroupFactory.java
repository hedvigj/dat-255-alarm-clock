package com.dat255.alarmclock.logic.group;

public class GroupFactory {

	public static IGroup get(long id) {
		return new Group(id);
	}
}
