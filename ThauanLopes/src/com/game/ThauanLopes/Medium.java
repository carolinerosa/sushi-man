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
		this.walk = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy2_walk(), 3, 5, AnimationType.LOOP);
		this.attack = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy2_walk(), 3, 5, AnimationType.ONCE);
		this.die = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy3_walk(), 3, 5, AnimationType.ONCE);
	}
	public Medium(HashSet<Enemy> inimigos)
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
