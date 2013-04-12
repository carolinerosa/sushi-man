package com.game.ThauanLopes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class ImageButton extends GameObject
{
	public Bitmap myBitmap;
	public Paint paint;
	public Rect myPosition;
	public String tag;
	
	public ImageButton(Bitmap bitmap,int posX,int posY,String tag)
	{
		myPosition = new Rect(posX,posY,posX + bitmap.getWidth(),posY+bitmap.getHeight());
		this.tag = tag;
		myBitmap = bitmap;
		paint = new Paint();
	}
	
	@Override
	public void Draw(Canvas canvas)
	{
		canvas.drawBitmap(myBitmap,myPosition.left,myPosition.top,paint);
	}
	
	@Override
	public boolean collision(Rect r)
	{
		if(r.intersect(myPosition))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
		
	}

}
