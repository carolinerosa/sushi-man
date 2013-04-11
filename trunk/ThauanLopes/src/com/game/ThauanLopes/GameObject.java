package com.game.ThauanLopes;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class GameObject {

	public String tag;
	
	public abstract void Update();
	
	public abstract void Draw(Canvas canvas);

	public abstract boolean collision(Rect fingersPos);
	
	public abstract void Die();
	
	public abstract void Destroy();
}
