package com.game.ThauanLopes;

import java.util.HashSet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Player extends GameObject {
	
	// eccentric attributes
	public float xVelocity = (float) 0.8;
	
	public int width = 30;
	public int height = 30;
	
	// intrinsic attributes
	HashSet<GameObject> gameObjects;
	public Side side;
	public float x;
	public float y;
	
	Sprite sprite;
	SpriteAnimationData walk = new SpriteAnimationData(BitmapStorage.getInstance().getPlayer_walk(), 3, 4, AnimationType.LOOP);
	SpriteAnimationData attack = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy3_walk(), 2, 1, AnimationType.ONCE);
	
	public Player(int canvasWidth, int canvasHeight, HashSet<GameObject> hs)
	{	
		this.gameObjects = hs;
		this.y = Game.floor;
		this.x = Game.canvasWidth / 2;
		hs.add(this);
		sprite = new Sprite();
		sprite.Start(walk);
		side = side.RIGHT;
		this.Turn();	
	}
	public void Update()
	{
		//Log.i("Player", "update");
		
		x += xVelocity;
		if(x + this.width > Game.canvasWidth)
		{	
			x = Game.canvasWidth - this.width;
		}
		if(x < 0)
		{
			x = 0;
		}
		
		this.sprite.Update();

	}

	public void Stop()
	{
	}
	public void Resume()
	{
	}

	public void SetPotsition(float x, float y)
	{
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public void Turn()
	{
		this.xVelocity = -xVelocity;
		switch(side)
		{
		case LEFT:
			side = side.RIGHT;
			this.sprite.turn(side.RIGHT);
			break;
		case RIGHT:
			side = side.LEFT;
			this.sprite.turn(side.LEFT);
			break;
		}
	}

	@Override
	public void Draw(Canvas canvas)
	{
		
		Rect r = new Rect ((int)x, Game.floor - this.height, (int)(x + this.width), Game.floor);
		sprite.Draw(canvas,r);
	}
	@Override
	public boolean collision(Rect enemyRect) {
		boolean state = false;
		Rect myRect = new Rect((int)this.x, Game.floor - this.height, (int)(x + this.width), Game.floor);
		if(enemyRect.intersect(myRect))
		{
			if(enemyRect.centerX() < myRect.centerX())
			{
				if(this.side.equals(side.LEFT))
				{
					state = true;
				}else
				{
					state = false;
				}
			}else
			{
				if(this.side.equals(side.RIGHT))
				{
					state = true;
				}else
				{
					state = false;
				}
			}
		}
		
		return state;	
	}

	public int Collision(Rect enemyRect) {
		int state = 0;
		Rect myRect = new Rect((int)this.x, Game.floor - this.height, (int)(x + this.width), Game.floor);
		if(enemyRect.intersect(myRect))
		{
			if(enemyRect.centerX() < myRect.centerX())
			{
				if(this.side.equals(side.LEFT))
				{
					state = 1;
				}else
				{
					state = 2;
				}
			}else
			{
				if(this.side.equals(side.RIGHT))
				{
					state = 1;
				}else
				{
					state = 2;
				}
			}
		}
		
		return state;	
	}
	@Override
	public void Die() {
		//this.gameObjects.remove(this);
		Game.cemetery.add(this);
	}
	
	public void Destroy()
	{
		this.gameObjects.remove(this);
	}

}
