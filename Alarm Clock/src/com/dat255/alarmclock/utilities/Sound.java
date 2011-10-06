package com.dat255.alarmclock.utilities;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

	private static MediaPlayer p = null;

	public static void soundStart(Context context, int res) {
		p = MediaPlayer.create(context, res);
		p.setLooping(true);
		p.start();
	}

	public static void soundStop(Context context) {
		if (p != null) {
			p.stop();
			p.release();
			p = null;
		}
	}
}
