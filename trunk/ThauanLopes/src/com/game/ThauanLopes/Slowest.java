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
			this.width = 40;
			this.height = 20;
			this.initialDistancePos = this.width;
			
			this.walk = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy3_walk(), 3, 5, AnimationType.LOOP);
			this.attack = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy3_walk(), 3, 5, AnimationType.ONCE);
			this.die = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy1_walk(), 3, 5, AnimationType.ONCE);
		}

		public Slowest(HashSet<Enemy> inimigos)
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
