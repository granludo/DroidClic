package pfc.Activitats;

import pfc.Jclic.R;
import android.media.AudioManager;
import android.media.SoundPool;
import android.annotation.TargetApi;
import android.content.Context;

public class Sounds {
	private SoundPool sndPool;
	private float leftVolume;
	private float rightVolume;
	private float rate;
	private int action_error;
	private int action_ok;
	private int click;
	private int finished_error;
	private int finished_ok;
	private int start;
	
	@TargetApi(10)
	public  Sounds(Context appContext) {
	
	sndPool = new SoundPool(6, AudioManager.STREAM_MUSIC,100);
	
	
	rate = 1.0f;
	
	leftVolume = rightVolume = 1.0f;
	start = sndPool.load(appContext, R.raw.start, 1);
	action_error = sndPool.load(appContext, R.raw.action_error, 1);
	action_ok = sndPool.load(appContext, R.raw.action_ok, 1);
	click = sndPool.load(appContext, R.raw.click, 1);
	finished_error = sndPool.load(appContext, R.raw.finished_error, 1);
	finished_ok = sndPool.load(appContext, R.raw.finished_ok, 1);
	
	}
	public void playActionError() {
		sndPool.play(action_error, leftVolume, rightVolume, 1, 0, rate);
	}
	public void playAction_ok(){
		sndPool.play(action_ok, leftVolume, rightVolume, 1, 0, rate);
	}
	public void playClick(){
		sndPool.play(click, leftVolume, rightVolume, 1, 0, rate);
	}
	public void playFinished_error(){
		sndPool.play(finished_error, leftVolume, rightVolume, 1, 0, rate);
	}
	
	public void playFinished_ok(){
		sndPool.play(finished_ok, leftVolume, rightVolume, 1, 0, rate);
	}
	public void playStart(){
		sndPool.play(start, leftVolume, rightVolume, 1, 0, rate);
	}
	//¡¡¡¡l acabar el Activity llamar siempre a esta función!!!!.
	public void unloadAll() {
	sndPool.release();
	}

}