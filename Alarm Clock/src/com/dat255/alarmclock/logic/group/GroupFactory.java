package com.dat255.alarmclock.logic.group;

public class GroupFactory {

	private GroupFactory() {
	}

	/**
	 * Initializes a new group with a specific id
	 * 
	 * @param id
	 *            the desired group id
	 * @return a new IGroup instance
	 */
	public static IGroup get(long id) {
		return new Group(id);
	}
}
