package com.dat255.alarmclock.utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.dat255.alarmclock.R;

public class Sound {

	private static MediaPlayer player = null;

	public static void soundStart(Context context) {
		player = MediaPlayer.create(context, R.raw.test);
		player.start();
	}

	public static void soundStop(Context context) {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
	}
}
