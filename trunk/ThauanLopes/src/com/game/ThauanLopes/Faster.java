package com.game.ThauanLopes;

import java.util.HashSet;
import java.util.Random;

import android.util.Log;

public class Faster extends Enemy {


	
	// The eccentric attributes. Change this for different instances.
	private void Setup()
	{

		this.xVelocity = 1.4f;
		this.width = 30;
		this.height = 40;
		this.initialDistancePos = this.width;
			
	}
	public Faster(HashSet<Enemy> inimigos)
	{
		super(inimigos);
		Setup();
		
		// Randomize the instantiate side
		Random random = new Random();
		
		// Randomize the side to be instanced.
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

		this.inimigos.add(this);
		
		Log.i("ENEMY", "INSTANCIOU INIMIGO");
		
	}	

}
