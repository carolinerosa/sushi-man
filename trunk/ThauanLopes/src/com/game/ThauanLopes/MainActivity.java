package com.game.ThauanLopes;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	
	Game game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		game = new Game(this);
		setContentView(game);
		
	}
	
	@Override
	public void onDestroy()
	{
		game.SetAlive(false);
	    super.onDestroy();
	    
	}

	
}
