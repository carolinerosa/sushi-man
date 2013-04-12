package com.game.ThauanLopes;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	
	
	Game game;
	public static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(context==null)
		context=this;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		AudioManager audio = AudioManager.GetInstance();
		
		if(audio.ready == false)
		audio.PlayAudio("cat");
		
		else
			audio.resumeAudio();
		
		game = new Game(this);
		setContentView(game);
		
	}
	

	@Override
	public void onDestroy()
	{
     	AudioManager.GetInstance().StopAudio();
		game.SetAlive(false);
	    super.onDestroy();
	}



}
