package com.game.ThauanLopes;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	
	Game game;
	public static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(context==null)
		context=this;
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
