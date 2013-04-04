package com.game.ThauanLopes;

import java.util.Random;
import android.util.Log;

import com.game.ThauanLopes.R;

public class Faster extends Enemy {


	
	// The eccentric attributes. Change this for different instances.
	private void Setup()
	{

		this.xVelocity = 1.4f;
		this.width = 100;
		this.height = 100;
		this.initialDistancePos = this.width;
			
	}
	public Faster()
	{
		Setup();
		
		// Randomize the instantiate side
		Random random = new Random();
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


		walk = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy1_walk(), 3, 4, AnimationType.LOOP);
		
		this.sprite = new Sprite();
		sprite.Start(this.walk);
		this.sprite.turn(this.side);

		Game.inimigos.add(this);
		
		Log.i("ENEMY", "INSTANCIOU INIMIGO");
		
	}	

}
