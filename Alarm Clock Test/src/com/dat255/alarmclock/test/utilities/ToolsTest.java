package com.dat255.alarmclock.test.utilities;

import java.util.HashMap;
import java.util.Map;

import android.test.AndroidTestCase;
import android.view.View;

import com.dat255.alarmclock.logic.alarm.Alarm;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.utilities.Tools;
import com.dat255.alarmclock.view.AlarmActivity;
import com.dat255.alarmclock.view.GroupActivity;
import com.dat255.alarmclock.view.HomeActivity;
import com.dat255.alarmclock.view.OverviewActivity;
import com.dat255.alarmclock.view.TriggerActivity;

public class ToolsTest extends AndroidTestCase {
	@Override
	public void setUp() throws Exception {
		super.setUp();

	}

	public void testGetUniqueMapId() {

		Map<Long, IAlarm> map = new HashMap<Long, IAlarm>();
		long[] keys = new long[100];

		for (int i = 0; i < 100; i++) {
			long id = Tools.getUniqueMapId(map);

			// Test that the new ID is not one that existed before
			for (int j = 0; j <= i; j++) {
				if (i == 0 && j == 0) {
					assertTrue(id == 0);
				} else {
					assertTrue(keys[j] != id);
				}
			}

			keys[i] = id;
			map.put(keys[i], new Alarm(getContext(), AlarmActivity.class, i));
		}

	}

	public void testCreateOnClickLauncher() {
		View testView = new View(getContext());

		assertTrue(Tools.createOnClickLauncher(testView, getContext(), AlarmActivity.class));
		assertTrue(Tools.createOnClickLauncher(testView, getContext(), GroupActivity.class));
		assertTrue(Tools.createOnClickLauncher(testView, getContext(), TriggerActivity.class));
		assertTrue(Tools.createOnClickLauncher(testView, getContext(), HomeActivity.class));
		assertTrue(Tools.createOnClickLauncher(testView, getContext(), OverviewActivity.class));

	}
}
