package com.game.ThauanLopes;

import java.util.HashSet;
import java.util.Random;

import android.util.Log;

public class Faster extends Enemy {
	
	// The eccentric attributes. Change this for different instances.
	private void Setup()
	{

		this.xVelocity = 1.4f;
		this.width = 50;
		this.height = 50;
		this.initialDistancePos = this.width;
		this.walk = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy1_walk(), 5, 5, AnimationType.LOOP);
		this.attack = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy1_walk(), 3, 5, AnimationType.ONCE);
		this.die = new SpriteAnimationData(BitmapStorage.getInstance().getSushi(), 1, 1/2, AnimationType.ONCE);
	}

	public Faster(HashSet<Enemy> inimigos)
	{
		super(inimigos);
		Setup();
		
		RandomizeSide();

		this.sprite = new Sprite();
		sprite.Start(this.walk, this.side);

		this.inimigos.add(this);
		
		Log.i("ENEMY", "INSTANCIOU INIMIGO");
		
	}


}
