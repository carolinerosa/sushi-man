package com.game.ThauanLopes;


import java.util.HashSet;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Enemy extends GameObject {

	// Extrinsic values
	protected float xVelocity;
	
	protected int bitmapId;
	
	protected Side side;
	
	protected int width;
	protected int height;
	
	protected int initialDistancePos;
	
	protected int frameNr;
	protected int FPS;

	protected SpriteAnimationData walk;
	
	// Intrinsic values
	protected Sprite sprite;
	
	protected HashSet<Enemy> inimigos;
	protected float x;
	protected float y;

	public int gameCanvasWidth;
	public int gameCanvasHeight;

	public Enemy(HashSet<Enemy> inimigos)
	{
		this.inimigos = inimigos;
	}
	
	public void Update()
	{
		x += xVelocity;
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
		this.sprite.ChangeSprite(data);
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
	
	public void Die() {
		//this.inimigos.remove(this);
		Game.cemetery.add(this);
	}
	
	public void Destroy()
	{
		this.inimigos.remove(this);
	}

}
