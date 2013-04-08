package com.game.ThauanLopes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapStorage {
	
	private static BitmapStorage instance;
	
	
	public Bitmap getPlayer_walk() {
		return player_walk;
	}

	public void setPlayer_walk(Bitmap player_walk) {
		this.player_walk = player_walk;
	}

	public Bitmap getEnemy1_walk() {
		return enemy1_walk;
	}

	public void setEnemy1_walk(Bitmap enemy1_walk) {
		this.enemy1_walk = enemy1_walk;
	}

	public Bitmap getEnemy2_walk() {
		return enemy2_walk;
	}

	public void setEnemy2_walk(Bitmap enemy2_walk) {
		this.enemy2_walk = enemy2_walk;
	}

	public Bitmap getEnemy3_walk() {
		return enemy3_walk;
	}

	public void setEnemy3_walk(Bitmap enemy3_walk) {
		this.enemy3_walk = enemy3_walk;
	}

	
	public Bitmap getMetalslug_soldier() {
		return metalslug_soldier;
	}

	public void setMetalslug_soldier(Bitmap metalslug_soldier) {
		this.metalslug_soldier = metalslug_soldier;
	}

	private Bitmap player_walk;
	private Bitmap enemy1_walk;
	private Bitmap enemy2_walk;
	private Bitmap enemy3_walk;
	private Bitmap metalslug_soldier;
	
	private BitmapStorage()
	{
		
		this.enemy1_walk = BitmapFactory.decodeResource(Game.resources, R.drawable.inimigo1);
		this.enemy2_walk = BitmapFactory.decodeResource(Game.resources, R.drawable.inimigo2);
		this.enemy3_walk = BitmapFactory.decodeResource(Game.resources, R.drawable.inimigo3);
		this.player_walk = BitmapFactory.decodeResource(Game.resources, R.drawable.personagem);	 
		this.metalslug_soldier = BitmapFactory.decodeResource(Game.resources, R.drawable.rebelzombie);
	}
	
	public static BitmapStorage getInstance()
	{
		if(instance == null)
		{
			instance = new BitmapStorage();
		}
		
		return instance;
	}
	
	
}
