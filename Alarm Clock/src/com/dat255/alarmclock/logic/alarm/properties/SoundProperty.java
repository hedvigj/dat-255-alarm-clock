package com.dat255.alarmclock.logic.alarm.properties;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.IAlarm;

public class SoundProperty implements IAlarmProperty {

	private MediaPlayer player;

	/**
	 * Occurs when the parent alarm is set
	 * 
	 * @param triggerTime
	 *            the set trigger time of the alarm
	 */
	@Override
	public void onAlarmSet(IAlarm reference) {
		// None
	}

	/**
	 * Occurs when the parent alarm is triggered
	 * 
	 * @param context
	 *            the context in which the alarm is triggered
	 */
	@Override
	public void onAlarmTriggered(Context context) {
		if (player == null) {
			// Initialize the media player and start playing the sound
			player = new MediaPlayer();

			// Set sound resource, make sure this happens before prepare()
			try {
				player.setDataSource(context, Uri.parse("android.resource://com.dat255.alarmclock/" + R.raw.test));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Set player properties
			player.setAudioStreamType(AudioManager.STREAM_ALARM);

			player.setLooping(true);

			// Start the sound
			try {
				player.prepare();
			} catch (Exception e) {
				e.printStackTrace();
			}

			player.start();
		}
	}

	/**
	 * Occurs when the parent alarm is stopped
	 * 
	 * @param context
	 *            the context in which the alarm is stopped
	 */
	@Override
	public void onAlarmStopped(Context context) {

		if (player != null) {
			// Stop the sound
			player.stop();

			// Release the media player
			player.release();

			// Set the reference to null
			player = null;
		}
	}

	/**
	 * Creates a deep clone if this alarm property
	 * 
	 * @return a new IAlarmProperty instance
	 */
	@Override
	public IAlarmProperty deepClone() {
		return new SoundProperty();
	}

}
