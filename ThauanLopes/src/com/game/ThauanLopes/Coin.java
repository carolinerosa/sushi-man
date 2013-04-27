package com.game.ThauanLopes;

import java.util.HashSet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Coin extends GameObject {

	private Sprite sprite;
	private int x;
	private int y;
	private int width = 10;
	private int height = 10;
	private Side side;
	SpriteAnimationData stand;
	private HashSet<GameObject> gameObjects;
	private float upSpeed = 10;
	Bitmap sushi;
	Paint paint = new Paint();
	
	private float cronometro;
	private float lifeTime = 10;
	
	public Coin(HashSet<GameObject> gameObjects, float x2, float y2)
	{
		this.gameObjects = gameObjects;
		sushi = BitmapFactory.decodeResource(Game.resources, R.drawable.sushi);		
		this.x = (int) x2;
		this.y = (int) y2;
		sprite.Start(stand, side);
		
		gameObjects.add(this);
	}
	
	@Override
	public void Update() {
		
		cronometro += Game.deltaTime;
		
		if(cronometro >= lifeTime){
			Game.cemetery.add(this);
		}
		
		this.y -= upSpeed;
		this.sprite.Update();
		
	}
	@Override
	public void Draw(Canvas canvas) {
		
		Rect destRect = new Rect(this.x, this.y, this.x + this.width, this.y + this.height);
		canvas.drawBitmap(sushi, destRect,destRect, paint);
		
	}

	@Override
	public boolean collision(Rect fingersPos) {
		
		return false;
	}

	@Override
	public void Die() {
		
		
	}

	@Override
	public void Destroy() {
		Game.cemetery.add(this);
		
	}
	
	
	// Retirar o método DIE do gameObject. Deixar Destruir
	// Deixar somente o método Destroy, Update, Draw apenas
	// Usar interface para o Die;
}
