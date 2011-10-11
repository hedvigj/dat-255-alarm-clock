package com.dat255.alarmclock.utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.dat255.alarmclock.R;

public class Sound {

	private static MediaPlayer player = null;
	private static boolean soundOn = false;

	public static void soundStart(Context context) {
		soundStop(context);
		player = MediaPlayer.create(context, R.raw.test);
		player.start();
		soundOn = true;
	}

	public static void soundLoopStart(Context context) {
		soundStop(context);
		player = MediaPlayer.create(context, R.raw.test);
		player.setLooping(true);
		player.start();
		soundOn = true;
	}

	public static void soundStop(Context context) {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
		soundOn = false;
	}

	public static boolean isSoundOn() {
		return soundOn;
	}
}
