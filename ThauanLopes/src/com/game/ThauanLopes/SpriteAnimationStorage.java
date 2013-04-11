package com.game.ThauanLopes;

public class SpriteAnimationStorage {
	private SpriteAnimationStorage()
	{
		
	}
	private static SpriteAnimationStorage instance;
	public static SpriteAnimationStorage getInstance()
	{
		if(instance == null)
		{
			instance = new SpriteAnimationStorage();
		}
		
		return instance;
	}
	
	
	
	
	
	
}
