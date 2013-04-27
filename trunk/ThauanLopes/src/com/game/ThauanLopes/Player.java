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
	public PlayerState playerState;
	public int width = 50;
	public int height = 50;
	
	// intrinsic attributes
	HashSet<GameObject> gameObjects;
	public Side side;
	private float x;
	private float y;
	private Sprite sprite;
	private SpriteAnimationData walk = new SpriteAnimationData(BitmapStorage.getInstance().getPlayer_walk(), 10, 11, AnimationType.LOOP);
	private SpriteAnimationData attack = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy3_walk(), 3, 10, AnimationType.ONCE);
	private SpriteAnimationData die = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy2_walk(), 3, 10, AnimationType.ONCE);
	
	public Player(int canvasWidth, int canvasHeight, HashSet<GameObject> hs)
	{	
		this.gameObjects = hs;
		this.y = Game.floor;
		this.x = Game.canvasWidth / 2;
		hs.add(this);
		sprite = new Sprite();
		sprite.Start(walk, this.side);
		this.playerState = playerState.WALKING;
		
		side = side.RIGHT;
		this.Turn();	
	}
	
	public void Update()
	{
		//Log.i("Player", "update");
		
		if(sprite.isFinished())
		{
			switch(playerState)
			{
			case DIED:
				Game.cemetery.add(this);
				break;
				
			
			case ATTACKING:
				this.sprite.ChangeSprite(walk, this.side);
				this.playerState = playerState.WALKING;
				break;
			}
		}
		if(isAlive()){
			x += xVelocity;
			if(x + this.width > Game.canvasWidth)
			{	
				x = Game.canvasWidth - this.width;
			}
			if(x < 0)
			{
				x = 0;
			}
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
		switch(this.playerState){
		
		case WALKING:
			
		break;
		
		case ATTACKING:
			switch(this.side){
				case LEFT:
					r  = new Rect ((int)x - this.width , Game.floor - this.height, (int)(x + this.width), Game.floor);
				break;
				case RIGHT:
					r = new Rect ((int)x, Game.floor - this.height, (int)(x + 2*this.width), Game.floor);
	
				break;
			}
			break;
		}
		sprite.Draw(canvas,r);
		
	}


	public int Collision(Rect enemyRect) 
	{
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
	public boolean isAlive()
	{
		boolean alive = true;
		if(this.playerState.equals(playerState.DIED))
		{
			alive = false;
		}
		return alive;
	}

	public void Attack()
	{
		this.sprite.ChangeSprite(this.attack, this.side);
		this.playerState = playerState.ATTACKING;
	}
	
	@Override
	public void Die() {
		
		this.sprite.ChangeSprite(die, side);
		this.playerState = playerState.DIED;
		
	}
	
	public void Destroy()
	{
		this.gameObjects.remove(this);
	}

	@Override
	public boolean collision(Rect fingersPos) {
		// TODO Auto-generated method stub
		return false;
	}

}


