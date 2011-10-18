package com.dat255.alarmclock.test.logic.group;

import java.util.List;

import android.test.AndroidTestCase;

import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;

public class GroupManagerTest extends AndroidTestCase {
	private GroupManager gm;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		gm = GroupManager.getInstance();
	}

	public void testGetInstance() {
		// Test that you can getInstance without getting any error
		GroupManager gm;
		for (int i = 0; i < 100; i++) {
			gm = GroupManager.getInstance();
			assertEquals(gm, this.gm);
			this.gm = gm;
		}
	}

	public void testCreateGroup() {
		// test that you can create groups without geting any errors
		for (int i = 0; i < 100; i++) {
			gm.createGroup();
		}
	}

	public void testGetGroups() {
		// Test that you can use getGroups function without getting any errors
		for (int i = 0; i < 100; i++) {
			@SuppressWarnings("unused")
			List<IGroup> g = gm.getGroups();
		}
	}

	public void testRemoveGroup() {
		// Get created groups
		List<IGroup> gl = gm.getGroups();

		// Make sure that there are more then 100 groups within the GroupManager
		if (gl.size() < 100) {
			for (int i = 0; i < 100; i++) {
				gm.createGroup();
			}
			gl = gm.getGroups();
		}
		// Save a group object for later use in an attempt to remove a group not
		// in the GroupManager
		IGroup g = gl.get(0);
		// Remove all groups in GroupManager
		for (int i = 0; i < gl.size(); i++) {
			gm.removeGroup(gl.get(i));
		}

		// Try remove a null object and se that no error are reported
		gm.removeGroup(null);
		// Try remove a group not in the GroupManager, nothing should happend
		gm.removeGroup(g);

	}

	public void testRemoveGroupAndContent() {
		List<IGroup> gl = gm.getGroups();

		// Make sure that there are more then 100 groups in GroupManager
		if (gl.size() < 100) {
			for (int i = 0; i < 100; i++) {
				gm.createGroup();
			}
			gl = gm.getGroups();
		}
		// Save for later use
		IGroup g = gl.get(0);

		// Remove all groups from GroupManager
		for (int i = 0; i < gl.size(); i++) {
			gm.removeGroupAndContent(gl.get(i));
		}

		// Removeing null or a group not in GroupManager should not generate
		// errors
		gm.removeGroupAndContent(null);
		gm.removeGroupAndContent(g);
	}

	public void testFindGroupById() {
		List<IGroup> gl = gm.getGroups();

		// Make sure that there are more then 100 items in GroupManager
		if (gl.size() < 100) {
			for (int i = 0; i < 100; i++) {
				gm.createGroup();
			}
			gl = gm.getGroups();
		}
		// Make sure all items in GroupManager can be reached through
		// findGroupById
		for (int i = 0; i < gl.size(); i++) {
			IGroup fg = gm.findGroupById(gl.get(i).getId());
			assertEquals(fg, gl.get(i));
		}
		// Remove all alarms
		for (int i = 0; i < gl.size(); i++) {
			gm.removeGroup(gl.get(i));
		}
		// Create one alarm
		gm.createGroup();
		gl = gm.getGroups();
		assertEquals(gl.size(), 1);
		// Test that you can't find a id that is not in the list
		long id = gl.get(0).getId();
		id += 1;

		// Test the the fake id dont return any group
		IGroup g = gm.findGroupById(id);
		assertEquals(null, g);

	}
}
