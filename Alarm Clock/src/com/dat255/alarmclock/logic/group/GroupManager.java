package com.dat255.alarmclock.logic.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dat255.alarmclock.utilities.Tools;

public class GroupManager {

	private static GroupManager instance;

	private final Map<Long, IGroup> map = new HashMap<Long, IGroup>();

	private GroupManager() {
	}

	/**
	 * @return the single instance of the group manager
	 */
	public static GroupManager getInstance() {
		if (instance == null) {
			instance = new GroupManager();
		}

		return instance;
	}

	/**
	 * Creates a new group. Always use this method for instantiating new group
	 * objects.
	 * 
	 * @return
	 */
	public IGroup createGroup() {
		IGroup group = GroupFactory.get(Tools.getUniqueMapId(map));

		map.put(group.getId(), group);

		return group;
	}

	/**
	 * Removes a group and frees up the corresponding group id
	 * 
	 * @param group
	 *            the group to be removed
	 */
	public void removeGroup(IGroup group) {
		if (group != null) {
			map.remove(group.getId());
		}
	}

	/**
	 * Gets a specific group by it's id
	 * 
	 * @param id
	 *            the id of the existing group
	 * @return the reference to the desired group
	 */
	public IGroup findGroupById(long id) {
		return map.get(id);
	}

	/**
	 * Gets all existing groups
	 * 
	 * @return a list of all groups
	 */
	public List<IGroup> getGroups() {
		List<IGroup> groups = new ArrayList<IGroup>();

		for (IGroup group : map.values()) {
			groups.add(group);
		}

		return groups;
	}
}
