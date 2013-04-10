package com.game.ThauanLopes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Sprite
{
	private static final String TAG = "SPRITE SHEET DRAW";
	
	private int frameNr;
	private int currentFrame;
	private double framePeriod;
	
	private Bitmap bitmap;
	private Rect selectRect;
	private int spriteWidth;
	private int spriteHeight;
	
	private AnimationType type;
	
	private long cronometro;
	
	private Paint paint;
	
	private boolean pause = false;
	private boolean finished = false;
	
	public Sprite()
	{
		paint = new Paint();
	}
	
	public void Start(SpriteAnimationData spriteAnimation, Side side)
	{
		
		this.frameNr = spriteAnimation.frameNr;
		this.spriteWidth = spriteAnimation.bitmap.getWidth() / spriteAnimation.frameNr;
		this.spriteHeight = spriteAnimation.bitmap.getHeight();
		this.framePeriod = 1000/spriteAnimation.FPS;
		this.currentFrame = 0;
		this.selectRect = new Rect(0,0, spriteWidth, spriteHeight);
		this.bitmap = spriteAnimation.bitmap;
		this.turn(side);
		this.type = spriteAnimation.type;
		this.cronometro = 0;
		this.currentFrame = 0;
		
		this.finished = false;
		
	}
	public void ChangeSprite(SpriteAnimationData spriteAnimation, Side side)
	{
		this.frameNr = spriteAnimation.frameNr;
		this.spriteWidth = spriteAnimation.bitmap.getWidth() / spriteAnimation.frameNr;
		this.spriteHeight = spriteAnimation.bitmap.getHeight();
		this.framePeriod = 1000/spriteAnimation.FPS;
		this.currentFrame = 0;
		this.selectRect = new Rect(0,0, spriteWidth, spriteHeight);
		this.bitmap = spriteAnimation.bitmap;
		this.turn(side);
		this.type = spriteAnimation.type;
		
		this.cronometro = 0;
		this.currentFrame = 0;
		
		this.finished = false;
	}
	
	public void Update()
	{
		if(!pause || !finished){
			
			this.cronometro += Game.deltaTime;
			
			if(cronometro >= framePeriod)
			{
				this.cronometro = 0;
				
				if(currentFrame >= frameNr - 1)
				{
					if(this.type.equals(AnimationType.ONCE))
					{
						onSpriteAnimationFinished();
						
					}else
					{
						currentFrame = 0;	
					}
				}else
				{
					currentFrame++;
				}
				
				
				this.selectRect.left = this.currentFrame * this.spriteWidth;
				this.selectRect.right = this.selectRect.left + this.spriteWidth;
				
				
			}
		}
		
	}

	public void Draw(Canvas canvas, Rect destRect)
	{
		canvas.drawBitmap(this.bitmap, this.selectRect, destRect, paint);
	}
	public boolean isFinished()
	{
		return finished;
	}
	public void onSpriteAnimationFinished()
	{
		finished = true;
		
	}
	
	public void turn(Side side)
	{
		if(side == side.RIGHT)
		{
			this.selectRect.top = 0;
			this.selectRect.bottom = this.spriteHeight / 2;
			
		}else
		{
			this.selectRect.top = this.spriteHeight / 2;
			this.selectRect.bottom = this.spriteHeight;
		}
	}
	public void Pause()
	{
		this.pause = true;
	}
	public void Resume()
	{
		this.pause = false;
	}
	
}
