package com.game.ThauanLopes;


import java.util.HashSet;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Enemy extends GameObject {

	// Extrinsic values
	protected float xVelocity;
	protected Side side;
	
	protected int width;
	protected int height;

	protected SpriteAnimationData walk;
	protected SpriteAnimationData attack;
	protected SpriteAnimationData die;
	// Intrinsic values
	protected Sprite sprite;

	protected EnemyState enemyState;
	
	protected HashSet<Enemy> inimigos;
	protected float x;
	protected float y;
	protected int initialDistancePos;
	public int gameCanvasWidth;
	public int gameCanvasHeight;

	public Enemy(HashSet<Enemy> inimigos)
	{
		this.inimigos = inimigos;
		
		this.enemyState = enemyState.WALKING;
	}
	
	public void Update()
	{
		if(this.sprite.isFinished()){
			switch(enemyState)
			{
			case DIED:
				Game.cemetery.add(this);
				break;
				
			case ATTACKING:
				Walk();
				break;
			
			}
			
		}
		if(isAlive())
		{
			x += xVelocity;
		}
		
		this.sprite.Update();
	}
	@Override
	public void Draw(Canvas canvas)
	{
		Rect destRect = new Rect((int)this.x, Game.floor - this.height, (int)(this.x + this.width), Game.floor);
		this.sprite.Draw(canvas, destRect);
	}

	public void SetVelocity(int newVel)
	{
		this.xVelocity = newVel;
	}
	
	public void ChangeSprite(SpriteAnimationData data)
	{
		this.sprite.ChangeSprite(data, this.side);
	}
	
	protected void RandomizeSide()
	{
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
	}
	public boolean isAlive()
	{
		boolean alive = true;
		if(this.enemyState.equals(enemyState.DIED))
		{
			alive = false;
		}
		return alive;
	}
	public void SetPotsition(float x, float y)
	{
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public Rect getRect()
	{
		Rect rect = new Rect((int)this.x, Game.floor - this.height, (int)(this.x + this.width), Game.floor);
		return rect;
	}
	
	@Override
	public boolean collision(Rect rect) {
		
		Rect myRect = new Rect((int)this.x, Game.floor - this.height, (int)(this.x + this.width), Game.floor);
		boolean state = false;
		
		if(rect.intersect(myRect))
		{
			state = true;
		}
		
		return state;
	}
	
	public void Attack()
	{
		this.sprite.ChangeSprite(this.attack, side);
		this.enemyState = enemyState.ATTACKING;
	}
	private void Walk()
	{
		this.sprite.ChangeSprite(walk, side);
		this.enemyState = enemyState.WALKING;
	}
	public void Die() {
		//this.inimigos.remove(this);
		Game.cemetery.add(this);
//		this.sprite.ChangeSprite(this.die, this.side);
//		this.enemyState = enemyState.DIED;
	}
	
	public void Destroy()
	{
		this.inimigos.remove(this);
	}

}
