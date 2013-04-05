package com.game.ThauanLopes;

import java.util.HashSet;
import java.util.Random;

import com.game.ThauanLopes.R;
import com.game.ThauanLopes.R.id;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Slowest extends Enemy {

	// The eccentric attributes. Change this for different instances.
		private void Setup()
		{
			this.xVelocity = 1f;
			this.width = 100;
			this.height = 100;
			this.initialDistancePos = this.width;
		}

		public Slowest(HashSet<Enemy> inimigos)
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

			walk = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy3_walk(), 3, 2, AnimationType.LOOP);			
			
			this.sprite = new Sprite();
			this.sprite.Start(walk);
			
			this.sprite.turn(this.side);
			
			this.inimigos.add(this);
				
			Log.i("ENEMY", "INSTANCIOU INIMIGO");
		}
}
