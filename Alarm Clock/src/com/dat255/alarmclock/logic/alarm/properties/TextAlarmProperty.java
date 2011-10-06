package com.dat255.alarmclock.logic.alarm.properties;

import com.dat255.alarmclock.logic.alarm.IAlarm;

public class TextAlarmProperty implements IAlarmProperty {

	private String text;

	public TextAlarmProperty(String text) {
		this.text = text;
	}

	@Override
	public void onAlarmSet(IAlarm reference) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAlarmTriggered() {
		// TODO Auto-generated method stub
	}

}
