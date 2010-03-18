package com.pwnux.android.ToneGenerator;

import java.io.IOException;
import com.pwnux.android.SoundTest.R;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class ToneGenerator {
	private static final String TAG = "ToneGenerator";
	
	public ToneGenerator(Context c) {
		Resources res = c.getResources();
		AssetFileDescriptor afd = res.openRawResourceFd(R.raw.sine_440hz_8khz_16s);
		byte[] byteData = new byte[(int) afd.getLength()];
		
		try {
			afd.createInputStream().read(byteData);
		} catch (IOException e) {
			Log.e(TAG, "Unable to read sound file", e);
			return;
		}
		
		AudioTrack track = createAudioTrack(c);
		
		Log.i(TAG, "Track state: " + track.getState());
		
		/*
		 * NOTES:
		 * 
		 * A "frame" in AudioTrack terminology is a single sample!
		 * Try looping audio...
		 */
		
		track.play();
		track.write(byteData, 0, byteData.length);
		track.stop();
	}
	
	public AudioTrack createAudioTrack(Context c) {
		int bufsize = AudioTrack.getMinBufferSize(8000,
												  AudioFormat.CHANNEL_CONFIGURATION_MONO,
												  AudioFormat.ENCODING_PCM_16BIT);
		Log.i(TAG, "Minimum buffer size: " + bufsize);
		AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC,
										  8000,
										  AudioFormat.CHANNEL_CONFIGURATION_MONO,
										  AudioFormat.ENCODING_PCM_16BIT,
										  4*bufsize,
										  AudioTrack.MODE_STREAM);
		return track;
	}
}