package com.google.code.dat255.alarmclock.logic.alarm.properties;

public class TextAlarmProperty implements IAlarmProperty {

	private final String text;

	public TextAlarmProperty(String text) {
		this.text = text;
	}

	public String GetAlarmText() {
		return this.text;
	}

	@Override
	public void onAlarmSet(long triggerTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAlarmTriggered() {
		// TODO Auto-generated method stub
	}

}
