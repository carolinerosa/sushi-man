package com.game.ThauanLopes;

import java.util.Random;

import android.util.Log;


public class EnemyManager {

	private float maxInterval;
	private float minInterval;
	
	private float cronometro;
	private float tempEnemyInterval;
	
	private Random random = new Random();
	
	public EnemyManager(int minInterval, int maxInterval)
	{
		this.maxInterval = maxInterval;
		this.minInterval = minInterval;
		
		tempEnemyInterval = random.nextFloat() * (this.maxInterval - this.minInterval) + minInterval;
	}

	public void Update()
	{
		
		this.cronometro += Game.deltaTime;
		//Log.i("ENEMYMANAGER", "" + this.cronometro);
		
		if(this.cronometro >= this.tempEnemyInterval)
		{
			this.cronometro = 0;
			Enemy enemy;
			
			int rndEnemy = random.nextInt(3);
			switch(rndEnemy)
			{
			case 0:
				enemy = new Slowest();
				break;
				
			case 1: 
				enemy = new Medium();
				break;
				
			case 2:
				enemy = new Faster();
				break;
			}
			
			
			this.tempEnemyInterval = random.nextFloat() * (this.maxInterval - this.minInterval) + minInterval;
		}
		
	}
	
	public void SetInterval(float min, float max)
	{
		this.minInterval = min;
		this.maxInterval = max;
	}
}
