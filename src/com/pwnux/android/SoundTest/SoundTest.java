package com.pwnux.android.SoundTest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

import com.pwnux.android.ToneGenerator.*;

public class SoundTest extends Activity {
	
	private static final String TAG = "SoundTest";
	
	private AudioTrack mAudioTrack;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(new InputView(this));
    }
    
    
    public class InputView extends View {
    	private static final float TOUCH_TOLERANCE = 4;
    	
    	private Paint 	mPaint;
    	
    	private float mX;
    	private float mY;
    	
    	private ToneGenerator mToneGen;
    	
    	public InputView(Context c) {
    		super(c);

    		mPaint = new Paint();
    		mPaint.setAntiAlias(true);
    		mPaint.setDither(true);
    		mPaint.setColor(0xFFFF0000);
    		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    		mPaint.setStrokeWidth(12);
    		mPaint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL));
    		
    		mX = 0.0f;
    		mY = 0.0f;
    		
    		mToneGen = new ToneGenerator(c);
    	}
    	
    	@Override
    	protected void onDraw(Canvas canvas) {
    		canvas.drawColor(0xFFFFFFFF);
    		canvas.drawCircle(mX, mY, 10, mPaint);
    	}
    	
    	@Override
    	public boolean onTouchEvent(MotionEvent event) {
    		float x = event.getX();
    		float y = event.getY();
    		float d = Math.max(Math.abs(x - mX), Math.abs(y - mY));
    		
    		if (d >= TOUCH_TOLERANCE) {
    			mX = x;
    			mY = y;
    			invalidate();
    		}
    		
    		return true;
    	}
    }
}