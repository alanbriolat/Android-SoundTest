package com.pwnux.android.ToneGenerator;

import java.io.IOException;

import com.pwnux.android.SoundTest.R;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;

public class ToneGenerator {
	private MediaPlayer mPlayer;
	
	public ToneGenerator(Context c) {
		mPlayer = new MediaPlayer();
		AssetFileDescriptor afd = null;
		try {
			afd = c.getAssets().openFd("tone_440hz_8khz_wav.wav");
		} catch (Exception e) {
			Log.e("OH CRAP", "Failed to get asset", e);
		}
		try {
			mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
			mPlayer.prepare();
			mPlayer.start();
		} catch (Exception e) {
			Log.e("OH CRAP", "Failed to create media player", e);
		}
	}
}