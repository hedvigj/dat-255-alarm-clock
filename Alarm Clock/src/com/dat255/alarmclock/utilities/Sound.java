package com.dat255.alarmclock.utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.dat255.alarmclock.R;

public class Sound {

	private static MediaPlayer player = null;

	/**
	 * Start the specific sound of this class
	 * 
	 * @param context
	 *            is the context in which sound will connected to
	 */
	public static void soundStart(Context context) {
		soundStop();
		player = MediaPlayer.create(context, R.raw.test);
		player.setLooping(false);
		player.start();
	}

	/**
	 * Start the specific sound of this class and loop it until it is turned of
	 * 
	 * @param context
	 *            is the context in which sound will connected to
	 */
	public static void soundLoopStart(Context context) {
		soundStop();
		player = MediaPlayer.create(context, R.raw.test);
		player.setLooping(true);
		player.start();
	}

	/**
	 * Stop current sound origeneting from this class
	 */
	public static void soundStop() {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
	}

	/**
	 * Is sound playing from this class
	 * 
	 * @return true if there is a sound playing, else false
	 */
	public static boolean isSoundOn() {
		if (player == null) {
			return false;
		}
		return player.isPlaying();
	}
}
