package com.game.ThauanLopes;

import java.util.HashSet;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.game.ThauanLopes.R;

public class Medium extends Enemy {

	// The eccentric attributes. Change this for different instances.
	private void Setup()
	{	
		this.xVelocity = 1.2f;
		this.width = 30;
		this.height = 30;
		this.initialDistancePos = this.width;	
	}
	public Medium(HashSet<Enemy> inimigos)
	{
		super(inimigos);
		Setup();
		
		// Randomize the instantiate side
		Random random = new Random();
		
		// Sort the side to be instanced.
		int temp = random.nextInt(2);
		if(temp == 1)
		{
			this.x = 0 - this.initialDistancePos;
			this.side = side.RIGHT;
		}
		else 
		{ 
			this.x = Game.canvasWidth + this.initialDistancePos;
			xVelocity = -xVelocity;
			this.side = side.LEFT;
		} 
		
		walk = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy2_walk(), 3, 3, AnimationType.LOOP);
		
		this.sprite = new Sprite();
		sprite.Start(this.walk, this.side);

		
		this.inimigos.add(this);
		
		Log.i("ENEMY", "INSTANCIOU INIMIGO");
		
	}


}
