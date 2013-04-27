package com.game.ThauanLopes;

import java.util.HashSet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Background extends GameObject

{
	private Bitmap mainBitmap;
	private Rect mainRect;
	private Rect finalRect;
	private HashSet<GameObject> go;
	public String tag = "bg";
	private Paint paint = new Paint();
	
	public Background(Bitmap bitmap,Rect destRect, HashSet<GameObject> gbs)
	{
		mainRect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
		mainBitmap = bitmap;
		finalRect = destRect;
		gbs.add(this);
		this.go = gbs;
	}
	
	@Override 
	public void Update() 
	{
		
	}

	@Override
	public void Draw(Canvas canvas) 
	{
		canvas.drawBitmap(mainBitmap, mainRect,finalRect, this.paint);
	}

	@Override
	public boolean collision(Rect fingersPos) 
	{
		return false;
		
	}

	@Override
	public void Die() {
		//this.go.remove(this);
		Game.cemetery.add(this);
	}
	public void Destroy()
	{
		this.go.remove(this);
	}
	
	
}
