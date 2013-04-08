package com.game.ThauanLopes;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class SpriteAnimationData {
	public int FPS;
	public Bitmap bitmap;
	public int frameNr;
	public AnimationType type;

	public SpriteAnimationData(Bitmap bitmap, int frameNr, int FPS, AnimationType type)
	{
		this.bitmap = bitmap;
		this.FPS = FPS;
		this.frameNr = frameNr;
		this.type = type;
	}
	
	

}
