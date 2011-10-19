package com.dat255.alarmclock.utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.dat255.alarmclock.R;

public class Sound {

	private MediaPlayer player = null;
	private static Sound instance = null;

	public static Sound getInstance() {
		if (instance == null) {
			instance = new Sound();
		}
		return instance;
	}

	/**
	 * Start the specific sound of this class
	 * 
	 * @param context
	 *            is the context in which sound will connected to
	 */
	public void soundStart(Context context) {
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
	public void soundLoopStart(Context context) {
		soundStop();
		player = MediaPlayer.create(context, R.raw.test);
		player.setLooping(true);
		player.start();
	}

	/**
	 * Stop current sound originating from this class
	 */
	public void soundStop() {
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
	public boolean isSoundOn() {
		if (player == null) {
			return false;
		}
		return player.isPlaying();
	}
}
