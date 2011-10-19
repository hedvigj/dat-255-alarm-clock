package com.dat255.alarmclock.logic.alarm.properties;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.IAlarm;

public class SoundProperty implements IAlarmProperty {

	private MediaPlayer player;

	@Override
	public void onAlarmSet(IAlarm reference) {
		// None
	}

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

}
