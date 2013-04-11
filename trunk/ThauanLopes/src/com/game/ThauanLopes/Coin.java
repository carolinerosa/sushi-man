package com.game.ThauanLopes;

import java.util.HashSet;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Coin extends GameObject {

	private Sprite sprite;
	private int x;
	private int y;
	private int width;
	private int height;
	private Side side;
	SpriteAnimationData stand;
	SpriteAnimationData catched;
	private HashSet<GameObject> gameObjects;
	
	public Coin(HashSet<GameObject> gameObjects)
	{
		this.gameObjects = gameObjects;
		side = side.LEFT;
		sprite = new Sprite();
		stand = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy1_walk(), 3, 5, AnimationType.LOOP);
		catched = new SpriteAnimationData(BitmapStorage.getInstance().getEnemy1_walk(), 3, 5, AnimationType.ONCE);
		sprite.Start(stand, side);
		
		gameObjects.add(this);
	}
	
	@Override
	public void Update() {
		
		this.sprite.Update();
		
	}
	@Override
	public void Draw(Canvas canvas) {
		
		Rect destRect = new Rect(this.x, this.y, this.x + this.width, this.y + this.height);
		this.sprite.Draw(canvas, destRect);
		
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
